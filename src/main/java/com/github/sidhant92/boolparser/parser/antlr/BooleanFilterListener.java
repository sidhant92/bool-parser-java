package com.github.sidhant92.boolparser.parser.antlr;

import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;
import org.apache.commons.lang3.tuple.Pair;
import com.github.sidhant92.boolparser.constant.DataType;
import com.github.sidhant92.boolparser.constant.LogicalOperationType;
import com.github.sidhant92.boolparser.constant.Operator;
import com.github.sidhant92.boolparser.domain.BooleanToken;
import com.github.sidhant92.boolparser.domain.InToken;
import com.github.sidhant92.boolparser.domain.Token;
import com.github.sidhant92.boolparser.domain.NumericRangeToken;
import com.github.sidhant92.boolparser.domain.NumericToken;
import com.github.sidhant92.boolparser.domain.StringToken;
import com.github.sidhant92.boolparser.domain.UnaryToken;
import com.github.sidhant92.boolparser.exception.InvalidExpressionException;
import com.github.sidhant92.boolparser.operator.OperatorFactory;
import com.github.sidhant92.boolparser.util.ValueUtils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BooleanFilterListener extends BooleanExpressionBaseListener {
    private Token token;

    private final Stack<Token> currentTokens;

    private org.antlr.v4.runtime.Token lastToken;

    public BooleanFilterListener() {
        this.token = null;
        this.lastToken = null;
        this.currentTokens = new Stack<>();
        OperatorFactory.initialize();
    }

    public Token getNode() {
        return this.token;
    }

    @Override
    public void exitComparatorExpression(BooleanExpressionBoolParser.ComparatorExpressionContext ctx) {
        final String variableName = ctx.left.getText();
        final DataType dataType = getDataType(ctx.right.getStart());
        final Operator operator = Operator.getOperatorFromSymbol(ctx.op.getText()).orElse(Operator.EQUALS);
        if (dataType == DataType.STRING) {
            currentTokens.add(new StringToken(variableName, (String) ValueUtils.convertValue(ctx.right.getText(), DataType.STRING)));
        } else {
            currentTokens.add(new NumericToken(variableName, ValueUtils.convertValue(ctx.right.getText(), dataType), operator, dataType));
        }
        super.enterComparatorExpression(ctx);
    }

    @Override
    public void exitToExpression(BooleanExpressionBoolParser.ToExpressionContext ctx) {
        final String field = ctx.field.getText();
        final DataType lowerDataType = getDataType(ctx.lower.start);
        final Object lowerValue = ValueUtils.convertValue(ctx.lower.start.getText(), lowerDataType);
        final DataType upperDataType = getDataType(ctx.upper.start);
        final Object upperValue = ValueUtils.convertValue(ctx.upper.getText(), upperDataType);
        currentTokens.add(new NumericRangeToken(field, lowerValue, upperValue, lowerDataType, upperDataType));
        super.exitToExpression(ctx);
    }

    @Override
    public void exitInExpression(BooleanExpressionBoolParser.InExpressionContext ctx) {
        final String field = ctx.field.getText();
        final List<Pair<DataType, Object>> items = ctx.data.children
                .stream()
                .filter(child -> child instanceof BooleanExpressionBoolParser.TypesContext)
                .map(child -> {
                    final DataType dataType = getDataType(((BooleanExpressionBoolParser.TypesContext) child).start);
                    final Object value = ValueUtils.convertValue(child.getText(), dataType);
                    return Pair.of(dataType, value);
                })
                .collect(Collectors.toList());
        currentTokens.add(new InToken(field, items));
        super.exitInExpression(ctx);
    }

    private DataType getDataType(final org.antlr.v4.runtime.Token token) {
        switch (token.getType()) {
            case BooleanExpressionLexer.DECIMAL:
                return DataType.DECIMAL;
            case BooleanExpressionLexer.INTEGER:
                return ValueUtils.getNumericDataType(token.getText());
            case BooleanExpressionLexer.APP_VERSION:
                return DataType.APP_VERSION;
            case BooleanExpressionLexer.TRUE:
            case BooleanExpressionLexer.FALSE:
                return DataType.BOOLEAN;
            default:
                return DataType.STRING;
        }
    }

    private LogicalOperationType getLogicalOperator(final org.antlr.v4.runtime.Token token) {
        switch (token.getType()) {
            case BooleanExpressionLexer.AND:
                return LogicalOperationType.AND;
            case BooleanExpressionLexer.OR:
                return LogicalOperationType.OR;
            default:
                return LogicalOperationType.NOT;
        }
    }

    @Override
    public void exitParse(BooleanExpressionBoolParser.ParseContext ctx) {
        if (this.token == null && this.currentTokens.size() == 1) {
            this.token = currentTokens.pop();
        } else {
            log.error("Error parsing expression for the string {}", ctx.getText());
            throw new InvalidExpressionException();
        }
        super.exitParse(ctx);
    }

    @Override
    public void exitNotExpression(BooleanExpressionBoolParser.NotExpressionContext ctx) {
        if (currentTokens.isEmpty()) {
            if (lastToken == null) {
                log.error("Error parsing not expression for the string {}", ctx.getText());
                throw new InvalidExpressionException();
            }
            final DataType dataType = getDataType(lastToken);
            final Object value = ValueUtils.convertValue(lastToken.getText(), dataType);
            currentTokens.add(new UnaryToken(dataType, value));
        }
        final BooleanToken booleanToken = new BooleanToken(currentTokens.pop(), null, LogicalOperationType.NOT);
        currentTokens.add(booleanToken);
        super.exitNotExpression(ctx);
    }

    @Override
    public void exitTypesExpression(BooleanExpressionBoolParser.TypesExpressionContext ctx) {
        this.lastToken = ctx.start;
    }

    @Override
    public void exitBinaryExpression(BooleanExpressionBoolParser.BinaryExpressionContext ctx) {
        if (currentTokens.size() < 2) {
            log.error("Error parsing binary expression for the string {}", ctx.getText());
            throw new InvalidExpressionException();
        }
        final Token firstToken = currentTokens.pop();
        final Token secondToken = currentTokens.pop();
        final LogicalOperationType operator = getLogicalOperator(ctx.op.getStart());
        final BooleanToken booleanToken = new BooleanToken(secondToken, firstToken, operator);
        currentTokens.add(booleanToken);
        super.enterBinaryExpression(ctx);
    }
}

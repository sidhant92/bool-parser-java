package com.github.sidhant92.boolparser.parser.antlr;

import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;
import org.apache.commons.lang3.tuple.Pair;
import com.github.sidhant92.boolparser.constant.DataType;
import com.github.sidhant92.boolparser.constant.LogicalOperationType;
import com.github.sidhant92.boolparser.constant.Operator;
import com.github.sidhant92.boolparser.domain.BooleanNode;
import com.github.sidhant92.boolparser.domain.InNode;
import com.github.sidhant92.boolparser.domain.Node;
import com.github.sidhant92.boolparser.domain.NumericRangeNode;
import com.github.sidhant92.boolparser.domain.ComparisonNode;
import com.github.sidhant92.boolparser.domain.UnaryNode;
import com.github.sidhant92.boolparser.exception.InvalidExpressionException;
import com.github.sidhant92.boolparser.util.ValueUtils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BooleanFilterListener extends BooleanExpressionBaseListener {
    private Node node;

    private final Stack<Node> currentNodes;

    private org.antlr.v4.runtime.Token lastToken;

    public BooleanFilterListener() {
        this.node = null;
        this.lastToken = null;
        this.currentNodes = new Stack<>();
    }

    public Node getNode() {
        return this.node;
    }

    @Override
    public void exitComparatorExpression(BooleanExpressionParser.ComparatorExpressionContext ctx) {
        final String variableName = ctx.left.getText();
        final DataType dataType = getDataType(ctx.right.getStart());
        final Operator operator = Operator.getOperatorFromSymbol(ctx.op.getText()).orElse(Operator.EQUALS);
        currentNodes.add(new ComparisonNode(variableName, ValueUtils.convertValue(ctx.right.getText(), dataType), operator, dataType));
        super.enterComparatorExpression(ctx);
    }

    @Override
    public void exitToExpression(BooleanExpressionParser.ToExpressionContext ctx) {
        final String field = ctx.field.getText();
        final DataType lowerDataType = getDataType(ctx.lower.start);
        final Object lowerValue = ValueUtils.convertValue(ctx.lower.start.getText(), lowerDataType);
        final DataType upperDataType = getDataType(ctx.upper.start);
        final Object upperValue = ValueUtils.convertValue(ctx.upper.getText(), upperDataType);
        currentNodes.add(new NumericRangeNode(field, lowerValue, upperValue, lowerDataType, upperDataType));
        super.exitToExpression(ctx);
    }

    @Override
    public void exitInExpression(BooleanExpressionParser.InExpressionContext ctx) {
        final String field = ctx.field.getText();
        final List<Pair<DataType, Object>> items = ctx.data.children
                .stream()
                .filter(child -> child instanceof BooleanExpressionParser.TypesContext)
                .map(child -> {
                    final DataType dataType = getDataType(((BooleanExpressionParser.TypesContext) child).start);
                    final Object value = ValueUtils.convertValue(child.getText(), dataType);
                    return Pair.of(dataType, value);
                })
                .collect(Collectors.toList());
        currentNodes.add(new InNode(field, items));
        super.exitInExpression(ctx);
    }

    private DataType getDataType(final org.antlr.v4.runtime.Token token) {
        switch (token.getType()) {
            case BooleanExpressionLexer.DECIMAL:
                return DataType.DECIMAL;
            case BooleanExpressionLexer.INTEGER:
                return ValueUtils.getNumericDataType(token.getText());
            case BooleanExpressionLexer.APP_VERSION:
                return DataType.VERSION;
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
    public void exitParse(BooleanExpressionParser.ParseContext ctx) {
        if (this.node == null && this.currentNodes.size() == 1) {
            this.node = currentNodes.pop();
        } else {
            log.error("Error parsing expression for the string {}", ctx.getText());
            throw new InvalidExpressionException();
        }
        super.exitParse(ctx);
    }

    @Override
    public void exitNotExpression(BooleanExpressionParser.NotExpressionContext ctx) {
        if (currentNodes.isEmpty()) {
            if (lastToken == null) {
                log.error("Error parsing not expression for the string {}", ctx.getText());
                throw new InvalidExpressionException();
            }
            final DataType dataType = getDataType(lastToken);
            final Object value = ValueUtils.convertValue(lastToken.getText(), dataType);
            currentNodes.add(new UnaryNode(dataType, value));
        }
        final BooleanNode booleanToken = new BooleanNode(currentNodes.pop(), null, LogicalOperationType.NOT);
        currentNodes.add(booleanToken);
        super.exitNotExpression(ctx);
    }

    @Override
    public void exitTypesExpression(BooleanExpressionParser.TypesExpressionContext ctx) {
        this.lastToken = ctx.start;
    }

    @Override
    public void exitBinaryExpression(BooleanExpressionParser.BinaryExpressionContext ctx) {
        if (currentNodes.size() < 2) {
            log.error("Error parsing binary expression for the string {}", ctx.getText());
            throw new InvalidExpressionException();
        }
        final Node firstNode = currentNodes.pop();
        final Node secondNode = currentNodes.pop();
        final LogicalOperationType operator = getLogicalOperator(ctx.op.getStart());
        final BooleanNode booleanToken = new BooleanNode(secondNode, firstNode, operator);
        currentNodes.add(booleanToken);
        super.enterBinaryExpression(ctx);
    }
}

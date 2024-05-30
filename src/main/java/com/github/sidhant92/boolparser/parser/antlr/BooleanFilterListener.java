package com.github.sidhant92.boolparser.parser.antlr;

import java.util.List;
import java.util.Objects;
import java.util.Stack;
import java.util.stream.Collectors;
import org.antlr.v4.runtime.CommonToken;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;
import org.apache.commons.lang3.StringUtils;
import com.github.sidhant92.boolparser.constant.FunctionType;
import com.github.sidhant92.boolparser.constant.DataType;
import com.github.sidhant92.boolparser.constant.LogicalOperationType;
import com.github.sidhant92.boolparser.constant.Operator;
import com.github.sidhant92.boolparser.domain.arithmetic.ArithmeticFunctionNode;
import com.github.sidhant92.boolparser.domain.arithmetic.ArithmeticNode;
import com.github.sidhant92.boolparser.domain.logical.ArrayNode;
import com.github.sidhant92.boolparser.domain.logical.BooleanNode;
import com.github.sidhant92.boolparser.domain.logical.InNode;
import com.github.sidhant92.boolparser.domain.logical.Node;
import com.github.sidhant92.boolparser.domain.logical.NumericRangeNode;
import com.github.sidhant92.boolparser.domain.logical.ComparisonNode;
import com.github.sidhant92.boolparser.domain.arithmetic.UnaryNode;
import com.github.sidhant92.boolparser.exception.InvalidExpressionException;
import com.github.sidhant92.boolparser.util.ValueUtils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BooleanFilterListener extends BooleanExpressionBaseListener {
    private Node node;

    private final Stack<Node> currentNodes;

    private org.antlr.v4.runtime.Token lastToken;

    private int tokenCount;

    private String defaultField;

    public BooleanFilterListener(final String defaultField) {
        this.defaultField = defaultField;
        this.node = null;
        this.lastToken = null;
        this.currentNodes = new Stack<>();
    }

    public Node getNode() {
        return this.node;
    }

    @Override
    public void exitComparatorExpression(BooleanExpressionParser.ComparatorExpressionContext ctx) {
        final String variableName = getField(ctx.left.getText());
        final Operator operator = Operator.getOperatorFromSymbol(ctx.op.getText()).orElse(Operator.EQUALS);
        /*if ((ctx.right instanceof BooleanExpressionParser.ParentExpressionContext || ctx.right instanceof BooleanExpressionParser.ArithmeticFunctionExpressionContext) && !currentNodes.isEmpty()) {
            final Node value = currentNodes.pop();
            currentNodes.add(new ComparisonNode(variableName, value, operator, DataType.INTEGER));
        } else {
            final DataType dataType = getDataType(ctx.right.getStart());
            currentNodes.add(new ComparisonNode(variableName, ValueUtils.convertValue(ctx.right.getText(), dataType), operator, dataType));
        }*/
        if (!(ctx.right instanceof BooleanExpressionParser.TypesExpressionContext) && !currentNodes.isEmpty()) {
            final Node value = currentNodes.pop();
            currentNodes.add(new ComparisonNode(variableName, value, operator, DataType.INTEGER));
        } else {
            final DataType dataType = getDataType(ctx.right.getStart());
            currentNodes.add(new ComparisonNode(variableName, ValueUtils.convertValue(ctx.right.getText(), dataType), operator, dataType));
        }
        super.enterComparatorExpression(ctx);
    }

    private UnaryNode getUnaryNode(final BooleanExpressionParser.TypesExpressionContext ctx) {
        final DataType dataType = getDataType(ctx.getStart());
        final Object operand = ValueUtils.convertValue(ctx.getText(), dataType);
        return UnaryNode.builder().value(operand).dataType(dataType).build();
    }

    @Override
    public void exitArithmeticExpression(BooleanExpressionParser.ArithmeticExpressionContext ctx) {
        final Operator operator = Operator.getOperatorFromSymbol(ctx.op.getText()).orElse(Operator.EQUALS);
        if (ctx.left instanceof BooleanExpressionParser.TypesExpressionContext && ctx.right instanceof BooleanExpressionParser.TypesExpressionContext) {
            final UnaryNode left = getUnaryNode((BooleanExpressionParser.TypesExpressionContext) ctx.left);
            final UnaryNode right = getUnaryNode((BooleanExpressionParser.TypesExpressionContext) ctx.right);
            final ArithmeticNode node = ArithmeticNode.builder().left(left).right(right).operator(operator).build();
            currentNodes.add(node);
        } else if (ctx.left instanceof BooleanExpressionParser.TypesExpressionContext) {
            final UnaryNode left = getUnaryNode((BooleanExpressionParser.TypesExpressionContext) ctx.left);
            final Node right = currentNodes.pop();
            final ArithmeticNode node = ArithmeticNode.builder().left(left).right(right).operator(operator).build();
            currentNodes.add(node);
        } else if (ctx.right instanceof BooleanExpressionParser.TypesExpressionContext) {
            final UnaryNode right = getUnaryNode((BooleanExpressionParser.TypesExpressionContext) ctx.right);
            final Node left = currentNodes.pop();
            final ArithmeticNode node = ArithmeticNode.builder().left(left).right(right).operator(operator).build();
            currentNodes.add(node);
        } else {
            if (currentNodes.size() < 2) {
                log.error("Error parsing expression for the string {}", ctx.getText());
                throw new InvalidExpressionException();
            }
            final Node right = currentNodes.pop();
            final Node left = currentNodes.pop();
            final ArithmeticNode node = ArithmeticNode.builder().left(left).right(right).operator(operator).build();
            currentNodes.add(node);
        }
        super.exitArithmeticExpression(ctx);
    }

    @Override
    public void exitUnaryArithmeticExpression(BooleanExpressionParser.UnaryArithmeticExpressionContext ctx) {
        final DataType dataType = getDataType(ctx.exp.getStart());
        final Object operand = ValueUtils.convertValue(ctx.exp.getText(), dataType);
        final UnaryNode leafNode = UnaryNode.builder().value(operand).dataType(dataType).build();
        currentNodes.add(ArithmeticNode.builder().left(leafNode).operator(Operator.UNARY).build());
        super.enterUnaryArithmeticExpression(ctx);
    }

    @Override
    public void exitToExpression(BooleanExpressionParser.ToExpressionContext ctx) {
        validateField(ctx.field, ctx.getText());
        final String field = getField(ctx.field.getText());
        final DataType lowerDataType = getDataType(ctx.lower.start);
        final Object lowerValue = ValueUtils.convertValue(ctx.lower.start.getText(), lowerDataType);
        final DataType upperDataType = getDataType(ctx.upper.start);
        final Object upperValue = ValueUtils.convertValue(ctx.upper.getText(), upperDataType);
        currentNodes.add(new NumericRangeNode(field, lowerValue, upperValue, lowerDataType, upperDataType));
        super.exitToExpression(ctx);
    }

    @Override
    public void exitArrayExpression(BooleanExpressionParser.ArrayExpressionContext ctx) {
        validateField(ctx.field, ctx.getText());
        final String field = getField(ctx.field.getText());
        final List<Node> items = getArrayElements(ctx.data.children);
        final Operator operator = Operator.getOperatorFromSymbol(ctx.op.getText()).orElse(Operator.EQUALS);
        currentNodes.add(new ArrayNode(field, operator, items));
        super.exitArrayExpression(ctx);
    }

    @Override
    public void exitArithmeticFunctionExpression(BooleanExpressionParser.ArithmeticFunctionExpressionContext ctx) {
        if (ctx.data.exception != null) {
            log.error("Error parsing expression for the string {}", ctx.getText());
            throw new InvalidExpressionException();
        }
        final FunctionType functionType = FunctionType.getArrayFunctionFromSymbol(ctx.left.getText()).orElseThrow(() -> {
            log.error("Error parsing expression for the string {}", ctx.getText());
            return new InvalidExpressionException();
        });
        final List<Node> items = getArrayElements(ctx.data.children);
        currentNodes.add(new ArithmeticFunctionNode(functionType, items));
        super.exitArithmeticFunctionExpression(ctx);
    }

    @Override
    public void exitInExpression(BooleanExpressionParser.InExpressionContext ctx) {
        validateField(ctx.field, ctx.getText());
        final String field = getField(ctx.field.getText());
        final List<Node> items = getArrayElements(ctx.data.children);
        final InNode inNode = new InNode(field, items);
        if (Objects.isNull(ctx.not)) {
            currentNodes.add(inNode);
        } else {
            final BooleanNode booleanNode = new BooleanNode(inNode, null, LogicalOperationType.NOT);
            currentNodes.add(booleanNode);
        }
        super.exitInExpression(ctx);
    }

    private List<Node> getArrayElements(final List<ParseTree> trees) {
        return trees
                .stream()
                .filter(child -> child instanceof BooleanExpressionParser.TypesExpressionContext)
                .map(child -> {
                    final DataType dataType = getDataType(((BooleanExpressionParser.TypesExpressionContext) child).start);
                    final Object value = ValueUtils.convertValue(child.getText(), dataType);
                    return new UnaryNode(dataType, value);
                })
                .collect(Collectors.toList());
    }

    private void validateField(final Token token, final String text) {
        if (Objects.isNull(token) || (StringUtils.isBlank(token.getText()) && StringUtils.isBlank(this.defaultField))) {
            log.error("Error parsing expression (missing field) for the string {}", text);
            throw new InvalidExpressionException();
        }
    }

    private String getField(final String field) {
        return StringUtils.isBlank(field) ? defaultField : field;
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
        } else if (this.node == null && this.currentNodes.size() == 2) {
            final Node firstNode = currentNodes.pop();
            final Node secondNode = currentNodes.pop();
            if (firstNode instanceof ArithmeticNode && secondNode instanceof ArithmeticNode && ((ArithmeticNode) secondNode).getRight() == null) {
                this.node = ArithmeticNode.builder().operator(Operator.UNARY).left(firstNode).build();
            }
            if (secondNode instanceof ArithmeticNode && firstNode instanceof ArithmeticNode && ((ArithmeticNode) firstNode).getRight() == null) {
                this.node = ArithmeticNode.builder().operator(Operator.UNARY).left(secondNode).build();
            }
        }
        if (this.node == null && tokenCount == 1 && lastToken instanceof CommonToken) {
            this.node = UnaryNode.builder().dataType(DataType.STRING).value(ValueUtils.convertValue(lastToken.getText(), DataType.STRING).toString())
                                 .build();
        }
        if (this.node == null) {
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
        tokenCount++;
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

package com.github.sidhant92.boolparser.parser.antlr;

import java.util.List;
import java.util.Objects;
import java.util.Stack;
import java.util.stream.Collectors;
import org.antlr.v4.runtime.CommonToken;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNodeImpl;
import org.apache.commons.lang3.StringUtils;
import com.github.sidhant92.boolparser.constant.FunctionType;
import com.github.sidhant92.boolparser.constant.DataType;
import com.github.sidhant92.boolparser.constant.LogicalOperationType;
import com.github.sidhant92.boolparser.constant.Operator;
import com.github.sidhant92.boolparser.domain.FieldNode;
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

public class BooleanFilterListener extends BooleanExpressionBaseListener {
    private Node node;

    private final Stack<Node> currentNodes;

    private org.antlr.v4.runtime.Token lastToken;

    private int tokenCount;

    private final String defaultField;

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
        currentNodes.add(mapComparatorExpressionContext(ctx));
        super.enterComparatorExpression(ctx);
    }

    @Override
    public void exitArithmeticExpression(BooleanExpressionParser.ArithmeticExpressionContext ctx) {
        currentNodes.add(mapArithmeticExpressionContext(ctx));
        super.exitArithmeticExpression(ctx);
    }

    @Override
    public void exitUnaryArithmeticExpression(BooleanExpressionParser.UnaryArithmeticExpressionContext ctx) {
        final Node leafNode = !currentNodes.isEmpty() && currentNodes.peek() instanceof ArithmeticNode ? currentNodes.pop() : mapTypesExpressionContext(
                (BooleanExpressionParser.TypesExpressionContext) ctx.exp);
        currentNodes.add(ArithmeticNode.builder().left(leafNode).operator(Operator.UNARY).build());
        super.enterUnaryArithmeticExpression(ctx);
    }

    @Override
    public void exitToExpression(BooleanExpressionParser.ToExpressionContext ctx) {
        currentNodes.add(mapToExpressionContext(ctx));
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
        final Node node = mapArithmeticFunctionExpressionContext(ctx);
        currentNodes.add(node);
        super.exitArithmeticFunctionExpression(ctx);
    }

    @Override
    public void exitInExpression(BooleanExpressionParser.InExpressionContext ctx) {
        currentNodes.add(mapInExpressionContext(ctx));
        super.exitInExpression(ctx);
    }

    private List<Node> getArrayElements(final List<ParseTree> trees) {
        return trees
                .stream()
                .filter(child -> !(child instanceof TerminalNodeImpl))
                .map(this::mapContextToNode)
                .collect(Collectors.toList());
    }

    private Node mapContextToNode(final ParseTree ctx) {
        if (ctx instanceof BooleanExpressionParser.ArithmeticExpressionContext) {
            return mapArithmeticExpressionContext((BooleanExpressionParser.ArithmeticExpressionContext) ctx);
        } else if (ctx instanceof BooleanExpressionParser.InExpressionContext) {
            return mapInExpressionContext((BooleanExpressionParser.InExpressionContext) ctx);
        } else if (ctx instanceof BooleanExpressionParser.ArithmeticFunctionExpressionContext) {
            return mapArithmeticFunctionExpressionContext((BooleanExpressionParser.ArithmeticFunctionExpressionContext) ctx);
        } else if (ctx instanceof BooleanExpressionParser.ComparatorExpressionContext) {
            return mapComparatorExpressionContext((BooleanExpressionParser.ComparatorExpressionContext) ctx);
        } else if (ctx instanceof BooleanExpressionParser.ToExpressionContext) {
            return mapToExpressionContext((BooleanExpressionParser.ToExpressionContext) ctx);
        } else if (ctx instanceof BooleanExpressionParser.TypesExpressionContext && ((BooleanExpressionParser.TypesExpressionContext) ctx).start.getType() == BooleanExpressionLexer.FIELD) {
            return mapTypesExpressionContextField((BooleanExpressionParser.TypesExpressionContext) ctx);
        } else if (ctx instanceof BooleanExpressionParser.TypesExpressionContext) {
            return mapTypesExpressionContext((BooleanExpressionParser.TypesExpressionContext) ctx);
        } else {
            throw new InvalidExpressionException(String.format("Array does not support this expression %s", ctx.getText()));
        }
    }

    private FieldNode mapTypesExpressionContextField(BooleanExpressionParser.TypesExpressionContext ctx) {
        final String value = StringUtils.isBlank(ctx.getText()) ? defaultField : ctx.getText();
        return new FieldNode(value);
    }

    private Node mapTypesExpressionContext(BooleanExpressionParser.TypesExpressionContext ctx) {
        if (ctx.start.getType() == BooleanExpressionLexer.FIELD) {
            return mapTypesExpressionContextField(ctx);
        }
        if (StringUtils.isBlank(ctx.getText())) {
            return mapTypesExpressionContextField(ctx);
        }
        final DataType dataType = getDataType(ctx.start);
        final Object value = ValueUtils.convertValue(ctx.start.getText(), dataType);
        return new UnaryNode(dataType, value);
    }

    private ArithmeticFunctionNode mapArithmeticFunctionExpressionContext(BooleanExpressionParser.ArithmeticFunctionExpressionContext ctx) {
        if (ctx.data.exception != null) {
            throw new InvalidExpressionException(String.format("Error parsing expression for the string %s", ctx.getText()));
        }
        final FunctionType functionType = FunctionType.getArrayFunctionFromSymbol(ctx.left.getText()).orElseThrow(() -> {
            return new InvalidExpressionException(String.format("Error parsing expression for the string %s", ctx.getText()));
        });
        final List<Node> items = getArrayElements(ctx.data.children);
        return new ArithmeticFunctionNode(functionType, items);
    }

    private ComparisonNode mapComparatorExpressionContext(BooleanExpressionParser.ComparatorExpressionContext ctx) {
        final Operator operator = Operator.getOperatorFromSymbol(ctx.op.getText()).orElse(Operator.EQUALS);

        if (!currentNodes.isEmpty() && ((ctx.right instanceof BooleanExpressionParser.ParentExpressionContext || ctx.left instanceof BooleanExpressionParser.ParentExpressionContext) || !(currentNodes.peek() instanceof ComparisonNode || currentNodes.peek() instanceof BooleanNode))) {
            if (ctx.left instanceof BooleanExpressionParser.TypesExpressionContext) {
                final DataType dataType = getDataType(ctx.left.getStart());
                final Node value = mapContextToNode(ctx.left);
                return new ComparisonNode(value, currentNodes.pop(), operator, dataType);
            } else if (ctx.right instanceof BooleanExpressionParser.TypesExpressionContext) {
                final DataType dataType = getDataType(ctx.right.getStart());
                final Node value = mapContextToNode(ctx.right);
                return new ComparisonNode(currentNodes.pop(), value, operator, dataType);
            }
        }

        final DataType dataType = getDataType(ctx.right.getStart());
        final Node value = mapContextToNode(ctx.right);
        return new ComparisonNode(mapContextToNode(ctx.left), value, operator, dataType);
    }

    private ArithmeticNode mapArithmeticExpressionContext(BooleanExpressionParser.ArithmeticExpressionContext ctx) {
        final Operator operator = Operator.getOperatorFromSymbol(ctx.op.getText()).orElse(Operator.EQUALS);
        if (ctx.left instanceof BooleanExpressionParser.TypesExpressionContext && ctx.right instanceof BooleanExpressionParser.TypesExpressionContext) {
            final Node left = mapTypesExpressionContext((BooleanExpressionParser.TypesExpressionContext) ctx.left);
            final Node right = mapTypesExpressionContext((BooleanExpressionParser.TypesExpressionContext) ctx.right);
            return ArithmeticNode.builder().left(left).right(right).operator(operator).build();
        } else if (ctx.left instanceof BooleanExpressionParser.TypesExpressionContext) {
            final Node left = mapTypesExpressionContext((BooleanExpressionParser.TypesExpressionContext) ctx.left);
            final Node right = currentNodes.pop();
            return ArithmeticNode.builder().left(left).right(right).operator(operator).build();
        } else if (ctx.right instanceof BooleanExpressionParser.TypesExpressionContext) {
            final Node right = mapTypesExpressionContext((BooleanExpressionParser.TypesExpressionContext) ctx.right);
            final Node left = currentNodes.pop();
            return ArithmeticNode.builder().left(left).right(right).operator(operator).build();
        } else {
            if (currentNodes.size() < 2) {
                throw new InvalidExpressionException(String.format("Error parsing expression for the string %s", ctx.getText()));
            }
            final Node right = currentNodes.pop();
            final Node left = currentNodes.pop();
            return ArithmeticNode.builder().left(left).right(right).operator(operator).build();
        }
    }

    private Node mapInExpressionContext(BooleanExpressionParser.InExpressionContext ctx) {
        validateField(ctx.field, ctx.getText());
        final String field = getField(ctx.field.getText());
        final List<Node> items = getArrayElements(ctx.data.children);
        final InNode inNode = new InNode(field, items);
        if (Objects.isNull(ctx.not)) {
            return inNode;
        } else {
            return new BooleanNode(inNode, null, LogicalOperationType.NOT);
        }
    }

    private NumericRangeNode mapToExpressionContext(BooleanExpressionParser.ToExpressionContext ctx) {
        validateField(ctx.field, ctx.getText());
        final String field = getField(ctx.field.getText());
        final DataType lowerDataType = getDataType(ctx.lower.start);
        final Object lowerValue = ValueUtils.convertValue(ctx.lower.start.getText(), lowerDataType);
        final DataType upperDataType = getDataType(ctx.upper.start);
        final Object upperValue = ValueUtils.convertValue(ctx.upper.getText(), upperDataType);
        return new NumericRangeNode(field, lowerValue, upperValue, lowerDataType, upperDataType);
    }

    private void validateField(final Token token, final String text) {
        if (Objects.isNull(token) || (StringUtils.isBlank(token.getText()) && StringUtils.isBlank(this.defaultField))) {
            throw new InvalidExpressionException(String.format("Error parsing expression (missing field) for the string %s", text));
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
        if (this.node == null && !this.currentNodes.isEmpty()) {
            this.node = currentNodes.pop();
        }
        if (this.node == null && tokenCount == 1 && lastToken instanceof CommonToken) {
            this.node = lastToken.getType() == BooleanExpressionLexer.FIELD ? FieldNode.builder().field(lastToken.getText()).build() : UnaryNode
                    .builder().dataType(DataType.STRING).value(ValueUtils.convertValue(lastToken.getText(), DataType.STRING).toString()).build();
        }
        if (this.node == null) {
            throw new InvalidExpressionException(String.format("Error parsing expression for the string %s", ctx.getText()));
        }
        super.exitParse(ctx);
    }

    @Override
    public void exitNotExpression(BooleanExpressionParser.NotExpressionContext ctx) {
        if (currentNodes.isEmpty()) {
            if (lastToken == null) {
                throw new InvalidExpressionException(String.format("Error parsing not expression for the string %s", ctx.getText()));
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
            throw new InvalidExpressionException(String.format("Error parsing binary expression for the string %s", ctx.getText()));
        }
        final Node firstNode = currentNodes.pop();
        final Node secondNode = currentNodes.pop();
        final LogicalOperationType operator = getLogicalOperator(ctx.op.getStart());
        final BooleanNode booleanToken = new BooleanNode(secondNode, firstNode, operator);
        currentNodes.add(booleanToken);
        super.enterBinaryExpression(ctx);
    }
}

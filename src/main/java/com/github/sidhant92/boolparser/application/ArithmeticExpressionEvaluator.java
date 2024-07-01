package com.github.sidhant92.boolparser.application;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import com.github.sidhant92.boolparser.constant.ContainerDataType;
import com.github.sidhant92.boolparser.constant.DataType;
import com.github.sidhant92.boolparser.constant.Operator;
import com.github.sidhant92.boolparser.domain.EvaluatedNode;
import com.github.sidhant92.boolparser.domain.FieldNode;
import com.github.sidhant92.boolparser.domain.arithmetic.UnaryNode;
import com.github.sidhant92.boolparser.domain.arithmetic.ArithmeticNode;
import com.github.sidhant92.boolparser.domain.logical.Node;
import com.github.sidhant92.boolparser.domain.arithmetic.ArithmeticFunctionNode;
import com.github.sidhant92.boolparser.exception.DataNotFoundException;
import com.github.sidhant92.boolparser.exception.UnsupportedToken;
import com.github.sidhant92.boolparser.function.FunctionEvaluatorService;
import com.github.sidhant92.boolparser.operator.OperatorService;
import com.github.sidhant92.boolparser.parser.BoolExpressionParser;
import com.github.sidhant92.boolparser.util.ValueUtils;
import io.vavr.control.Try;

/**
 * @author sidhant.aggarwal
 * @since 15/03/2024
 */
public class ArithmeticExpressionEvaluator {
    private final BoolExpressionParser boolExpressionParser;

    private final OperatorService operatorService;

    private final FunctionEvaluatorService functionEvaluatorService;

    public ArithmeticExpressionEvaluator(final BoolExpressionParser boolExpressionParser) {
        this.boolExpressionParser = boolExpressionParser;
        operatorService = new OperatorService();
        functionEvaluatorService = new FunctionEvaluatorService();
    }

    public Try<Object> evaluate(final String expression, final Map<String, Object> data) {
        final Try<Node> tokenOptional = boolExpressionParser.parseExpression(expression, null);
        return tokenOptional.map(node -> evaluateToken(node, data));
    }

    protected Object evaluate(final Node node, final Map<String, Object> data) {
        return evaluateToken(node, data);
    }

    private Object evaluateToken(final Node node, final Map<String, Object> data) {
        switch (node.getTokenType()) {
            case ARITHMETIC:
                return evaluateArithmeticToken((ArithmeticNode) node, data);
            case ARITHMETIC_FUNCTION:
                return evaluateArithmeticFunctionToken((ArithmeticFunctionNode) node, data);
            case UNARY:
                return evaluateUnaryToken((UnaryNode) node, data);
            case FIELD:
                return evaluateFieldToken((FieldNode) node, data);
            default:
                throw new UnsupportedToken(node.getTokenType().name());
        }
    }

    private Object evaluateFieldToken(final FieldNode fieldNode, final Map<String, Object> data) {
        final Optional<Object> value = ValueUtils.getValueFromMap(fieldNode.getField(), data);
        if (!value.isPresent()) {
            throw new DataNotFoundException(fieldNode.getField());
        }
        return value.get();
    }

    private Object evaluateUnaryToken(final UnaryNode unaryNode, final Map<String, Object> data) {
        return unaryNode.getValue();
    }

    private Object evaluateArithmeticFunctionToken(final ArithmeticFunctionNode arithmeticFunctionNode, final Map<String, Object> data) {
        final List<Object> resolvedValues = arithmeticFunctionNode.getItems()
                .stream()
                .map(item -> evaluate(item, data))
                .collect(Collectors.toList());
        final List<EvaluatedNode> flattenedValues = ValueUtils.mapToEvaluatedNodes(resolvedValues);
        return functionEvaluatorService.evaluateArithmeticFunction(arithmeticFunctionNode.getFunctionType(), flattenedValues);
    }

    private Object evaluateArithmeticToken(final ArithmeticNode arithmeticNode, final Map<String, Object> data) {
        final Object leftValue = evaluateToken(arithmeticNode.getLeft(), data);
        if (arithmeticNode.getOperator().equals(Operator.UNARY)) {
            if (leftValue instanceof EvaluatedNode) {
                final EvaluatedNode left = (EvaluatedNode) leftValue;
                return operatorService.evaluateArithmeticOperator(left.getValue(), left.getDataType(), null, null, arithmeticNode.getOperator(),
                                                                  ContainerDataType.PRIMITIVE);
            } else {
                final DataType leftDataType = ValueUtils.getDataType(leftValue);
                return operatorService.evaluateArithmeticOperator(leftValue, leftDataType, null, null, arithmeticNode.getOperator(),
                                                                  ContainerDataType.PRIMITIVE);
            }
        }
        final Object rightValue = evaluateToken(arithmeticNode.getRight(), data);
        if (leftValue instanceof EvaluatedNode && rightValue instanceof EvaluatedNode) {
            final EvaluatedNode left = (EvaluatedNode) leftValue;
            final EvaluatedNode right = (EvaluatedNode) rightValue;
            return operatorService.evaluateArithmeticOperator(left.getValue(), left.getDataType(), right.getValue(), right.getDataType(),
                                                              arithmeticNode.getOperator(), ContainerDataType.PRIMITIVE);
        } else if (leftValue instanceof EvaluatedNode) {
            final EvaluatedNode left = (EvaluatedNode) leftValue;
            final DataType rightDataType = ValueUtils.getDataType(rightValue);
            return operatorService.evaluateArithmeticOperator(left.getValue(), left.getDataType(), rightValue, rightDataType,
                                                              arithmeticNode.getOperator(), ContainerDataType.PRIMITIVE);
        } else if (rightValue instanceof EvaluatedNode) {
            final EvaluatedNode right = (EvaluatedNode) rightValue;
            final DataType leftDataType = ValueUtils.getDataType(leftValue);
            return operatorService.evaluateArithmeticOperator(leftValue, leftDataType, right.getValue(), right.getDataType(),
                                                              arithmeticNode.getOperator(), ContainerDataType.PRIMITIVE);
        } else {
            final DataType leftDataType = ValueUtils.getDataType(leftValue);
            final DataType rightDataType = ValueUtils.getDataType(rightValue);
            return operatorService.evaluateArithmeticOperator(leftValue, leftDataType, rightValue, rightDataType, arithmeticNode.getOperator(),
                                                              ContainerDataType.PRIMITIVE);
        }
    }
}

package com.github.sidhant92.boolparser.application;

import java.util.Map;
import java.util.Optional;
import org.apache.commons.lang3.tuple.Pair;
import com.github.sidhant92.boolparser.constant.ContainerDataType;
import com.github.sidhant92.boolparser.constant.DataType;
import com.github.sidhant92.boolparser.constant.Operator;
import com.github.sidhant92.boolparser.domain.StringNode;
import com.github.sidhant92.boolparser.domain.arithmetic.ArithmeticLeafNode;
import com.github.sidhant92.boolparser.domain.arithmetic.ArithmeticNode;
import com.github.sidhant92.boolparser.domain.arithmetic.ArithmeticUnaryNode;
import com.github.sidhant92.boolparser.domain.Node;
import com.github.sidhant92.boolparser.exception.UnsupportedToken;
import com.github.sidhant92.boolparser.operator.OperatorService;
import com.github.sidhant92.boolparser.parser.BoolExpressionParser;
import com.github.sidhant92.boolparser.util.ValueUtils;
import io.vavr.control.Try;
import lombok.extern.slf4j.Slf4j;

/**
 * @author sidhant.aggarwal
 * @since 15/03/2024
 */
@Slf4j
public class ArithmeticExpressionEvaluator {
    private final BoolExpressionParser boolExpressionParser;

    private final OperatorService operatorService;

    public ArithmeticExpressionEvaluator(final BoolExpressionParser boolExpressionParser) {
        this.boolExpressionParser = boolExpressionParser;
        operatorService = new OperatorService();
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
            case ARITHMETIC_LEAF:
                return evaluateArithmeticLeafToken((ArithmeticLeafNode) node, data);
            case ARITHMETIC_UNARY:
                return evaluateUnaryArithmeticToken((ArithmeticUnaryNode) node, data);
            case STRING:
                return evaluateStringToken((StringNode) node, data);
            default:
                log.error("unsupported token {}", node.getTokenType());
                throw new UnsupportedToken();
        }
    }

    private Object evaluateStringToken(final StringNode stringNode, final Map<String, Object> data) {
        return ValueUtils.getValueFromMap(stringNode.getField(), data).orElse(stringNode.getField());
    }

    private Pair<Object, DataType> evaluateArithmeticLeafToken(final ArithmeticLeafNode arithmeticLeafNode, final Map<String, Object> data) {
        final Optional<Object> fetchedValue = ValueUtils.getValueFromMap(arithmeticLeafNode.getOperand().toString(), data);
        return fetchedValue
                .map(o -> Pair.of(o, ValueUtils.getDataType(o)))
                .orElseGet(() -> Pair.of(arithmeticLeafNode.getOperand(), arithmeticLeafNode.getDataType()));
    }

    private Object evaluateUnaryArithmeticToken(final ArithmeticUnaryNode arithmeticUnaryNode, final Map<String, Object> data) {
        final Object resolvedValue = evaluateToken(arithmeticUnaryNode.getOperand(), data);
        if (resolvedValue instanceof Pair) {
            final Pair<Object, DataType> pair = (Pair<Object, DataType>) resolvedValue;
            return operatorService.evaluateArithmeticOperator(pair.getLeft(), pair.getRight(), null, null, Operator.UNARY,
                                                              ContainerDataType.PRIMITIVE);
        }
        final DataType dataType = ValueUtils.getDataType(resolvedValue);
        return operatorService.evaluateArithmeticOperator(resolvedValue, dataType, null, null, Operator.UNARY, ContainerDataType.PRIMITIVE);
    }

    private Object evaluateArithmeticToken(final ArithmeticNode arithmeticNode, final Map<String, Object> data) {
        final Object leftValue = evaluateToken(arithmeticNode.getLeft(), data);
        final Object rightValue = evaluateToken(arithmeticNode.getRight(), data);
        if (leftValue instanceof Pair && rightValue instanceof Pair) {
            final Pair<Object, DataType> leftPair = (Pair<Object, DataType>) leftValue;
            final Pair<Object, DataType> rightPair = (Pair<Object, DataType>) rightValue;
            return operatorService.evaluateArithmeticOperator(leftPair.getLeft(), leftPair.getRight(), rightPair.getLeft(), rightPair.getRight(),
                                                              arithmeticNode.getOperator(), ContainerDataType.PRIMITIVE);
        } else if (leftValue instanceof Pair) {
            final Pair<Object, DataType> leftPair = (Pair<Object, DataType>) leftValue;
            final DataType rightDataType = ValueUtils.getDataType(rightValue);
            return operatorService.evaluateArithmeticOperator(leftPair.getLeft(), leftPair.getRight(), rightValue, rightDataType,
                                                              arithmeticNode.getOperator(), ContainerDataType.PRIMITIVE);
        } else if (rightValue instanceof Pair) {
            final Pair<Object, DataType> rightPair = (Pair<Object, DataType>) rightValue;
            final DataType leftDataType = ValueUtils.getDataType(leftValue);
            return operatorService.evaluateArithmeticOperator(leftValue, leftDataType, rightPair.getLeft(), rightPair.getRight(),
                                                              arithmeticNode.getOperator(), ContainerDataType.PRIMITIVE);
        } else {
            final DataType leftDataType = ValueUtils.getDataType(leftValue);
            final DataType rightDataType = ValueUtils.getDataType(rightValue);
            return operatorService.evaluateArithmeticOperator(leftValue, leftDataType, rightValue, rightDataType, arithmeticNode.getOperator(),
                                                              ContainerDataType.PRIMITIVE);
        }
    }
}

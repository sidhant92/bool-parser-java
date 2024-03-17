package com.github.sidhant92.boolparser.application;

import java.util.Map;
import org.apache.commons.lang3.tuple.Pair;
import com.github.sidhant92.boolparser.constant.ContainerDataType;
import com.github.sidhant92.boolparser.constant.DataType;
import com.github.sidhant92.boolparser.constant.Operator;
import com.github.sidhant92.boolparser.domain.ArrayNode;
import com.github.sidhant92.boolparser.domain.BooleanNode;
import com.github.sidhant92.boolparser.domain.InNode;
import com.github.sidhant92.boolparser.domain.NumericRangeNode;
import com.github.sidhant92.boolparser.domain.ComparisonNode;
import com.github.sidhant92.boolparser.domain.Node;
import com.github.sidhant92.boolparser.domain.UnaryNode;
import com.github.sidhant92.boolparser.domain.arithmetic.ArithmeticBaseNode;
import com.github.sidhant92.boolparser.exception.DataNotFoundException;
import com.github.sidhant92.boolparser.exception.HeterogeneousArrayException;
import com.github.sidhant92.boolparser.exception.InvalidUnaryOperand;
import com.github.sidhant92.boolparser.operator.OperatorService;
import com.github.sidhant92.boolparser.parser.BoolExpressionParser;
import com.github.sidhant92.boolparser.util.ValueUtils;
import io.vavr.control.Try;
import lombok.extern.slf4j.Slf4j;

/**
 * @author sidhant.aggarwal
 * @since 07/02/2023
 */
@Slf4j
public class BooleanExpressionEvaluator {
    private final BoolExpressionParser boolExpressionParser;

    private final OperatorService operatorService;

    private final ArithmeticExpressionEvaluator arithmeticExpressionEvaluator;

    public BooleanExpressionEvaluator(final BoolExpressionParser boolExpressionParser) {
        this.boolExpressionParser = boolExpressionParser;
        operatorService = new OperatorService();
        arithmeticExpressionEvaluator = new ArithmeticExpressionEvaluator(boolExpressionParser);
    }

    public Try<Boolean> evaluate(final String expression, final Map<String, Object> data, final String defaultField) {
        final Try<Node> tokenOptional = boolExpressionParser.parseExpression(expression, defaultField);
        return tokenOptional.map(node -> evaluateToken(node, data));
    }

    public Try<Boolean> evaluate(final String expression, final Map<String, Object> data) {
        return evaluate(expression, data, null);
    }

    private boolean evaluateToken(final Node node, final Map<String, Object> data) {
        switch (node.getTokenType()) {
            case COMPARISON:
                return evaluateComparisonToken((ComparisonNode) node, data);
            case NUMERIC_RANGE:
                return evaluateNumericRangeToken((NumericRangeNode) node, data);
            case IN:
                return evaluateInToken((InNode) node, data);
            case ARRAY:
                return evaluateArrayToken((ArrayNode) node, data);
            case UNARY:
                return evaluateUnaryToken((UnaryNode) node, data);
            case BOOLEAN:
                return evaluateBooleanNode((BooleanNode) node, data);
            default:
                return false;
        }
    }

    private boolean evaluateComparisonToken(final ComparisonNode comparisonToken, final Map<String, Object> data) {
        final Object fieldData = ValueUtils.getValueFromMap(comparisonToken.getField(), data).orElseThrow(DataNotFoundException::new);
        final Object value = comparisonToken.getValue() instanceof ArithmeticBaseNode ? arithmeticExpressionEvaluator.evaluate(
                (Node) comparisonToken.getValue(), data) : comparisonToken.getValue();
        return operatorService.evaluateLogicalOperator(comparisonToken.getOperator(), ContainerDataType.PRIMITIVE, comparisonToken.getDataType(),
                                                       fieldData, value);
    }

    private boolean evaluateNumericRangeToken(final NumericRangeNode numericRangeToken, final Map<String, Object> data) {
        final Object fieldData = ValueUtils.getValueFromMap(numericRangeToken.getField(), data).orElseThrow(DataNotFoundException::new);
        return operatorService.evaluateLogicalOperator(Operator.GREATER_THAN_EQUAL, ContainerDataType.PRIMITIVE, numericRangeToken.getFromDataType(),
                                                       fieldData, numericRangeToken.getFromValue()) && operatorService.evaluateLogicalOperator(
                Operator.LESS_THAN_EQUAL, ContainerDataType.PRIMITIVE, numericRangeToken.getToDataType(), fieldData, numericRangeToken.getToValue());
    }

    private boolean evaluateInToken(final InNode inToken, final Map<String, Object> data) {
        final Object fieldData = ValueUtils.getValueFromMap(inToken.getField(), data).orElseThrow(DataNotFoundException::new);
        final DataType dataType = inToken.getItems().get(0).getLeft();
        final Object[] values = inToken.getItems()
                .stream()
                .map(Pair::getRight).toArray();
        return operatorService.evaluateLogicalOperator(Operator.IN, ContainerDataType.PRIMITIVE, dataType, fieldData, values);
    }

    private boolean evaluateArrayToken(final ArrayNode arrayNode, final Map<String, Object> data) {
        final Object fieldData = ValueUtils.getValueFromMap(arrayNode.getField(), data).orElseThrow(DataNotFoundException::new);
        if (arrayNode.getItems()
                .stream()
                .map(Pair::getLeft).distinct().count() > 1) {
            throw new HeterogeneousArrayException();
        }
        final DataType dataType = arrayNode.getItems().get(0).getLeft();
        final Object[] values = arrayNode.getItems()
                .stream()
                .map(Pair::getRight).toArray();
        return operatorService.evaluateLogicalOperator(arrayNode.getOperator(), ContainerDataType.LIST, dataType, fieldData, values);
    }

    private boolean evaluateUnaryToken(final UnaryNode unaryToken, final Map<String, Object> data) {
        if (unaryToken.getDataType().equals(DataType.BOOLEAN)) {
            return (boolean) unaryToken.getValue();
        }
        final Object fieldData = ValueUtils.getValueFromMap(unaryToken.getValue().toString(), data).orElseThrow(DataNotFoundException::new);
        if (!(fieldData instanceof Boolean)) {
            throw new InvalidUnaryOperand();
        }
        return (boolean) fieldData;
    }

    private boolean evaluateBooleanNode(final BooleanNode booleanToken, final Map<String, Object> data) {
        switch (booleanToken.getOperator()) {
            case AND:
                return evaluateToken(booleanToken.getLeft(), data) && evaluateToken(booleanToken.getRight(), data);
            case OR:
                return evaluateToken(booleanToken.getLeft(), data) || evaluateToken(booleanToken.getRight(), data);
            default:
                return !evaluateToken(booleanToken.getLeft(), data);
        }
    }
}

package com.github.sidhant92.boolparser.application;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.apache.commons.lang3.tuple.Pair;
import com.github.sidhant92.boolparser.constant.ContainerDataType;
import com.github.sidhant92.boolparser.constant.DataType;
import com.github.sidhant92.boolparser.constant.Operator;
import com.github.sidhant92.boolparser.domain.FieldNode;
import com.github.sidhant92.boolparser.domain.logical.ArrayNode;
import com.github.sidhant92.boolparser.domain.logical.BooleanNode;
import com.github.sidhant92.boolparser.domain.EvaluatedNode;
import com.github.sidhant92.boolparser.domain.logical.InNode;
import com.github.sidhant92.boolparser.domain.logical.NumericRangeNode;
import com.github.sidhant92.boolparser.domain.logical.ComparisonNode;
import com.github.sidhant92.boolparser.domain.logical.Node;
import com.github.sidhant92.boolparser.domain.arithmetic.UnaryNode;
import com.github.sidhant92.boolparser.domain.arithmetic.ArithmeticBaseNode;
import com.github.sidhant92.boolparser.exception.DataNotFoundException;
import com.github.sidhant92.boolparser.exception.HeterogeneousArrayException;
import com.github.sidhant92.boolparser.exception.InvalidUnaryOperand;
import com.github.sidhant92.boolparser.operator.OperatorService;
import com.github.sidhant92.boolparser.parser.BoolExpressionParser;
import com.github.sidhant92.boolparser.util.ValueUtils;
import io.vavr.control.Try;

/**
 * @author sidhant.aggarwal
 * @since 07/02/2023
 */
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
        final Optional<Object> fieldDataOptional = ValueUtils.getValueFromMap(comparisonToken.getField(), data);

        final Object fieldData = comparisonToken.isNullCheck() ? fieldDataOptional.orElse("null") : fieldDataOptional.orElseThrow(
                () -> new DataNotFoundException(comparisonToken.getField()));
        final Object value = comparisonToken.isNullCheck() ? "null" : comparisonToken.getValue() instanceof ArithmeticBaseNode ? arithmeticExpressionEvaluator.evaluate(
                comparisonToken.getValue(), data) : comparisonToken.getValue();
        final DataType dataType = ValueUtils.getDataType(value);
        final DataType fieldDataType = ValueUtils.getDataType(fieldData);
        return operatorService.evaluateLogicalOperator(comparisonToken.getOperator(), ContainerDataType.PRIMITIVE, fieldData, fieldDataType,
                                                       Collections.singletonList(Pair.of(value, dataType)));
    }

    private boolean evaluateNumericRangeToken(final NumericRangeNode numericRangeToken, final Map<String, Object> data) {
        final Object fieldData = ValueUtils.getValueFromMap(numericRangeToken.getField(), data)
                                           .orElseThrow(() -> new DataNotFoundException(numericRangeToken.getField()));
        final DataType fieldDataType = ValueUtils.getDataType(fieldData);
        final List<Pair<Object, DataType>> fromValues = Collections.singletonList(
                Pair.of(numericRangeToken.getFromValue(), numericRangeToken.getFromDataType()));
        final List<Pair<Object, DataType>> toValues = Collections.singletonList(
                Pair.of(numericRangeToken.getToValue(), numericRangeToken.getToDataType()));
        final boolean leftResult = operatorService.evaluateLogicalOperator(Operator.GREATER_THAN_EQUAL, ContainerDataType.PRIMITIVE, fieldData,
                                                                           fieldDataType, fromValues);
        final boolean rightResult = operatorService.evaluateLogicalOperator(Operator.LESS_THAN_EQUAL, ContainerDataType.PRIMITIVE, fieldData,
                                                                            fieldDataType, toValues);
        return leftResult && rightResult;
    }

    private boolean evaluateInToken(final InNode inToken, final Map<String, Object> data) {
        final Object fieldData = ValueUtils.getValueFromMap(inToken.getField(), data)
                                           .orElseThrow(() -> new DataNotFoundException(inToken.getField()));
        final List<EvaluatedNode> items = resolveArrayElements(inToken.getItems(), data);
        final DataType dataType = ValueUtils.getDataType(fieldData);
        final List<Pair<Object, DataType>> values = items
                .stream()
                .map(item -> Pair.of(item.getValue(), item.getDataType()))
                .collect(Collectors.toList());
        return operatorService.evaluateLogicalOperator(Operator.IN, ContainerDataType.PRIMITIVE, fieldData, dataType, values);
    }

    private List<EvaluatedNode> resolveArrayElements(final List<Node> items, final Map<String, Object> data) {
        final List<Object> resolvedValues = items
                .stream()
                .map(item -> {
                    if (item instanceof ArithmeticBaseNode) {
                        return arithmeticExpressionEvaluator.evaluate(item, data);
                    }
                    return evaluateToken(item, data);
                })
                .collect(Collectors.toList());
        return ValueUtils.mapToEvaluatedNodes(resolvedValues);
    }

    private boolean evaluateArrayToken(final ArrayNode arrayNode, final Map<String, Object> data) {
        final Object fieldData = ValueUtils.getValueFromMap(arrayNode.getField(), data)
                                           .orElseThrow(() -> new DataNotFoundException(arrayNode.getField()));
        final DataType fieldDataType = ValueUtils.getDataType(fieldData);
        final List<EvaluatedNode> items = resolveArrayElements(arrayNode.getItems(), data);
        if (items
                .stream()
                .map(EvaluatedNode::getDataType).distinct().count() > 1) {
            throw new HeterogeneousArrayException();
        }
        final List<Pair<Object, DataType>> values = items
                .stream()
                .map(item -> Pair.of(item.getValue(), item.getDataType()))
                .collect(Collectors.toList());
        return operatorService.evaluateLogicalOperator(arrayNode.getOperator(), ContainerDataType.LIST, fieldData, fieldDataType, values);
    }

    private boolean evaluateUnaryToken(final UnaryNode unaryToken, final Map<String, Object> data) {
        if (unaryToken.getDataType().equals(DataType.BOOLEAN)) {
            return (boolean) unaryToken.getValue();
        }
        final Object fieldData = ValueUtils.getValueFromMap(unaryToken.getValue().toString(), data)
                                           .orElseThrow(() -> new DataNotFoundException(unaryToken.getValue().toString()));
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

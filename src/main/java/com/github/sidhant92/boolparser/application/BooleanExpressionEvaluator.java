package com.github.sidhant92.boolparser.application;

import java.util.Map;
import org.apache.commons.lang3.tuple.Pair;
import com.github.sidhant92.boolparser.constant.ContainerDataType;
import com.github.sidhant92.boolparser.constant.DataType;
import com.github.sidhant92.boolparser.constant.Operator;
import com.github.sidhant92.boolparser.domain.BooleanNode;
import com.github.sidhant92.boolparser.domain.InNode;
import com.github.sidhant92.boolparser.domain.NumericRangeNode;
import com.github.sidhant92.boolparser.domain.ComparisonNode;
import com.github.sidhant92.boolparser.domain.Node;
import com.github.sidhant92.boolparser.domain.UnaryNode;
import com.github.sidhant92.boolparser.exception.InvalidUnaryOperand;
import com.github.sidhant92.boolparser.operator.OperatorService;
import com.github.sidhant92.boolparser.parser.BoolExpressionParser;
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

    public BooleanExpressionEvaluator(final BoolExpressionParser boolExpressionParser) {
        this.boolExpressionParser = boolExpressionParser;
        operatorService = new OperatorService();
    }

    public Try<Boolean> evaluate(final String expression, final Map<String, Object> data) {
        final Try<Node> tokenOptional = boolExpressionParser.parseExpression(expression);
        return tokenOptional.map(node -> evaluateToken(node, data));
    }

    private boolean evaluateToken(final Node node, final Map<String, Object> data) {
        switch (node.getTokenType()) {
            case COMPARISON:
                return evaluateComparisonToken((ComparisonNode) node, data);
            case NUMERIC_RANGE:
                return evaluateNumericRangeToken((NumericRangeNode) node, data);
            case IN:
                return evaluateInToken((InNode) node, data);
            case UNARY:
                return evaluateUnaryToken((UnaryNode) node, data);
            case BOOLEAN:
                return evaluateBooleanNode((BooleanNode) node, data);
            default:
                return false;
        }
    }

    private boolean evaluateComparisonToken(final ComparisonNode comparisonToken, final Map<String, Object> data) {
        if (checkFieldDataMissing(comparisonToken.getField(), data)) {
            return false;
        }
        final Object fieldData = data.get(comparisonToken.getField());
        return operatorService.evaluate(comparisonToken.getOperator(), ContainerDataType.primitive, comparisonToken.getDataType(), fieldData,
                                        comparisonToken.getValue());
    }

    private boolean evaluateNumericRangeToken(final NumericRangeNode numericRangeToken, final Map<String, Object> data) {
        if (checkFieldDataMissing(numericRangeToken.getField(), data)) {
            return false;
        }
        final Object fieldData = data.get(numericRangeToken.getField());
        return operatorService.evaluate(Operator.GREATER_THAN_EQUAL, ContainerDataType.primitive, numericRangeToken.getFromDataType(), fieldData,
                                        numericRangeToken.getFromValue()) && operatorService.evaluate(Operator.LESS_THAN_EQUAL,
                                                                                                      ContainerDataType.primitive,
                                                                                                      numericRangeToken.getToDataType(), fieldData,
                                                                                                      numericRangeToken.getToValue());
    }

    private boolean evaluateInToken(final InNode inToken, final Map<String, Object> data) {
        if (checkFieldDataMissing(inToken.getField(), data)) {
            return false;
        }
        final Object fieldData = data.get(inToken.getField());
        final DataType dataType = inToken.getItems().get(0).getLeft();
        final Object[] values = inToken.getItems()
                .stream()
                .map(Pair::getRight).toArray();
        return operatorService.evaluate(Operator.IN, ContainerDataType.primitive, dataType, fieldData, values);
    }

    private boolean evaluateUnaryToken(final UnaryNode unaryToken, final Map<String, Object> data) {
        if (unaryToken.getDataType().equals(DataType.BOOLEAN)) {
            return (boolean) unaryToken.getValue();
        }
        if (checkFieldDataMissing(unaryToken.getValue().toString(), data)) {
            return false;
        }
        final Object fieldValue = data.get(unaryToken.getValue().toString());
        if (!(fieldValue instanceof Boolean)) {
            throw new InvalidUnaryOperand();
        }
        return (boolean) fieldValue;
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

    private boolean checkFieldDataMissing(final String field, final Map<String, Object> data) {
        if (!data.containsKey(field)) {
            log.error("Error data not found for field {}", field);
            return true;
        }
        return false;
    }
}

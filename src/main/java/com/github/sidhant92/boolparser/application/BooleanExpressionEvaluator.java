package com.github.sidhant92.boolparser.application;

import java.util.Map;
import org.apache.commons.lang3.tuple.Pair;
import com.github.sidhant92.boolparser.constant.ContainerDataType;
import com.github.sidhant92.boolparser.constant.DataType;
import com.github.sidhant92.boolparser.constant.Operator;
import com.github.sidhant92.boolparser.domain.BooleanToken;
import com.github.sidhant92.boolparser.domain.InToken;
import com.github.sidhant92.boolparser.domain.NumericRangeToken;
import com.github.sidhant92.boolparser.domain.NumericToken;
import com.github.sidhant92.boolparser.domain.StringToken;
import com.github.sidhant92.boolparser.domain.Token;
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
        final Try<Token> tokenOptional = boolExpressionParser.parseExpression(expression);
        return tokenOptional.map(node -> evaluateToken(node, data));
    }

    private boolean evaluateToken(final Token token, final Map<String, Object> data) {
        switch (token.getTokenType()) {
            case STRING:
                return evaluateStringToken((StringToken) token, data);
            case NUMERIC:
                return evaluateNumericToken((NumericToken) token, data);
            case NUMERIC_RANGE:
                return evaluateNumericRangeToken((NumericRangeToken) token, data);
            case IN:
                return evaluateInToken((InToken) token, data);
            case BOOLEAN:
                return evaluateBooleanNode((BooleanToken) token, data);
            default:
                return false;
        }
    }

    private boolean evaluateStringToken(final StringToken stringToken, final Map<String, Object> data) {
        if (checkFieldDataMissing(stringToken.getField(), data)) {
            return false;
        }
        final Object fieldData = data.get(stringToken.getField());
        return operatorService.evaluate(Operator.EQUALS, ContainerDataType.primitive, DataType.STRING, fieldData, stringToken.getValue());
    }

    private boolean evaluateNumericToken(final NumericToken numericToken, final Map<String, Object> data) {
        if (checkFieldDataMissing(numericToken.getField(), data)) {
            return false;
        }
        final Object fieldData = data.get(numericToken.getField());
        return operatorService.evaluate(numericToken.getOperator(), ContainerDataType.primitive, numericToken.getDataType(), fieldData,
                                        numericToken.getValue());
    }

    private boolean evaluateNumericRangeToken(final NumericRangeToken numericRangeToken, final Map<String, Object> data) {
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

    private boolean evaluateInToken(final InToken inToken, final Map<String, Object> data) {
        if (checkFieldDataMissing(inToken.getField(), data)) {
            return false;
        }
        final Object fieldData = data.get(inToken.getField());
        final DataType dataType = inToken.getItems().get(0).getLeft();
        final Object[] values = inToken.getItems()
                .stream()
                .map(Pair::getRight)
                .toArray();
        return operatorService.evaluate(Operator.IN, ContainerDataType.primitive, dataType, fieldData, values);
    }

    private boolean evaluateBooleanNode(final BooleanToken booleanToken, final Map<String, Object> data) {
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

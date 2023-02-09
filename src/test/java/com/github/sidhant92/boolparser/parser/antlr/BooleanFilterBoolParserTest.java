package com.github.sidhant92.boolparser.parser.antlr;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import com.github.sidhant92.boolparser.constant.DataType;
import com.github.sidhant92.boolparser.constant.LogicalOperationType;
import com.github.sidhant92.boolparser.constant.TokenType;
import com.github.sidhant92.boolparser.constant.Operator;
import com.github.sidhant92.boolparser.domain.BooleanToken;
import com.github.sidhant92.boolparser.domain.InToken;
import com.github.sidhant92.boolparser.domain.Token;
import com.github.sidhant92.boolparser.domain.NumericRangeToken;
import com.github.sidhant92.boolparser.domain.NumericToken;
import com.github.sidhant92.boolparser.domain.StringToken;
import com.github.sidhant92.boolparser.domain.UnaryToken;
import com.github.sidhant92.boolparser.exception.InvalidExpressionException;
import io.vavr.control.Try;

/**
 * @author sidhant.aggarwal
 * @since 19/07/2020
 */
public class BooleanFilterBoolParserTest {
    private final BoolParser boolExpressionBoolParser = new BoolParser();

    @Test
    public void testUnaryTokenBoolean() {
        final Try<Token> nodeOptional = boolExpressionBoolParser.parseExpression("not false");
        assertTrue(nodeOptional.isSuccess());
        assertEquals(nodeOptional.get().getTokenType(), TokenType.BOOLEAN);
        assertEquals(((BooleanToken) nodeOptional.get()).getLeft().getTokenType(), TokenType.UNARY);
        assertEquals(((UnaryToken) ((BooleanToken) nodeOptional.get()).getLeft()).getDataType(), DataType.BOOLEAN);
        assertEquals(((UnaryToken) ((BooleanToken) nodeOptional.get()).getLeft()).getValue(), false);
    }

    @Test
    public void testUnaryTokenString() {
        final Try<Token> nodeOptional = boolExpressionBoolParser.parseExpression("not abc");
        assertTrue(nodeOptional.isSuccess());
        assertEquals(nodeOptional.get().getTokenType(), TokenType.BOOLEAN);
        assertEquals(((BooleanToken) nodeOptional.get()).getLeft().getTokenType(), TokenType.UNARY);
        assertEquals(((UnaryToken) ((BooleanToken) nodeOptional.get()).getLeft()).getDataType(), DataType.STRING);
        assertEquals(((UnaryToken) ((BooleanToken) nodeOptional.get()).getLeft()).getValue(), "abc");
    }

    @Test
    public void testSingleStringToken() {
        final Try<Token> nodeOptional = boolExpressionBoolParser.parseExpression("name = test");
        assertTrue(nodeOptional.isSuccess());
        assertEquals(nodeOptional.get().getTokenType().name(), TokenType.STRING.name());
        verifyStringToken((StringToken) nodeOptional.get(), "name", "test");
    }

    @Test
    public void testSingleStringTokenWithSingleQuotes() {
        final Try<Token> nodeOptional = boolExpressionBoolParser.parseExpression("name = \"te\'st\"");
        assertTrue(nodeOptional.isSuccess());
        assertEquals(nodeOptional.get().getTokenType().name(), TokenType.STRING.name());
        verifyStringToken((StringToken) nodeOptional.get(), "name", "te\'st");
    }

    @Test
    public void testSingleStringTokenWithDoubleQuotes() {
        final Try<Token> nodeOptional = boolExpressionBoolParser.parseExpression("name = 'te\"st'");
        assertTrue(nodeOptional.isSuccess());
        assertEquals(nodeOptional.get().getTokenType().name(), TokenType.STRING.name());
        verifyStringToken((StringToken) nodeOptional.get(), "name", "te\"st");
    }

    @Test
    public void testSingleStringTokenWithSpace() {
        final Try<Token> nodeOptional = boolExpressionBoolParser.parseExpression("name = \"first second\"");
        assertTrue(nodeOptional.isSuccess());
        assertEquals(nodeOptional.get().getTokenType().name(), TokenType.STRING.name());
        verifyStringToken((StringToken) nodeOptional.get(), "name", "first second");
    }

    @Test
    public void testSingleIntToken() {
        final Try<Token> nodeOptional = boolExpressionBoolParser.parseExpression("age=44");
        assertTrue(nodeOptional.isSuccess());
        assertEquals(nodeOptional.get().getTokenType().name(), TokenType.NUMERIC.name());
        verifyNumericToken((NumericToken) nodeOptional.get(), "age", 44, Operator.EQUALS);
    }

    @Test
    public void testSingleLongToken() {
        final Try<Token> nodeOptional = boolExpressionBoolParser.parseExpression("age=1611473334114");
        assertTrue(nodeOptional.isSuccess());
        assertEquals(nodeOptional.get().getTokenType().name(), TokenType.NUMERIC.name());
        verifyNumericToken((NumericToken) nodeOptional.get(), "age", 1611473334114L, Operator.EQUALS);
    }

    @Test
    public void testSingleDecimalToken() {
        final Try<Token> nodeOptional = boolExpressionBoolParser.parseExpression("age=44.34");
        assertTrue(nodeOptional.isSuccess());
        assertEquals(nodeOptional.get().getTokenType().name(), TokenType.NUMERIC.name());
        verifyNumericToken((NumericToken) nodeOptional.get(), "age", 44.34, Operator.EQUALS);
    }

    @Test
    public void testSingleIntRangeToken() {
        final Try<Token> nodeOptional = boolExpressionBoolParser.parseExpression("age 18 TO 44");
        assertTrue(nodeOptional.isSuccess());
        assertEquals(nodeOptional.get().getTokenType().name(), TokenType.NUMERIC_RANGE.name());
        verifyNumericRangeToken((NumericRangeToken) nodeOptional.get(), "age", 18, 44);
    }

    @Test
    public void testGreaterThan() {
        final Try<Token> nodeOptional = boolExpressionBoolParser.parseExpression("age > 18");
        assertTrue(nodeOptional.isSuccess());
        assertEquals(nodeOptional.get().getTokenType().name(), TokenType.NUMERIC.name());
        verifyNumericToken((NumericToken) nodeOptional.get(), "age", 18, Operator.GREATER_THAN);
    }

    @Test
    public void testSingleDecimalRangeToken() {
        final Try<Token> nodeOptional = boolExpressionBoolParser.parseExpression("age 18.4 TO 44.2");
        assertTrue(nodeOptional.isSuccess());
        assertEquals(nodeOptional.get().getTokenType().name(), TokenType.NUMERIC_RANGE.name());
        verifyNumericRangeToken((NumericRangeToken) nodeOptional.get(), "age", 18.4, 44.2);
    }

    @Test
    public void testSimpleOrCondition() {
        final Try<Token> nodeOptional = boolExpressionBoolParser.parseExpression("name = test OR age=33");
        assertTrue(nodeOptional.isSuccess());
        assertEquals(nodeOptional.get().getTokenType().name(), TokenType.BOOLEAN.name());
        final BooleanToken booleanToken = (BooleanToken) nodeOptional.get();
        assertNotNull(booleanToken.getLeft());
        assertNotNull(booleanToken.getRight());
        assertEquals(booleanToken.getOperator(), LogicalOperationType.OR);
        assertEquals(booleanToken.getLeft().getTokenType().name(), TokenType.STRING.name());
        assertEquals(booleanToken.getRight().getTokenType().name(), TokenType.NUMERIC.name());
        verifyStringToken((StringToken) booleanToken.getLeft(), "name", "test");
        verifyNumericToken((NumericToken) booleanToken.getRight(), "age", 33, Operator.EQUALS);
    }

    @Test
    public void testSimpleAndCondition() {
        final Try<Token> nodeOptional = boolExpressionBoolParser.parseExpression("name = test AND age=33");
        assertTrue(nodeOptional.isSuccess());
        assertEquals(nodeOptional.get().getTokenType().name(), TokenType.BOOLEAN.name());
        final BooleanToken booleanToken = (BooleanToken) nodeOptional.get();
        assertNotNull(booleanToken.getLeft());
        assertNotNull(booleanToken.getRight());
        assertEquals(booleanToken.getOperator(), LogicalOperationType.AND);
        assertEquals(booleanToken.getLeft().getTokenType().name(), TokenType.STRING.name());
        assertEquals(booleanToken.getRight().getTokenType().name(), TokenType.NUMERIC.name());
        verifyStringToken((StringToken) booleanToken.getLeft(), "name", "test");
        verifyNumericToken((NumericToken) booleanToken.getRight(), "age", 33, Operator.EQUALS);
    }

    @Test
    public void testSimpleNotCondition() {
        final Try<Token> nodeOptional = boolExpressionBoolParser.parseExpression("NOT (name = test)");
        assertTrue(nodeOptional.isSuccess());
        assertEquals(nodeOptional.get().getTokenType().name(), TokenType.BOOLEAN.name());
        final BooleanToken booleanToken = (BooleanToken) nodeOptional.get();
        assertNotNull(booleanToken.getLeft());
        assertNull(booleanToken.getRight());
        assertEquals(booleanToken.getOperator(), LogicalOperationType.NOT);
        assertEquals(booleanToken.getLeft().getTokenType().name(), TokenType.STRING.name());
        verifyStringToken((StringToken) booleanToken.getLeft(), "name", "test");
    }

    @Test
    public void testNestedAndCondition() {
        final Try<Token> nodeOptional = boolExpressionBoolParser.parseExpression("name = test OR (age=33 AND city = dummy)");
        assertTrue(nodeOptional.isSuccess());
        assertEquals(nodeOptional.get().getTokenType().name(), TokenType.BOOLEAN.name());
        final BooleanToken booleanToken = (BooleanToken) nodeOptional.get();
        assertNotNull(booleanToken.getLeft());
        assertNotNull(booleanToken.getRight());
        assertEquals(booleanToken.getOperator(), LogicalOperationType.OR);
        assertEquals(booleanToken.getLeft().getTokenType().name(), TokenType.STRING.name());
        assertEquals(booleanToken.getRight().getTokenType().name(), TokenType.BOOLEAN.name());
        verifyStringToken((StringToken) booleanToken.getLeft(), "name", "test");
        final BooleanToken nestedBooleanExpression = (BooleanToken) booleanToken.getRight();
        assertNotNull(nestedBooleanExpression.getLeft());
        assertNotNull(nestedBooleanExpression.getRight());
        assertEquals(nestedBooleanExpression.getOperator(), LogicalOperationType.AND);
        assertEquals(nestedBooleanExpression.getLeft().getTokenType().name(), TokenType.NUMERIC.name());
        assertEquals(nestedBooleanExpression.getRight().getTokenType().name(), TokenType.STRING.name());
        verifyNumericToken((NumericToken) nestedBooleanExpression.getLeft(), "age", 33, Operator.EQUALS);
        verifyStringToken((StringToken) nestedBooleanExpression.getRight(), "city", "dummy");
    }

    @Test
    public void testNestedAndCondition1() {
        final Try<Token> nodeOptional = boolExpressionBoolParser.parseExpression("(agel=44 AND cityl = abc) OR (ager=33 AND cityr = dummy)");
        assertTrue(nodeOptional.isSuccess());
        assertEquals(nodeOptional.get().getTokenType().name(), TokenType.BOOLEAN.name());
        final BooleanToken booleanToken = (BooleanToken) nodeOptional.get();
        assertNotNull(booleanToken.getLeft());
        assertNotNull(booleanToken.getRight());
        assertEquals(booleanToken.getOperator(), LogicalOperationType.OR);
        assertEquals(booleanToken.getLeft().getTokenType().name(), TokenType.BOOLEAN.name());
        assertEquals(booleanToken.getRight().getTokenType().name(), TokenType.BOOLEAN.name());
        final BooleanToken nestedLeftBooleanExpression = (BooleanToken) booleanToken.getLeft();
        assertNotNull(nestedLeftBooleanExpression.getLeft());
        assertNotNull(nestedLeftBooleanExpression.getRight());
        assertEquals(nestedLeftBooleanExpression.getOperator(), LogicalOperationType.AND);
        assertEquals(nestedLeftBooleanExpression.getLeft().getTokenType().name(), TokenType.NUMERIC.name());
        assertEquals(nestedLeftBooleanExpression.getRight().getTokenType().name(), TokenType.STRING.name());
        verifyNumericToken((NumericToken) nestedLeftBooleanExpression.getLeft(), "agel", 44, Operator.EQUALS);
        verifyStringToken((StringToken) nestedLeftBooleanExpression.getRight(), "cityl", "abc");
        final BooleanToken nestedRightBooleanExpression = (BooleanToken) booleanToken.getRight();
        assertNotNull(nestedRightBooleanExpression.getLeft());
        assertNotNull(nestedRightBooleanExpression.getRight());
        assertEquals(nestedRightBooleanExpression.getOperator(), LogicalOperationType.AND);
        assertEquals(nestedRightBooleanExpression.getLeft().getTokenType().name(), TokenType.NUMERIC.name());
        assertEquals(nestedRightBooleanExpression.getRight().getTokenType().name(), TokenType.STRING.name());
        verifyNumericToken((NumericToken) nestedRightBooleanExpression.getLeft(), "ager", 33, Operator.EQUALS);
        verifyStringToken((StringToken) nestedRightBooleanExpression.getRight(), "cityr", "dummy");
    }

    @Test
    public void testNestedOrCondition() {
        final Try<Token> nodeOptional = boolExpressionBoolParser.parseExpression("name = test AND (age=33 OR city = dummy)");
        assertTrue(nodeOptional.isSuccess());
        assertEquals(nodeOptional.get().getTokenType().name(), TokenType.BOOLEAN.name());
        final BooleanToken booleanToken = (BooleanToken) nodeOptional.get();
        assertNotNull(booleanToken.getLeft());
        assertNotNull(booleanToken.getRight());
        assertEquals(booleanToken.getOperator(), LogicalOperationType.AND);
        assertEquals(booleanToken.getLeft().getTokenType().name(), TokenType.STRING.name());
        assertEquals(booleanToken.getRight().getTokenType().name(), TokenType.BOOLEAN.name());
        verifyStringToken((StringToken) booleanToken.getLeft(), "name", "test");
        final BooleanToken nestedBooleanExpression = (BooleanToken) booleanToken.getRight();
        assertNotNull(nestedBooleanExpression.getLeft());
        assertNotNull(nestedBooleanExpression.getRight());
        assertEquals(nestedBooleanExpression.getOperator(), LogicalOperationType.OR);
        assertEquals(nestedBooleanExpression.getLeft().getTokenType().name(), TokenType.NUMERIC.name());
        assertEquals(nestedBooleanExpression.getRight().getTokenType().name(), TokenType.STRING.name());
        verifyNumericToken((NumericToken) nestedBooleanExpression.getLeft(), "age", 33, Operator.EQUALS);
        verifyStringToken((StringToken) nestedBooleanExpression.getRight(), "city", "dummy");
    }

    @Test
    public void testIntegerList() {
        final Try<Token> nodeOptional = boolExpressionBoolParser.parseExpression("age IN (12,45)");
        assertTrue(nodeOptional.isSuccess());
        assertEquals(nodeOptional.get().getTokenType().name(), TokenType.IN.name());
        final InToken inToken = (InToken) nodeOptional.get();
        assertEquals(inToken.getItems().size(), 2);
        assertEquals(inToken.getField(), "age");
        assertEquals(inToken.getItems().get(0).getKey(), DataType.INTEGER);
        assertEquals(inToken.getItems().get(1).getKey(), DataType.INTEGER);
        assertEquals(inToken.getItems().get(0).getValue(), 12);
        assertEquals(inToken.getItems().get(1).getValue(), 45);
    }

    @Test
    public void testStringList() {
        final Try<Token> nodeOptional = boolExpressionBoolParser.parseExpression("name IN (abc, def, 'abc def')");
        assertTrue(nodeOptional.isSuccess());
        assertEquals(nodeOptional.get().getTokenType().name(), TokenType.IN.name());
        final InToken inToken = (InToken) nodeOptional.get();
        assertEquals(inToken.getItems().size(), 3);
        assertEquals(inToken.getField(), "name");
        assertEquals(inToken.getItems().get(0).getKey(), DataType.STRING);
        assertEquals(inToken.getItems().get(1).getKey(), DataType.STRING);
        assertEquals(inToken.getItems().get(2).getKey(), DataType.STRING);
        assertEquals(inToken.getItems().get(0).getValue(), "abc");
        assertEquals(inToken.getItems().get(1).getValue(), "def");
        assertEquals(inToken.getItems().get(2).getValue(), "abc def");
    }

    @Test
    public void testStringList1() {
        final Try<Token> nodeOptional = boolExpressionBoolParser.parseExpression("name IN (abc, def, 'abc, def')");
        assertTrue(nodeOptional.isSuccess());
        assertEquals(nodeOptional.get().getTokenType().name(), TokenType.IN.name());
        final InToken inToken = (InToken) nodeOptional.get();
        assertEquals(inToken.getItems().size(), 3);
        assertEquals(inToken.getField(), "name");
        assertEquals(inToken.getItems().get(0).getKey(), DataType.STRING);
        assertEquals(inToken.getItems().get(1).getKey(), DataType.STRING);
        assertEquals(inToken.getItems().get(2).getKey(), DataType.STRING);
        assertEquals(inToken.getItems().get(0).getValue(), "abc");
        assertEquals(inToken.getItems().get(1).getValue(), "def");
        assertEquals(inToken.getItems().get(2).getValue(), "abc, def");
    }

    @Test
    public void testStringList2() {
        final Try<Token> nodeOptional = boolExpressionBoolParser.parseExpression("name IN (abc, def, 'ab\"c')");
        assertTrue(nodeOptional.isSuccess());
        assertEquals(nodeOptional.get().getTokenType().name(), TokenType.IN.name());
        final InToken inToken = (InToken) nodeOptional.get();
        assertEquals(inToken.getItems().size(), 3);
        assertEquals(inToken.getField(), "name");
        assertEquals(inToken.getItems().get(0).getKey(), DataType.STRING);
        assertEquals(inToken.getItems().get(1).getKey(), DataType.STRING);
        assertEquals(inToken.getItems().get(2).getKey(), DataType.STRING);
        assertEquals(inToken.getItems().get(0).getValue(), "abc");
        assertEquals(inToken.getItems().get(1).getValue(), "def");
        assertEquals(inToken.getItems().get(2).getValue(), "ab\"c");
    }

    @Test
    public void testInvalidExpression() {
        final Try<Token> nodeOptional = boolExpressionBoolParser.parseExpression("a");
        assertTrue(nodeOptional.isFailure());
        assertTrue(nodeOptional.getCause() instanceof InvalidExpressionException);
    }

    @Test
    public void testInvalidNotExpression() {
        final Try<Token> nodeOptional = boolExpressionBoolParser.parseExpression("not a > 5");
        assertTrue(nodeOptional.isFailure());
        assertTrue(nodeOptional.getCause() instanceof InvalidExpressionException);
    }

    private void verifyStringToken(final StringToken stringToken, final String field, final String value) {
        assertEquals(stringToken.getTokenType().name(), TokenType.STRING.name());
        assertEquals(stringToken.getField(), field);
        assertEquals(stringToken.getValue(), value);
    }

    private void verifyNumericToken(final NumericToken numericToken, final String field, final Object value, final Operator operator) {
        assertEquals(numericToken.getTokenType().name(), TokenType.NUMERIC.name());
        assertEquals(numericToken.getField(), field);
        assertEquals(numericToken.getValue(), value);
        assertEquals(numericToken.getOperator().name(), operator.name());
    }

    private void verifyNumericRangeToken(final NumericRangeToken numericRangeToken, final String field, final Object fromValue,
                                         final Object toValue) {
        assertEquals(numericRangeToken.getTokenType().name(), TokenType.NUMERIC_RANGE.name());
        assertEquals(numericRangeToken.getField(), field);
        assertEquals(numericRangeToken.getFromValue(), fromValue);
        assertEquals(numericRangeToken.getToValue(), toValue);
    }
}

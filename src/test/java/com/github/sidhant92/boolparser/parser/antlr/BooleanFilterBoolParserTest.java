package com.github.sidhant92.boolparser.parser.antlr;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import com.github.sidhant92.boolparser.constant.DataType;
import com.github.sidhant92.boolparser.constant.LogicalOperationType;
import com.github.sidhant92.boolparser.constant.NodeType;
import com.github.sidhant92.boolparser.constant.Operator;
import com.github.sidhant92.boolparser.domain.ArrayNode;
import com.github.sidhant92.boolparser.domain.BooleanNode;
import com.github.sidhant92.boolparser.domain.InNode;
import com.github.sidhant92.boolparser.domain.Node;
import com.github.sidhant92.boolparser.domain.NumericRangeNode;
import com.github.sidhant92.boolparser.domain.ComparisonNode;
import com.github.sidhant92.boolparser.domain.UnaryNode;
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
        final Try<Node> nodeOptional = boolExpressionBoolParser.parseExpression("not false");
        assertTrue(nodeOptional.isSuccess());
        assertEquals(nodeOptional.get().getTokenType(), NodeType.BOOLEAN);
        assertEquals(((BooleanNode) nodeOptional.get()).getLeft().getTokenType(), NodeType.UNARY);
        assertEquals(((UnaryNode) ((BooleanNode) nodeOptional.get()).getLeft()).getDataType(), DataType.BOOLEAN);
        assertEquals(((UnaryNode) ((BooleanNode) nodeOptional.get()).getLeft()).getValue(), false);
    }

    @Test
    public void testUnaryTokenString() {
        final Try<Node> nodeOptional = boolExpressionBoolParser.parseExpression("not abc");
        assertTrue(nodeOptional.isSuccess());
        assertEquals(nodeOptional.get().getTokenType(), NodeType.BOOLEAN);
        assertEquals(((BooleanNode) nodeOptional.get()).getLeft().getTokenType(), NodeType.UNARY);
        assertEquals(((UnaryNode) ((BooleanNode) nodeOptional.get()).getLeft()).getDataType(), DataType.STRING);
        assertEquals(((UnaryNode) ((BooleanNode) nodeOptional.get()).getLeft()).getValue(), "abc");
    }

    @Test
    public void testSingleStringToken() {
        final Try<Node> nodeOptional = boolExpressionBoolParser.parseExpression("name = test");
        assertTrue(nodeOptional.isSuccess());
        assertEquals(nodeOptional.get().getTokenType().name(), NodeType.COMPARISON.name());
        verifyStringToken((ComparisonNode) nodeOptional.get(), "name", "test");
    }

    @Test
    public void testSingleStringTokenWithSingleQuotes() {
        final Try<Node> nodeOptional = boolExpressionBoolParser.parseExpression("name = \"te\'st\"");
        assertTrue(nodeOptional.isSuccess());
        assertEquals(nodeOptional.get().getTokenType().name(), NodeType.COMPARISON.name());
        verifyStringToken((ComparisonNode) nodeOptional.get(), "name", "te\'st");
    }

    @Test
    public void testSingleStringTokenWithDoubleQuotes() {
        final Try<Node> nodeOptional = boolExpressionBoolParser.parseExpression("name = 'te\"st'");
        assertTrue(nodeOptional.isSuccess());
        assertEquals(nodeOptional.get().getTokenType().name(), NodeType.COMPARISON.name());
        verifyStringToken((ComparisonNode) nodeOptional.get(), "name", "te\"st");
    }

    @Test
    public void testSingleStringTokenWithSpace() {
        final Try<Node> nodeOptional = boolExpressionBoolParser.parseExpression("name = \"first second\"");
        assertTrue(nodeOptional.isSuccess());
        assertEquals(nodeOptional.get().getTokenType().name(), NodeType.COMPARISON.name());
        verifyStringToken((ComparisonNode) nodeOptional.get(), "name", "first second");
    }

    @Test
    public void testSingleIntToken() {
        final Try<Node> nodeOptional = boolExpressionBoolParser.parseExpression("age=44");
        assertTrue(nodeOptional.isSuccess());
        assertEquals(nodeOptional.get().getTokenType().name(), NodeType.COMPARISON.name());
        verifyNumericToken((ComparisonNode) nodeOptional.get(), "age", 44, Operator.EQUALS);
    }

    @Test
    public void testSingleLongToken() {
        final Try<Node> nodeOptional = boolExpressionBoolParser.parseExpression("age=1611473334114");
        assertTrue(nodeOptional.isSuccess());
        assertEquals(nodeOptional.get().getTokenType().name(), NodeType.COMPARISON.name());
        verifyNumericToken((ComparisonNode) nodeOptional.get(), "age", 1611473334114L, Operator.EQUALS);
    }

    @Test
    public void testSingleDecimalToken() {
        final Try<Node> nodeOptional = boolExpressionBoolParser.parseExpression("age=44.34");
        assertTrue(nodeOptional.isSuccess());
        assertEquals(nodeOptional.get().getTokenType().name(), NodeType.COMPARISON.name());
        verifyNumericToken((ComparisonNode) nodeOptional.get(), "age", 44.34, Operator.EQUALS);
    }

    @Test
    public void testSingleIntRangeToken() {
        final Try<Node> nodeOptional = boolExpressionBoolParser.parseExpression("age 18 TO 44");
        assertTrue(nodeOptional.isSuccess());
        assertEquals(nodeOptional.get().getTokenType().name(), NodeType.NUMERIC_RANGE.name());
        verifyNumericRangeToken((NumericRangeNode) nodeOptional.get(), "age", 18, 44);
    }

    @Test
    public void testGreaterThan() {
        final Try<Node> nodeOptional = boolExpressionBoolParser.parseExpression("age > 18");
        assertTrue(nodeOptional.isSuccess());
        assertEquals(nodeOptional.get().getTokenType().name(), NodeType.COMPARISON.name());
        verifyNumericToken((ComparisonNode) nodeOptional.get(), "age", 18, Operator.GREATER_THAN);
    }

    @Test
    public void testSingleDecimalRangeToken() {
        final Try<Node> nodeOptional = boolExpressionBoolParser.parseExpression("age 18.4 TO 44.2");
        assertTrue(nodeOptional.isSuccess());
        assertEquals(nodeOptional.get().getTokenType().name(), NodeType.NUMERIC_RANGE.name());
        verifyNumericRangeToken((NumericRangeNode) nodeOptional.get(), "age", 18.4, 44.2);
    }

    @Test
    public void testSimpleOrCondition() {
        final Try<Node> nodeOptional = boolExpressionBoolParser.parseExpression("name = test OR age=33");
        assertTrue(nodeOptional.isSuccess());
        assertEquals(nodeOptional.get().getTokenType().name(), NodeType.BOOLEAN.name());
        final BooleanNode booleanToken = (BooleanNode) nodeOptional.get();
        assertNotNull(booleanToken.getLeft());
        assertNotNull(booleanToken.getRight());
        assertEquals(booleanToken.getOperator(), LogicalOperationType.OR);
        assertEquals(booleanToken.getLeft().getTokenType().name(), NodeType.COMPARISON.name());
        assertEquals(booleanToken.getRight().getTokenType().name(), NodeType.COMPARISON.name());
        verifyStringToken((ComparisonNode) booleanToken.getLeft(), "name", "test");
        verifyNumericToken((ComparisonNode) booleanToken.getRight(), "age", 33, Operator.EQUALS);
    }

    @Test
    public void testSimpleAndCondition() {
        final Try<Node> nodeOptional = boolExpressionBoolParser.parseExpression("name = test AND age=33");
        assertTrue(nodeOptional.isSuccess());
        assertEquals(nodeOptional.get().getTokenType().name(), NodeType.BOOLEAN.name());
        final BooleanNode booleanToken = (BooleanNode) nodeOptional.get();
        assertNotNull(booleanToken.getLeft());
        assertNotNull(booleanToken.getRight());
        assertEquals(booleanToken.getOperator(), LogicalOperationType.AND);
        assertEquals(booleanToken.getLeft().getTokenType().name(), NodeType.COMPARISON.name());
        assertEquals(booleanToken.getRight().getTokenType().name(), NodeType.COMPARISON.name());
        verifyStringToken((ComparisonNode) booleanToken.getLeft(), "name", "test");
        verifyNumericToken((ComparisonNode) booleanToken.getRight(), "age", 33, Operator.EQUALS);
    }

    @Test
    public void testSimpleNotCondition() {
        final Try<Node> nodeOptional = boolExpressionBoolParser.parseExpression("NOT (name = test)");
        assertTrue(nodeOptional.isSuccess());
        assertEquals(nodeOptional.get().getTokenType().name(), NodeType.BOOLEAN.name());
        final BooleanNode booleanToken = (BooleanNode) nodeOptional.get();
        assertNotNull(booleanToken.getLeft());
        assertNull(booleanToken.getRight());
        assertEquals(booleanToken.getOperator(), LogicalOperationType.NOT);
        assertEquals(booleanToken.getLeft().getTokenType().name(), NodeType.COMPARISON.name());
        verifyStringToken((ComparisonNode) booleanToken.getLeft(), "name", "test");
    }

    @Test
    public void testNestedAndCondition() {
        final Try<Node> nodeOptional = boolExpressionBoolParser.parseExpression("name = test OR (age=33 AND city = dummy)");
        assertTrue(nodeOptional.isSuccess());
        assertEquals(nodeOptional.get().getTokenType().name(), NodeType.BOOLEAN.name());
        final BooleanNode booleanToken = (BooleanNode) nodeOptional.get();
        assertNotNull(booleanToken.getLeft());
        assertNotNull(booleanToken.getRight());
        assertEquals(booleanToken.getOperator(), LogicalOperationType.OR);
        assertEquals(booleanToken.getLeft().getTokenType().name(), NodeType.COMPARISON.name());
        assertEquals(booleanToken.getRight().getTokenType().name(), NodeType.BOOLEAN.name());
        verifyStringToken((ComparisonNode) booleanToken.getLeft(), "name", "test");
        final BooleanNode nestedBooleanExpression = (BooleanNode) booleanToken.getRight();
        assertNotNull(nestedBooleanExpression.getLeft());
        assertNotNull(nestedBooleanExpression.getRight());
        assertEquals(nestedBooleanExpression.getOperator(), LogicalOperationType.AND);
        assertEquals(nestedBooleanExpression.getLeft().getTokenType().name(), NodeType.COMPARISON.name());
        assertEquals(nestedBooleanExpression.getRight().getTokenType().name(), NodeType.COMPARISON.name());
        verifyNumericToken((ComparisonNode) nestedBooleanExpression.getLeft(), "age", 33, Operator.EQUALS);
        verifyStringToken((ComparisonNode) nestedBooleanExpression.getRight(), "city", "dummy");
    }

    @Test
    public void testNestedAndCondition1() {
        final Try<Node> nodeOptional = boolExpressionBoolParser.parseExpression("(agel=44 AND cityl = abc) OR (ager=33 AND cityr = dummy)");
        assertTrue(nodeOptional.isSuccess());
        assertEquals(nodeOptional.get().getTokenType().name(), NodeType.BOOLEAN.name());
        final BooleanNode booleanToken = (BooleanNode) nodeOptional.get();
        assertNotNull(booleanToken.getLeft());
        assertNotNull(booleanToken.getRight());
        assertEquals(booleanToken.getOperator(), LogicalOperationType.OR);
        assertEquals(booleanToken.getLeft().getTokenType().name(), NodeType.BOOLEAN.name());
        assertEquals(booleanToken.getRight().getTokenType().name(), NodeType.BOOLEAN.name());
        final BooleanNode nestedLeftBooleanExpression = (BooleanNode) booleanToken.getLeft();
        assertNotNull(nestedLeftBooleanExpression.getLeft());
        assertNotNull(nestedLeftBooleanExpression.getRight());
        assertEquals(nestedLeftBooleanExpression.getOperator(), LogicalOperationType.AND);
        assertEquals(nestedLeftBooleanExpression.getLeft().getTokenType().name(), NodeType.COMPARISON.name());
        assertEquals(nestedLeftBooleanExpression.getRight().getTokenType().name(), NodeType.COMPARISON.name());
        verifyNumericToken((ComparisonNode) nestedLeftBooleanExpression.getLeft(), "agel", 44, Operator.EQUALS);
        verifyStringToken((ComparisonNode) nestedLeftBooleanExpression.getRight(), "cityl", "abc");
        final BooleanNode nestedRightBooleanExpression = (BooleanNode) booleanToken.getRight();
        assertNotNull(nestedRightBooleanExpression.getLeft());
        assertNotNull(nestedRightBooleanExpression.getRight());
        assertEquals(nestedRightBooleanExpression.getOperator(), LogicalOperationType.AND);
        assertEquals(nestedRightBooleanExpression.getLeft().getTokenType().name(), NodeType.COMPARISON.name());
        assertEquals(nestedRightBooleanExpression.getRight().getTokenType().name(), NodeType.COMPARISON.name());
        verifyNumericToken((ComparisonNode) nestedRightBooleanExpression.getLeft(), "ager", 33, Operator.EQUALS);
        verifyStringToken((ComparisonNode) nestedRightBooleanExpression.getRight(), "cityr", "dummy");
    }

    @Test
    public void testNestedOrCondition() {
        final Try<Node> nodeOptional = boolExpressionBoolParser.parseExpression("name = test AND (age=33 OR city = dummy)");
        assertTrue(nodeOptional.isSuccess());
        assertEquals(nodeOptional.get().getTokenType().name(), NodeType.BOOLEAN.name());
        final BooleanNode booleanToken = (BooleanNode) nodeOptional.get();
        assertNotNull(booleanToken.getLeft());
        assertNotNull(booleanToken.getRight());
        assertEquals(booleanToken.getOperator(), LogicalOperationType.AND);
        assertEquals(booleanToken.getLeft().getTokenType().name(), NodeType.COMPARISON.name());
        assertEquals(booleanToken.getRight().getTokenType().name(), NodeType.BOOLEAN.name());
        verifyStringToken((ComparisonNode) booleanToken.getLeft(), "name", "test");
        final BooleanNode nestedBooleanExpression = (BooleanNode) booleanToken.getRight();
        assertNotNull(nestedBooleanExpression.getLeft());
        assertNotNull(nestedBooleanExpression.getRight());
        assertEquals(nestedBooleanExpression.getOperator(), LogicalOperationType.OR);
        assertEquals(nestedBooleanExpression.getLeft().getTokenType().name(), NodeType.COMPARISON.name());
        assertEquals(nestedBooleanExpression.getRight().getTokenType().name(), NodeType.COMPARISON.name());
        verifyNumericToken((ComparisonNode) nestedBooleanExpression.getLeft(), "age", 33, Operator.EQUALS);
        verifyStringToken((ComparisonNode) nestedBooleanExpression.getRight(), "city", "dummy");
    }

    @Test
    public void testIntegerList() {
        final Try<Node> nodeOptional = boolExpressionBoolParser.parseExpression("age IN (12,45)");
        assertTrue(nodeOptional.isSuccess());
        assertEquals(nodeOptional.get().getTokenType().name(), NodeType.IN.name());
        final InNode inToken = (InNode) nodeOptional.get();
        assertEquals(inToken.getItems().size(), 2);
        assertEquals(inToken.getField(), "age");
        assertEquals(inToken.getItems().get(0).getKey(), DataType.INTEGER);
        assertEquals(inToken.getItems().get(1).getKey(), DataType.INTEGER);
        assertEquals(inToken.getItems().get(0).getValue(), 12);
        assertEquals(inToken.getItems().get(1).getValue(), 45);
    }

    @Test
    public void testNotIntegerList() {
        final Try<Node> nodeOptional = boolExpressionBoolParser.parseExpression("age not IN (12,45)");
        assertTrue(nodeOptional.isSuccess());
        assertEquals(nodeOptional.get().getTokenType().name(), NodeType.BOOLEAN.name());
        assertNotNull(((BooleanNode)nodeOptional.get()).getLeft());
        assertNull(((BooleanNode)nodeOptional.get()).getRight());
        assertEquals(((BooleanNode)nodeOptional.get()).getOperator(), LogicalOperationType.NOT);
        final InNode inToken = (InNode) ((BooleanNode)nodeOptional.get()).getLeft();
        assertEquals(inToken.getItems().size(), 2);
        assertEquals(inToken.getField(), "age");
        assertEquals(inToken.getItems().get(0).getKey(), DataType.INTEGER);
        assertEquals(inToken.getItems().get(1).getKey(), DataType.INTEGER);
        assertEquals(inToken.getItems().get(0).getValue(), 12);
        assertEquals(inToken.getItems().get(1).getValue(), 45);
    }

    @Test
    public void testStringList() {
        final Try<Node> nodeOptional = boolExpressionBoolParser.parseExpression("name IN (abc, def, 'abc def')");
        assertTrue(nodeOptional.isSuccess());
        assertEquals(nodeOptional.get().getTokenType().name(), NodeType.IN.name());
        final InNode inToken = (InNode) nodeOptional.get();
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
        final Try<Node> nodeOptional = boolExpressionBoolParser.parseExpression("name IN (abc, def, 'abc, def')");
        assertTrue(nodeOptional.isSuccess());
        assertEquals(nodeOptional.get().getTokenType().name(), NodeType.IN.name());
        final InNode inToken = (InNode) nodeOptional.get();
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
        final Try<Node> nodeOptional = boolExpressionBoolParser.parseExpression("name IN (abc, def, 'ab\"c')");
        assertTrue(nodeOptional.isSuccess());
        assertEquals(nodeOptional.get().getTokenType().name(), NodeType.IN.name());
        final InNode inToken = (InNode) nodeOptional.get();
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
        final Try<Node> nodeOptional = boolExpressionBoolParser.parseExpression("a");
        assertTrue(nodeOptional.isFailure());
        assertTrue(nodeOptional.getCause() instanceof InvalidExpressionException);
    }

    @Test
    public void testInvalidNotExpression() {
        final Try<Node> nodeOptional = boolExpressionBoolParser.parseExpression("not a > 5");
        assertTrue(nodeOptional.isFailure());
        assertTrue(nodeOptional.getCause() instanceof InvalidExpressionException);
    }

    @Test
    public void testContainsAny() {
        final Try<Node> nodeOptional = boolExpressionBoolParser.parseExpression("a contains_any (1,2,3)");
        assertTrue(nodeOptional.isSuccess());
        assertEquals(nodeOptional.get().getTokenType(), NodeType.ARRAY);
        assertEquals(((ArrayNode) nodeOptional.get()).getField(), "a");
        assertEquals(((ArrayNode) nodeOptional.get()).getOperator(), Operator.CONTAINS_ANY);
        assertEquals(((ArrayNode) nodeOptional.get()).getItems().size(), 3);
    }

    @Test
    public void testContainsAll() {
        final Try<Node> nodeOptional = boolExpressionBoolParser.parseExpression("a contains_all (\"a\", \"b\"");
        assertTrue(nodeOptional.isSuccess());
        assertEquals(nodeOptional.get().getTokenType(), NodeType.ARRAY);
        assertEquals(((ArrayNode) nodeOptional.get()).getField(), "a");
        assertEquals(((ArrayNode) nodeOptional.get()).getOperator(), Operator.CONTAINS_ALL);
        assertEquals(((ArrayNode) nodeOptional.get()).getItems().size(), 2);
    }

    private void verifyStringToken(final ComparisonNode stringToken, final String field, final String value) {
        assertEquals(stringToken.getTokenType().name(), NodeType.COMPARISON.name());
        assertEquals(stringToken.getField(), field);
        assertEquals(stringToken.getValue(), value);
    }

    private void verifyNumericToken(final ComparisonNode comparisonToken, final String field, final Object value, final Operator operator) {
        assertEquals(comparisonToken.getTokenType().name(), NodeType.COMPARISON.name());
        assertEquals(comparisonToken.getField(), field);
        assertEquals(comparisonToken.getValue(), value);
        assertEquals(comparisonToken.getOperator().name(), operator.name());
    }

    private void verifyNumericRangeToken(final NumericRangeNode numericRangeToken, final String field, final Object fromValue, final Object toValue) {
        assertEquals(numericRangeToken.getTokenType().name(), NodeType.NUMERIC_RANGE.name());
        assertEquals(numericRangeToken.getField(), field);
        assertEquals(numericRangeToken.getFromValue(), fromValue);
        assertEquals(numericRangeToken.getToValue(), toValue);
    }
}

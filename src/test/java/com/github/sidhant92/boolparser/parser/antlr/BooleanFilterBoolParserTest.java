package com.github.sidhant92.boolparser.parser.antlr;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import com.github.sidhant92.boolparser.constant.DataType;
import com.github.sidhant92.boolparser.constant.LogicalOperationType;
import com.github.sidhant92.boolparser.constant.NodeType;
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
        final Try<Node> nodeOptional = boolExpressionBoolParser.parseExpression("name = 'test'");
        assertTrue(nodeOptional.isSuccess());
        verifyComparisonToken(nodeOptional.get(), "name", Operator.EQUALS);
        verifyUnaryToken(((ComparisonNode) nodeOptional.get()).getRight(), "test", DataType.STRING);
    }

    @Test
    public void testSingleStringTokenWithSingleQuotes() {
        final Try<Node> nodeOptional = boolExpressionBoolParser.parseExpression("name = \"te\'st\"");
        assertTrue(nodeOptional.isSuccess());
        verifyComparisonToken(nodeOptional.get(), "name", Operator.EQUALS);
        verifyUnaryToken(((ComparisonNode) nodeOptional.get()).getRight(), "te'st", DataType.STRING);
    }

    @Test
    public void testSingleStringTokenWithDoubleQuotes() {
        final Try<Node> nodeOptional = boolExpressionBoolParser.parseExpression("name = 'te\"st'");
        assertTrue(nodeOptional.isSuccess());
        verifyComparisonToken(nodeOptional.get(), "name", Operator.EQUALS);
        verifyUnaryToken(((ComparisonNode) nodeOptional.get()).getRight(), "te\"st", DataType.STRING);
    }

    @Test
    public void testSingleStringTokenWithSpace() {
        final Try<Node> nodeOptional = boolExpressionBoolParser.parseExpression("name = \"first second\"");
        assertTrue(nodeOptional.isSuccess());
        verifyComparisonToken(nodeOptional.get(), "name", Operator.EQUALS);
        verifyUnaryToken(((ComparisonNode) nodeOptional.get()).getRight(), "first second", DataType.STRING);
    }

    @Test
    public void testSingleIntToken() {
        final Try<Node> nodeOptional = boolExpressionBoolParser.parseExpression("age=44");
        assertTrue(nodeOptional.isSuccess());
        verifyComparisonToken(nodeOptional.get(), "age", Operator.EQUALS);
        verifyUnaryToken(((ComparisonNode) nodeOptional.get()).getRight(), 44, DataType.INTEGER);
    }

    @Test
    public void testSingleLongToken() {
        final Try<Node> nodeOptional = boolExpressionBoolParser.parseExpression("age=1611473334114");
        assertTrue(nodeOptional.isSuccess());
        verifyComparisonToken(nodeOptional.get(), "age", Operator.EQUALS);
        verifyUnaryToken(((ComparisonNode) nodeOptional.get()).getRight(), 1611473334114L, DataType.LONG);
    }

    @Test
    public void testSingleDecimalToken() {
        final Try<Node> nodeOptional = boolExpressionBoolParser.parseExpression("age=44.34");
        assertTrue(nodeOptional.isSuccess());
        verifyComparisonToken(nodeOptional.get(), "age", Operator.EQUALS);
        verifyUnaryToken(((ComparisonNode) nodeOptional.get()).getRight(), new BigDecimal("44.34"), DataType.DECIMAL);
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
        verifyComparisonToken(nodeOptional.get(), "age", Operator.GREATER_THAN);
        verifyUnaryToken(((ComparisonNode) nodeOptional.get()).getRight(), 18, DataType.INTEGER);
    }

    @Test
    public void testGreaterThanWithField() {
        final Try<Node> nodeOptional = boolExpressionBoolParser.parseExpression("age > a");
        assertTrue(nodeOptional.isSuccess());
        verifyComparisonToken(nodeOptional.get(), "age", Operator.GREATER_THAN);
        verifyFieldToken(((ComparisonNode) nodeOptional.get()).getRight(), "a");
    }

    @Test
    public void testSingleDecimalRangeToken() {
        final Try<Node> nodeOptional = boolExpressionBoolParser.parseExpression("age 18.4 TO 44.2");
        assertTrue(nodeOptional.isSuccess());
        assertEquals(nodeOptional.get().getTokenType().name(), NodeType.NUMERIC_RANGE.name());
        verifyNumericRangeToken((NumericRangeNode) nodeOptional.get(), "age", new BigDecimal("18.4"), new BigDecimal("44.2"));
    }

    @Test
    public void testSimpleOrCondition() {
        final Try<Node> nodeOptional = boolExpressionBoolParser.parseExpression("name = 'test' OR age=33");
        assertTrue(nodeOptional.isSuccess());
        assertEquals(nodeOptional.get().getTokenType().name(), NodeType.BOOLEAN.name());
        final BooleanNode booleanToken = (BooleanNode) nodeOptional.get();
        assertNotNull(booleanToken.getLeft());
        assertNotNull(booleanToken.getRight());
        assertEquals(booleanToken.getOperator(), LogicalOperationType.OR);
        assertEquals(booleanToken.getLeft().getTokenType().name(), NodeType.COMPARISON.name());
        assertEquals(booleanToken.getRight().getTokenType().name(), NodeType.COMPARISON.name());
        verifyComparisonToken(((BooleanNode) nodeOptional.get()).getLeft(), "name", Operator.EQUALS);
        verifyUnaryToken(((ComparisonNode)((BooleanNode) nodeOptional.get()).getLeft()).getRight(), "test", DataType.STRING);
        verifyComparisonToken(((BooleanNode) nodeOptional.get()).getRight(), "age", Operator.EQUALS);
        verifyUnaryToken(((ComparisonNode)((BooleanNode) nodeOptional.get()).getRight()).getRight(), 33, DataType.INTEGER);
    }

    @Test
    public void testSimpleAndCondition() {
        final Try<Node> nodeOptional = boolExpressionBoolParser.parseExpression("name = 'test' AND age=33");
        assertTrue(nodeOptional.isSuccess());
        assertEquals(nodeOptional.get().getTokenType().name(), NodeType.BOOLEAN.name());
        final BooleanNode booleanToken = (BooleanNode) nodeOptional.get();
        assertNotNull(booleanToken.getLeft());
        assertNotNull(booleanToken.getRight());
        assertEquals(booleanToken.getOperator(), LogicalOperationType.AND);
        assertEquals(booleanToken.getLeft().getTokenType().name(), NodeType.COMPARISON.name());
        assertEquals(booleanToken.getRight().getTokenType().name(), NodeType.COMPARISON.name());
        verifyComparisonToken(((BooleanNode) nodeOptional.get()).getLeft(), "name", Operator.EQUALS);
        verifyUnaryToken(((ComparisonNode)((BooleanNode) nodeOptional.get()).getLeft()).getRight(), "test", DataType.STRING);
        verifyComparisonToken(((BooleanNode) nodeOptional.get()).getRight(), "age", Operator.EQUALS);
        verifyUnaryToken(((ComparisonNode)((BooleanNode) nodeOptional.get()).getRight()).getRight(), 33, DataType.INTEGER);
    }

    @Test
    public void testSimpleNotCondition() {
        final Try<Node> nodeOptional = boolExpressionBoolParser.parseExpression("NOT (name = 'test')");
        assertTrue(nodeOptional.isSuccess());
        assertEquals(nodeOptional.get().getTokenType().name(), NodeType.BOOLEAN.name());
        final BooleanNode booleanToken = (BooleanNode) nodeOptional.get();
        assertNotNull(booleanToken.getLeft());
        assertNull(booleanToken.getRight());
        assertEquals(booleanToken.getOperator(), LogicalOperationType.NOT);
        assertEquals(booleanToken.getLeft().getTokenType().name(), NodeType.COMPARISON.name());
        verifyComparisonToken(((BooleanNode) nodeOptional.get()).getLeft(), "name", Operator.EQUALS);
        verifyUnaryToken(((ComparisonNode)((BooleanNode) nodeOptional.get()).getLeft()).getRight(), "test", DataType.STRING);
    }

    @Test
    public void testNestedAndCondition() {
        final Try<Node> nodeOptional = boolExpressionBoolParser.parseExpression("name = 'test' OR (age=33 AND city = 'dummy')");
        assertTrue(nodeOptional.isSuccess());
        assertEquals(nodeOptional.get().getTokenType().name(), NodeType.BOOLEAN.name());
        final BooleanNode booleanToken = (BooleanNode) nodeOptional.get();
        assertNotNull(booleanToken.getLeft());
        assertNotNull(booleanToken.getRight());
        assertEquals(booleanToken.getOperator(), LogicalOperationType.OR);
        assertEquals(booleanToken.getLeft().getTokenType().name(), NodeType.COMPARISON.name());
        assertEquals(booleanToken.getRight().getTokenType().name(), NodeType.BOOLEAN.name());
        verifyComparisonToken(booleanToken.getLeft(), "name", Operator.EQUALS);
        verifyUnaryToken(((ComparisonNode)((BooleanNode) nodeOptional.get()).getLeft()).getRight(), "test", DataType.STRING);
        final BooleanNode nestedBooleanExpression = (BooleanNode) booleanToken.getRight();
        assertNotNull(nestedBooleanExpression.getLeft());
        assertNotNull(nestedBooleanExpression.getRight());
        assertEquals(nestedBooleanExpression.getOperator(), LogicalOperationType.AND);
        assertEquals(nestedBooleanExpression.getLeft().getTokenType().name(), NodeType.COMPARISON.name());
        assertEquals(nestedBooleanExpression.getRight().getTokenType().name(), NodeType.COMPARISON.name());
        verifyComparisonToken(nestedBooleanExpression.getLeft(), "age", Operator.EQUALS);
        verifyUnaryToken(((ComparisonNode) nestedBooleanExpression.getLeft()).getRight(), 33, DataType.INTEGER);
        verifyComparisonToken(nestedBooleanExpression.getRight(), "city", Operator.EQUALS);
        verifyUnaryToken(((ComparisonNode) nestedBooleanExpression.getRight()).getRight(), "dummy", DataType.STRING);
    }

    @Test
    public void testNestedAndCondition1() {
        final Try<Node> nodeOptional = boolExpressionBoolParser.parseExpression("(agel=44 AND cityl = 'abc') OR (ager=33 AND cityr = 'dummy')");
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
        verifyComparisonToken(nestedLeftBooleanExpression.getLeft(), "agel", Operator.EQUALS);
        verifyUnaryToken(((ComparisonNode)nestedLeftBooleanExpression.getLeft()).getRight(), 44, DataType.INTEGER);
        verifyComparisonToken(nestedLeftBooleanExpression.getRight(), "cityl", Operator.EQUALS);
        verifyUnaryToken(((ComparisonNode)nestedLeftBooleanExpression.getRight()).getRight(), "abc", DataType.STRING);
        final BooleanNode nestedRightBooleanExpression = (BooleanNode) booleanToken.getRight();
        assertNotNull(nestedRightBooleanExpression.getLeft());
        assertNotNull(nestedRightBooleanExpression.getRight());
        assertEquals(nestedRightBooleanExpression.getOperator(), LogicalOperationType.AND);
        assertEquals(nestedRightBooleanExpression.getLeft().getTokenType().name(), NodeType.COMPARISON.name());
        assertEquals(nestedRightBooleanExpression.getRight().getTokenType().name(), NodeType.COMPARISON.name());
        verifyComparisonToken(nestedRightBooleanExpression.getLeft(), "ager", Operator.EQUALS);
        verifyUnaryToken(((ComparisonNode)nestedRightBooleanExpression.getLeft()).getRight(), 33, DataType.INTEGER);
        verifyComparisonToken(nestedRightBooleanExpression.getRight(), "cityr", Operator.EQUALS);
        verifyUnaryToken(((ComparisonNode)nestedRightBooleanExpression.getRight()).getRight(), "dummy", DataType.STRING);
    }

    @Test
    public void testNestedOrCondition() {
        final Try<Node> nodeOptional = boolExpressionBoolParser.parseExpression("name = 'test' AND (age=33 OR city = 'dummy')");
        assertTrue(nodeOptional.isSuccess());
        assertEquals(nodeOptional.get().getTokenType().name(), NodeType.BOOLEAN.name());
        final BooleanNode booleanToken = (BooleanNode) nodeOptional.get();
        assertNotNull(booleanToken.getLeft());
        assertNotNull(booleanToken.getRight());
        assertEquals(booleanToken.getOperator(), LogicalOperationType.AND);
        assertEquals(booleanToken.getLeft().getTokenType().name(), NodeType.COMPARISON.name());
        assertEquals(booleanToken.getRight().getTokenType().name(), NodeType.BOOLEAN.name());
        verifyComparisonToken(booleanToken.getLeft(), "name", Operator.EQUALS);
        verifyUnaryToken(((ComparisonNode)booleanToken.getLeft()).getRight(), "test", DataType.STRING);
        final BooleanNode nestedBooleanExpression = (BooleanNode) booleanToken.getRight();
        assertNotNull(nestedBooleanExpression.getLeft());
        assertNotNull(nestedBooleanExpression.getRight());
        assertEquals(nestedBooleanExpression.getOperator(), LogicalOperationType.OR);
        assertEquals(nestedBooleanExpression.getLeft().getTokenType().name(), NodeType.COMPARISON.name());
        assertEquals(nestedBooleanExpression.getRight().getTokenType().name(), NodeType.COMPARISON.name());
        verifyComparisonToken(nestedBooleanExpression.getLeft(), "age", Operator.EQUALS);
        verifyUnaryToken(((ComparisonNode)nestedBooleanExpression.getLeft()).getRight(), 33, DataType.INTEGER);
        verifyComparisonToken(nestedBooleanExpression.getRight(), "city", Operator.EQUALS);
        verifyUnaryToken(((ComparisonNode)nestedBooleanExpression.getRight()).getRight(), "dummy", DataType.STRING);
    }

    @Test
    public void testIntegerList() {
        final Try<Node> nodeOptional = boolExpressionBoolParser.parseExpression("age IN (12,45)");
        assertTrue(nodeOptional.isSuccess());
        assertEquals(nodeOptional.get().getTokenType().name(), NodeType.IN.name());
        final InNode inToken = (InNode) nodeOptional.get();
        assertEquals(inToken.getItems().size(), 2);
        assertEquals(inToken.getField(), "age");
        assertEquals(((UnaryNode)inToken.getItems().get(0)).getDataType(), DataType.INTEGER);
        assertEquals(((UnaryNode)inToken.getItems().get(1)).getDataType(), DataType.INTEGER);
        assertEquals(((UnaryNode)inToken.getItems().get(0)).getValue(), 12);
        assertEquals(((UnaryNode)inToken.getItems().get(1)).getValue(), 45);
    }

    @Test
    public void testNotIntegerList() {
        final Try<Node> nodeOptional = boolExpressionBoolParser.parseExpression("age not IN (12,45)");
        assertTrue(nodeOptional.isSuccess());
        assertEquals(nodeOptional.get().getTokenType().name(), NodeType.BOOLEAN.name());
        assertNotNull(((BooleanNode) nodeOptional.get()).getLeft());
        assertNull(((BooleanNode) nodeOptional.get()).getRight());
        assertEquals(((BooleanNode) nodeOptional.get()).getOperator(), LogicalOperationType.NOT);
        final InNode inToken = (InNode) ((BooleanNode) nodeOptional.get()).getLeft();
        assertEquals(inToken.getItems().size(), 2);
        assertEquals(inToken.getField(), "age");
        assertEquals(((UnaryNode)inToken.getItems().get(0)).getDataType(), DataType.INTEGER);
        assertEquals(((UnaryNode)inToken.getItems().get(1)).getDataType(), DataType.INTEGER);
        assertEquals(((UnaryNode)inToken.getItems().get(0)).getValue(), 12);
        assertEquals(((UnaryNode)inToken.getItems().get(1)).getValue(), 45);
    }

    @Test
    public void testStringList() {
        final Try<Node> nodeOptional = boolExpressionBoolParser.parseExpression("name IN ('abc', 'def', 'abc def')");
        assertTrue(nodeOptional.isSuccess());
        assertEquals(nodeOptional.get().getTokenType().name(), NodeType.IN.name());
        final InNode inToken = (InNode) nodeOptional.get();
        assertEquals(inToken.getItems().size(), 3);
        assertEquals(inToken.getField(), "name");
        assertEquals(((UnaryNode)inToken.getItems().get(0)).getDataType(), DataType.STRING);
        assertEquals(((UnaryNode)inToken.getItems().get(1)).getDataType(), DataType.STRING);
        assertEquals(((UnaryNode)inToken.getItems().get(2)).getDataType(), DataType.STRING);
        assertEquals(((UnaryNode)inToken.getItems().get(0)).getValue(), "abc");
        assertEquals(((UnaryNode)inToken.getItems().get(1)).getValue(), "def");
        assertEquals(((UnaryNode)inToken.getItems().get(2)).getValue(), "abc def");
    }

    @Test
    public void testStringList1() {
        final Try<Node> nodeOptional = boolExpressionBoolParser.parseExpression("name IN ('abc', 'def', 'abc, def')");
        assertTrue(nodeOptional.isSuccess());
        assertEquals(nodeOptional.get().getTokenType().name(), NodeType.IN.name());
        final InNode inToken = (InNode) nodeOptional.get();
        assertEquals(inToken.getItems().size(), 3);
        assertEquals(inToken.getField(), "name");
        assertEquals(((UnaryNode)inToken.getItems().get(0)).getDataType(), DataType.STRING);
        assertEquals(((UnaryNode)inToken.getItems().get(1)).getDataType(), DataType.STRING);
        assertEquals(((UnaryNode)inToken.getItems().get(2)).getDataType(), DataType.STRING);
        assertEquals(((UnaryNode)inToken.getItems().get(0)).getValue(), "abc");
        assertEquals(((UnaryNode)inToken.getItems().get(1)).getValue(), "def");
        assertEquals(((UnaryNode)inToken.getItems().get(2)).getValue(), "abc, def");
    }

    @Test
    public void testStringList2() {
        final Try<Node> nodeOptional = boolExpressionBoolParser.parseExpression("name IN ('abc', 'def', 'ab\"c')");
        assertTrue(nodeOptional.isSuccess());
        assertEquals(nodeOptional.get().getTokenType().name(), NodeType.IN.name());
        final InNode inToken = (InNode) nodeOptional.get();
        assertEquals(inToken.getItems().size(), 3);
        assertEquals(inToken.getField(), "name");
        assertEquals(((UnaryNode)inToken.getItems().get(0)).getDataType(), DataType.STRING);
        assertEquals(((UnaryNode)inToken.getItems().get(1)).getDataType(), DataType.STRING);
        assertEquals(((UnaryNode)inToken.getItems().get(2)).getDataType(), DataType.STRING);
        assertEquals(((UnaryNode)inToken.getItems().get(0)).getValue(), "abc");
        assertEquals(((UnaryNode)inToken.getItems().get(1)).getValue(), "def");
        assertEquals(((UnaryNode)inToken.getItems().get(2)).getValue(), "ab\"c");
    }

    @Test
    public void testSingleToken() {
        final Try<Node> nodeOptional = boolExpressionBoolParser.parseExpression("a");
        assertTrue(nodeOptional.isSuccess());
        assertTrue(nodeOptional.get() instanceof FieldNode);
        assertEquals(((FieldNode) nodeOptional.get()).getField(), "a");
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

    @Test
    public void testAddOperatorString() {
        final Try<Node> nodeOptional = boolExpressionBoolParser.parseExpression("a + b");
        assertTrue(nodeOptional.isSuccess());
        assertEquals(nodeOptional.get().getTokenType(), NodeType.ARITHMETIC);
        assertEquals(((FieldNode) ((ArithmeticNode) nodeOptional.get()).getLeft()).getField(), "a");
        assertEquals(((FieldNode) ((ArithmeticNode) nodeOptional.get()).getRight()).getField(), "b");
        assertEquals(((ArithmeticNode) nodeOptional.get()).getOperator(), Operator.ADD);
    }

    @Test
    public void testAddOperatorInt() {
        final Try<Node> nodeOptional = boolExpressionBoolParser.parseExpression("20 + 5");
        assertTrue(nodeOptional.isSuccess());
        assertEquals(nodeOptional.get().getTokenType(), NodeType.ARITHMETIC);
        assertEquals(((UnaryNode) ((ArithmeticNode) nodeOptional.get()).getLeft()).getValue(), 20);
        assertEquals(((UnaryNode) ((ArithmeticNode) nodeOptional.get()).getLeft()).getDataType(), DataType.INTEGER);
        assertEquals(((UnaryNode) ((ArithmeticNode) nodeOptional.get()).getRight()).getValue(), 5);
        assertEquals(((UnaryNode) ((ArithmeticNode) nodeOptional.get()).getRight()).getDataType(), DataType.INTEGER);
        assertEquals(((ArithmeticNode) nodeOptional.get()).getOperator(), Operator.ADD);
    }

    @Test
    public void testAddOperatorDecimal() {
        final Try<Node> nodeOptional = boolExpressionBoolParser.parseExpression("20.5 + 5");
        assertTrue(nodeOptional.isSuccess());
        assertEquals(nodeOptional.get().getTokenType(), NodeType.ARITHMETIC);
        assertEquals(((UnaryNode) ((ArithmeticNode) nodeOptional.get()).getLeft()).getValue(), new BigDecimal("20.5"));
        assertEquals(((UnaryNode) ((ArithmeticNode) nodeOptional.get()).getLeft()).getDataType(), DataType.DECIMAL);
        assertEquals(((UnaryNode) ((ArithmeticNode) nodeOptional.get()).getRight()).getValue(), 5);
        assertEquals(((UnaryNode) ((ArithmeticNode) nodeOptional.get()).getRight()).getDataType(), DataType.INTEGER);
        assertEquals(((ArithmeticNode) nodeOptional.get()).getOperator(), Operator.ADD);
    }

    @Test
    public void testArithmeticArrayFunction() {
        final Try<Node> nodeOptional = boolExpressionBoolParser.parseExpression("min (1,2,3)");
        assertTrue(nodeOptional.isSuccess());
        assertEquals(nodeOptional.get().getTokenType(), NodeType.ARITHMETIC_FUNCTION);
        assertEquals(((ArithmeticFunctionNode) nodeOptional.get()).getFunctionType().name(), "MIN");
        assertEquals(((ArithmeticFunctionNode) nodeOptional.get()).getItems().size(), 3);
        assertEquals(((UnaryNode) ((ArithmeticFunctionNode) nodeOptional.get()).getItems().get(0)).getDataType(), DataType.INTEGER);
        assertEquals(((UnaryNode) ((ArithmeticFunctionNode) nodeOptional.get()).getItems().get(0)).getValue(), 1);
        assertEquals(((UnaryNode) ((ArithmeticFunctionNode) nodeOptional.get()).getItems().get(1)).getDataType(), DataType.INTEGER);
        assertEquals(((UnaryNode) ((ArithmeticFunctionNode) nodeOptional.get()).getItems().get(1)).getValue(), 2);
        assertEquals(((UnaryNode) ((ArithmeticFunctionNode) nodeOptional.get()).getItems().get(2)).getDataType(), DataType.INTEGER);
        assertEquals(((UnaryNode) ((ArithmeticFunctionNode) nodeOptional.get()).getItems().get(2)).getValue(), 3);
    }

    @Test
    public void testArithmeticArrayFunctionWithSubstitution() {
        final Try<Node> nodeOptional = boolExpressionBoolParser.parseExpression("min(abc)");
        assertTrue(nodeOptional.isSuccess());
        assertEquals(nodeOptional.get().getTokenType(), NodeType.ARITHMETIC_FUNCTION);
        assertEquals(((ArithmeticFunctionNode) nodeOptional.get()).getFunctionType().name(), "MIN");
        assertEquals(((ArithmeticFunctionNode) nodeOptional.get()).getItems().size(), 1);
        assertEquals(((FieldNode) ((ArithmeticFunctionNode) nodeOptional.get()).getItems().get(0)).getField(), "abc");
    }

    @Test
    public void testArithmeticArrayFunctionWithError() {
        final Try<Node> nodeOptional = boolExpressionBoolParser.parseExpression("min abc");
        assertTrue(nodeOptional.isFailure());
    }

    @Test
    public void testAddOperatorBool() {
        final Try<Node> nodeOptional = boolExpressionBoolParser.parseExpression("false + 5");
        assertTrue(nodeOptional.isSuccess());
        assertEquals(nodeOptional.get().getTokenType(), NodeType.ARITHMETIC);
        assertEquals(((UnaryNode) ((ArithmeticNode) nodeOptional.get()).getLeft()).getValue(), false);
        assertEquals(((UnaryNode) ((ArithmeticNode) nodeOptional.get()).getLeft()).getDataType(), DataType.BOOLEAN);
        assertEquals(((UnaryNode) ((ArithmeticNode) nodeOptional.get()).getRight()).getValue(), 5);
        assertEquals(((UnaryNode) ((ArithmeticNode) nodeOptional.get()).getRight()).getDataType(), DataType.INTEGER);
        assertEquals(((ArithmeticNode) nodeOptional.get()).getOperator(), Operator.ADD);
    }

    @Test
    public void testComparisonWithArithmetic() {
        final Try<Node> nodeOptional = boolExpressionBoolParser.parseExpression("a > (10 + 20)");
        assertTrue(nodeOptional.isSuccess());
        assertEquals(nodeOptional.get().getTokenType(), NodeType.COMPARISON);
        assertEquals(((FieldNode)(((ComparisonNode) nodeOptional.get()).getLeft())).getField(), "a");
        final ArithmeticNode arithmeticNode = (ArithmeticNode) ((ComparisonNode) nodeOptional.get()).getRight();
        assertEquals(((UnaryNode) (arithmeticNode.getLeft())).getValue(), 10);
        assertEquals(((UnaryNode) (arithmeticNode.getLeft())).getDataType(), DataType.INTEGER);
        assertEquals(((UnaryNode) (arithmeticNode.getRight())).getValue(), 20);
        assertEquals(((UnaryNode) (arithmeticNode.getRight())).getDataType(), DataType.INTEGER);
        assertEquals(arithmeticNode.getOperator(), Operator.ADD);
    }

    private void verifyUnaryToken(final Node node, final Object value, final DataType dataType) {
        assertEquals(node.getTokenType().name(), NodeType.UNARY.name());
        assertEquals(((UnaryNode)node).getValue(), value);
        assertEquals(((UnaryNode)node).getDataType(), dataType);
    }

    private void verifyFieldToken(final Node node, final Object value) {
        assertEquals(node.getTokenType().name(), NodeType.FIELD.name());
        assertEquals(((FieldNode)node).getField(), value);
    }

    private void verifyComparisonToken(final Node node, final String field, final Operator operator) {
        assertEquals(node.getTokenType().name(), NodeType.COMPARISON.name());
        assertEquals(((FieldNode)((ComparisonNode)node).getLeft()).getField(), field);
        assertEquals(((ComparisonNode)node).getOperator(), operator);
    }

    private void verifyNumericRangeToken(final NumericRangeNode numericRangeToken, final String field, final Object fromValue, final Object toValue) {
        assertEquals(numericRangeToken.getTokenType().name(), NodeType.NUMERIC_RANGE.name());
        assertEquals(numericRangeToken.getField(), field);
        assertEquals(numericRangeToken.getFromValue(), fromValue);
        assertEquals(numericRangeToken.getToValue(), toValue);
    }
}

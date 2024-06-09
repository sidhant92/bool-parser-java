package com.github.sidhant92.boolparser.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import com.github.sidhant92.boolparser.exception.DataNotFoundException;
import com.github.sidhant92.boolparser.exception.InvalidDataType;
import com.github.sidhant92.boolparser.exception.InvalidExpressionException;
import com.github.sidhant92.boolparser.exception.InvalidUnaryOperand;
import com.github.sidhant92.boolparser.exception.UnsupportedToken;
import com.github.sidhant92.boolparser.parser.antlr.BoolParser;
import io.vavr.control.Try;

/**
 * @author sidhant.aggarwal
 * @since 07/02/2023
 */
public class ArithmeticExpressionEvaluatorTest {
    private final ArithmeticExpressionEvaluator arithmeticExpressionEvaluator = new ArithmeticExpressionEvaluator(new BoolParser());

    @Test
    public void testSimpleAddOperationWithoutVariable() {
        final Map<String, Object> data = new HashMap<>();
        final Try<Object> resultOptional = arithmeticExpressionEvaluator.evaluate("5 + 5", data);
        assertTrue(resultOptional.isSuccess());
        assertEquals(resultOptional.get(), 10);
    }

    @Test
    public void testSimpleSubtractOperationWithoutVariable() {
        final Map<String, Object> data = new HashMap<>();
        final Try<Object> resultOptional = arithmeticExpressionEvaluator.evaluate("5 - 4", data);
        assertTrue(resultOptional.isSuccess());
        assertEquals(resultOptional.get(), 1);
    }

    @Test
    public void testSimpleMultiplyOperationWithoutVariable() {
        final Map<String, Object> data = new HashMap<>();
        final Try<Object> resultOptional = arithmeticExpressionEvaluator.evaluate("5 * 5", data);
        assertTrue(resultOptional.isSuccess());
        assertEquals(resultOptional.get(), 25);
    }

    @Test
    public void testSimpleDivideOperationWithoutVariable() {
        final Map<String, Object> data = new HashMap<>();
        final Try<Object> resultOptional = arithmeticExpressionEvaluator.evaluate("5 / 5", data);
        assertTrue(resultOptional.isSuccess());
        assertEquals(resultOptional.get(), 1);
    }

    @Test
    public void testSimpleDivideOperationWithoutVariable1() {
        final Map<String, Object> data = new HashMap<>();
        final Try<Object> resultOptional = arithmeticExpressionEvaluator.evaluate("5 / 4", data);
        assertTrue(resultOptional.isSuccess());
        assertEquals(resultOptional.get(), 1.25);
    }

    @Test
    public void testSimpleModulusOperationWithoutVariable() {
        final Map<String, Object> data = new HashMap<>();
        final Try<Object> resultOptional = arithmeticExpressionEvaluator.evaluate("10 % 4", data);
        assertTrue(resultOptional.isSuccess());
        assertEquals(resultOptional.get(), 2);
    }

    @Test
    public void testSimpleExponentOperationWithoutVariable() {
        final Map<String, Object> data = new HashMap<>();
        final Try<Object> resultOptional = arithmeticExpressionEvaluator.evaluate("2 ^ 4", data);
        assertTrue(resultOptional.isSuccess());
        assertEquals(resultOptional.get(), 16);
    }

    @Test
    public void testSimpleAddOperationWithVariable() {
        final Map<String, Object> data = new HashMap<>();
        data.put("a", 10);
        final Try<Object> resultOptional = arithmeticExpressionEvaluator.evaluate("a + 5", data);
        assertTrue(resultOptional.isSuccess());
        assertEquals(resultOptional.get(), 15);
    }

    @Test
    public void testSimpleSubtractOperationWithVariable() {
        final Map<String, Object> data = new HashMap<>();
        data.put("a", 10);
        final Try<Object> resultOptional = arithmeticExpressionEvaluator.evaluate("a - 5", data);
        assertTrue(resultOptional.isSuccess());
        assertEquals(resultOptional.get(), 5);
    }

    @Test
    public void testSimpleSubtractOperationWithTwoVariable() {
        final Map<String, Object> data = new HashMap<>();
        data.put("a", 10);
        data.put("b", 5);
        final Try<Object> resultOptional = arithmeticExpressionEvaluator.evaluate("a-b", data);
        assertTrue(resultOptional.isSuccess());
        assertEquals(resultOptional.get(), 5);
    }

    @Test
    public void testStringEvaluation() {
        final Map<String, Object> data = new HashMap<>();
        data.put("a", 10);
        data.put("b", 5);
        final Try<Object> resultOptional = arithmeticExpressionEvaluator.evaluate("\"a-b\"", data);
        assertTrue(resultOptional.isSuccess());
        assertEquals(resultOptional.get(), "a-b");
    }

    @Test
    public void testSimpleMultiplyOperationWithVariable() {
        final Map<String, Object> data = new HashMap<>();
        data.put("a", 10);
        final Try<Object> resultOptional = arithmeticExpressionEvaluator.evaluate("a * 5", data);
        assertTrue(resultOptional.isSuccess());
        assertEquals(resultOptional.get(), 50);
    }

    @Test
    public void testSimpleDivideOperationWithVariable() {
        final Map<String, Object> data = new HashMap<>();
        data.put("a", 10);
        final Try<Object> resultOptional = arithmeticExpressionEvaluator.evaluate("a / 5", data);
        assertTrue(resultOptional.isSuccess());
        assertEquals(resultOptional.get(), 2);
    }

    @Test
    public void testSimpleModulusOperationWithVariable() {
        final Map<String, Object> data = new HashMap<>();
        data.put("a", 10);
        final Try<Object> resultOptional = arithmeticExpressionEvaluator.evaluate("a % 5", data);
        assertTrue(resultOptional.isSuccess());
        assertEquals(resultOptional.get(), 0);
    }

    @Test
    public void testSimpleExponentOperationWithVariable() {
        final Map<String, Object> data = new HashMap<>();
        data.put("a", 10);
        final Try<Object> resultOptional = arithmeticExpressionEvaluator.evaluate("a ^ 5", data);
        assertTrue(resultOptional.isSuccess());
        assertEquals(resultOptional.get(), 100000);
    }

    @Test
    public void testStringConcatenation() {
        final Map<String, Object> data = new HashMap<>();
        data.put("a", "first");
        data.put("b", "second");
        final Try<Object> resultOptional = arithmeticExpressionEvaluator.evaluate("a + \" \" + b", data);
        assertTrue(resultOptional.isSuccess());
        assertEquals(resultOptional.get(), "first second");
    }

    @Test
    public void testExpressionWithParenthesisNoVariable() {
        final Map<String, Object> data = new HashMap<>();
        data.put("a", 10);
        final Try<Object> resultOptional = arithmeticExpressionEvaluator.evaluate("((5 * 2) + 10) * 2 + (1 + 3 * (10 / 2))", data);
        assertTrue(resultOptional.isSuccess());
        assertEquals(resultOptional.get(), 56);
    }

    @Test
    public void testExpressionWithParenthesisWithVariable() {
        final Map<String, Object> data = new HashMap<>();
        data.put("a", 10);
        final Try<Object> resultOptional = arithmeticExpressionEvaluator.evaluate("((5 * 2) + a) * 2 + (1 + 3 * (a / 2))", data);
        assertTrue(resultOptional.isSuccess());
        assertEquals(resultOptional.get(), 56);
    }

    @Test
    public void testExpressionWithComparison() {
        final Map<String, Object> data = new HashMap<>();
        data.put("a", 10);
        final Try<Object> resultOptional = arithmeticExpressionEvaluator.evaluate("(a > (4 + 1))", data);
        assertTrue(resultOptional.isFailure());
        assertTrue(resultOptional.getCause() instanceof UnsupportedToken);
    }

    @Test
    public void testExpressionWithUnaryNode() {
        final Map<String, Object> data = new HashMap<>();
        data.put("a", 10);
        final Try<Object> resultOptional = arithmeticExpressionEvaluator.evaluate("- a", data);
        assertTrue(resultOptional.isSuccess());
        assertEquals(resultOptional.get(), -10);
    }

    @Test
    public void testExpressionWithNestedUnaryNode() {
        final Map<String, Object> data = new HashMap<>();
        data.put("a", 10);
        final Try<Object> resultOptional = arithmeticExpressionEvaluator.evaluate("- (a + 5)", data);
        assertTrue(resultOptional.isSuccess());
        assertEquals(resultOptional.get(), -15);
    }

    @Test
    public void testExpressionWithNestedUnaryNode1() {
        final Map<String, Object> data = new HashMap<>();
        data.put("a", "test");
        final Try<Object> resultOptional = arithmeticExpressionEvaluator.evaluate("a", data);
        assertTrue(resultOptional.isSuccess());
        assertEquals(resultOptional.get(), "test");
    }

    @Test
    public void testUnaryNode() {
        final Map<String, Object> data = new HashMap<>();
        data.put("a", 10);
        final Try<Object> resultOptional = arithmeticExpressionEvaluator.evaluate("-a", data);
        assertTrue(resultOptional.isSuccess());
        assertEquals(resultOptional.get(), -10);
    }

    @Test
    public void testDoubleUnaryNode() {
        final Map<String, Object> data = new HashMap<>();
        data.put("a", 10);
        final Try<Object> resultOptional = arithmeticExpressionEvaluator.evaluate("--a", data);
        assertTrue(resultOptional.isSuccess());
        assertEquals(resultOptional.get(), 10);
    }

    @Test
    public void testMinArithmeticFunctionInteger() {
        final Map<String, Object> data = new HashMap<>();
        data.put("a", 10);
        final Try<Object> resultOptional = arithmeticExpressionEvaluator.evaluate("min (1,2,3)", data);
        assertTrue(resultOptional.isSuccess());
        assertEquals(resultOptional.get(), 1);
    }

    @Test
    public void testMinArithmeticFunctionDouble() {
        final Map<String, Object> data = new HashMap<>();
        data.put("a", 10);
        final Try<Object> resultOptional = arithmeticExpressionEvaluator.evaluate("min (1,2,3,4.5)", data);
        assertTrue(resultOptional.isSuccess());
        assertEquals(resultOptional.get(), 1);
    }

    @Test
    public void testMaxArithmeticFunctionInteger() {
        final Map<String, Object> data = new HashMap<>();
        data.put("a", 10);
        final Try<Object> resultOptional = arithmeticExpressionEvaluator.evaluate("max (1,2,3)", data);
        assertTrue(resultOptional.isSuccess());
        assertEquals(resultOptional.get(), 3);
    }

    @Test
    public void testMaxArithmeticFunctionDouble() {
        final Map<String, Object> data = new HashMap<>();
        data.put("a", 10);
        final Try<Object> resultOptional = arithmeticExpressionEvaluator.evaluate("max (1,2,3,4.5)", data);
        assertTrue(resultOptional.isSuccess());
        assertEquals(resultOptional.get(), 4.5d);
    }

    @Test
    public void testAvgArithmeticFunctionInteger() {
        final Map<String, Object> data = new HashMap<>();
        data.put("a", 10);
        final Try<Object> resultOptional = arithmeticExpressionEvaluator.evaluate("avg (1,2,3)", data);
        assertTrue(resultOptional.isSuccess());
        assertEquals(resultOptional.get(), 2);
    }

    @Test
    public void testAvgArithmeticFunctionDouble() {
        final Map<String, Object> data = new HashMap<>();
        data.put("a", 10);
        final Try<Object> resultOptional = arithmeticExpressionEvaluator.evaluate("avg (1,2,3,4.5)", data);
        assertTrue(resultOptional.isSuccess());
        assertEquals(resultOptional.get(), 2.625);
    }

    @Test
    public void testSumArithmeticFunctionInteger() {
        final Map<String, Object> data = new HashMap<>();
        data.put("a", 10);
        final Try<Object> resultOptional = arithmeticExpressionEvaluator.evaluate("sum (1,2,3)", data);
        assertTrue(resultOptional.isSuccess());
        assertEquals(resultOptional.get(), 6);
    }

    @Test
    public void testSumArithmeticFunctionDouble() {
        final Map<String, Object> data = new HashMap<>();
        data.put("a", 10);
        final Try<Object> resultOptional = arithmeticExpressionEvaluator.evaluate("sum (1,2,3,4.5)", data);
        assertTrue(resultOptional.isSuccess());
        assertEquals(resultOptional.get(), 10.5);
    }

    @Test
    public void testMeanArithmeticFunctionInteger() {
        final Map<String, Object> data = new HashMap<>();
        data.put("a", 10);
        final Try<Object> resultOptional = arithmeticExpressionEvaluator.evaluate("mean (1,2,3)", data);
        assertTrue(resultOptional.isSuccess());
        assertEquals(resultOptional.get(), 2);
    }

    @Test
    public void testMeanArithmeticFunctionDouble() {
        final Map<String, Object> data = new HashMap<>();
        data.put("a", 10);
        final Try<Object> resultOptional = arithmeticExpressionEvaluator.evaluate("mean (1,2,3,4.5)", data);
        assertTrue(resultOptional.isSuccess());
        assertEquals(resultOptional.get(), 2.625);
    }

    @Test
    public void testModeArithmeticFunctionIntegerVariable() {
        final Map<String, Object> data = new HashMap<>();
        final List<Integer> numbers = new ArrayList<>();
        numbers.add(1);
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
        data.put("a", numbers);
        final Try<Object> resultOptional = arithmeticExpressionEvaluator.evaluate("mode (a)", data);
        assertTrue(resultOptional.isSuccess());
        assertEquals(resultOptional.get(), 1);
    }

    @Test
    public void testModeArithmeticFunctionDouble() {
        final Map<String, Object> data = new HashMap<>();
        data.put("a", 10);
        final Try<Object> resultOptional = arithmeticExpressionEvaluator.evaluate("mode (1,2,3,4.5)", data);
        assertTrue(resultOptional.isSuccess());
        assertEquals(resultOptional.get(), 1);
    }

    @Test
    public void testMedianArithmeticFunctionInteger() {
        final Map<String, Object> data = new HashMap<>();
        data.put("a", 10);
        final Try<Object> resultOptional = arithmeticExpressionEvaluator.evaluate("median (3, 13, 2, 34, 11, 17, 27, 47)", data);
        assertTrue(resultOptional.isSuccess());
        assertEquals(resultOptional.get(), 15);
    }

    @Test
    public void testIntArithmeticFunction() {
        final Map<String, Object> data = new HashMap<>();
        data.put("a", 10);
        final Try<Object> resultOptional = arithmeticExpressionEvaluator.evaluate("int (2.7)", data);
        assertTrue(resultOptional.isSuccess());
        assertEquals(resultOptional.get(), 2);
    }

    @Test
    public void testIntArithmeticFunctionWithVariable() {
        final Map<String, Object> data = new HashMap<>();
        data.put("a", 2.7);
        final Try<Object> resultOptional = arithmeticExpressionEvaluator.evaluate("int (a)", data);
        assertTrue(resultOptional.isSuccess());
        assertEquals(resultOptional.get(), 2);
    }

    @Test
    public void testLenArithmeticFunctionString() {
        final Map<String, Object> data = new HashMap<>();
        data.put("a", "lorem");
        final Try<Object> resultOptional = arithmeticExpressionEvaluator.evaluate("len (a)", data);
        assertTrue(resultOptional.isSuccess());
        assertEquals(resultOptional.get(), 5);
    }

    @Test
    public void testLenArithmeticFunctionIntegerVariable() {
        final Map<String, Object> data = new HashMap<>();
        final List<Integer> numbers = new ArrayList<>();
        numbers.add(1);
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
        data.put("a", numbers);
        final Try<Object> resultOptional = arithmeticExpressionEvaluator.evaluate("len (a)", data);
        assertTrue(resultOptional.isSuccess());
        assertEquals(resultOptional.get(), 4);
    }

    @Test
    public void testNestedFunctions() {
        final Map<String, Object> data = new HashMap<>();
        data.put("a", 2.7);
        final Try<Object> resultOptional = arithmeticExpressionEvaluator.evaluate("max(1,2,min(5,7,87))", data);
        assertTrue(resultOptional.isSuccess());
        assertEquals(resultOptional.get(), 5);
    }

    @Test
    public void testNestedFunctions1() {
        final Map<String, Object> data = new HashMap<>();
        data.put("a", 2.7);
        final Try<Object> resultOptional = arithmeticExpressionEvaluator.evaluate("max(1,2,min(5,7,87,min(1,2)))", data);
        assertTrue(resultOptional.isSuccess());
        assertEquals(resultOptional.get(), 2);
    }
}

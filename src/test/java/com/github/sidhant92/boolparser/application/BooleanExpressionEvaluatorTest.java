package com.github.sidhant92.boolparser.application;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;
import com.github.sidhant92.boolparser.exception.InvalidUnaryOperand;
import com.github.sidhant92.boolparser.parser.antlr.BoolParser;
import io.vavr.control.Try;

/**
 * @author sidhant.aggarwal
 * @since 07/02/2023
 */
public class BooleanExpressionEvaluatorTest {
    private final BooleanExpressionEvaluator booleanExpressionEvaluator = new BooleanExpressionEvaluator(new BoolParser());


    @Test
    public void testUnaryExpressionWithField() {
        final Map<String, Object> data = new HashMap<>();
        data.put("is_allowed", true);
        final Try<Boolean> booleanOptional = booleanExpressionEvaluator.evaluate("not is_allowed", data);
        assertTrue(booleanOptional.isSuccess());
        assertFalse(booleanOptional.get());
    }

    @Test
    public void testUnaryExpressionWithBoolean() {
        final Map<String, Object> data = new HashMap<>();
        data.put("is_allowed", true);
        final Try<Boolean> booleanOptional = booleanExpressionEvaluator.evaluate("not false", data);
        assertTrue(booleanOptional.isSuccess());
        assertTrue(booleanOptional.get());
    }

    @Test
    public void testInvalidUnaryOperation() {
        final Map<String, Object> data = new HashMap<>();
        data.put("is_allowed", 123);
        final Try<Boolean> booleanOptional = booleanExpressionEvaluator.evaluate("not is_allowed", data);
        assertTrue(booleanOptional.isFailure());
        assertTrue(booleanOptional.getCause() instanceof InvalidUnaryOperand);
    }

    @Test
    public void testSimpleTrueCorrectExpression() {
        final Map<String, Object> data = new HashMap<>();
        data.put("name", "abc");
        final Try<Boolean> booleanOptional = booleanExpressionEvaluator.evaluate("name = abc", data);
        assertTrue(booleanOptional.isSuccess());
        assertTrue(booleanOptional.get());
    }

    @Test
    public void testSimpleTrueCorrectExpressions() {
        final Map<String, Object> data = new HashMap<>();
        data.put("name", "abc-");
        final Try<Boolean> booleanOptional = booleanExpressionEvaluator.evaluate("name = abc-", data);
        assertTrue(booleanOptional.isSuccess());
        assertTrue(booleanOptional.get());
    }

    @Test
    public void testSimpleFalseIncorrectExpression() {
        final Map<String, Object> data = new HashMap<>();
        data.put("name", "def");
        final Try<Boolean> booleanOptional = booleanExpressionEvaluator.evaluate("name = abc", data);
        assertTrue(booleanOptional.isSuccess());
        assertFalse(booleanOptional.get());
    }

    @Test
    public void testNumericEqualCorrectExpression() {
        final Map<String, Object> data = new HashMap<>();
        data.put("age", 24);
        final Try<Boolean> booleanOptional = booleanExpressionEvaluator.evaluate("age = 24", data);
        assertTrue(booleanOptional.isSuccess());
        assertTrue(booleanOptional.get());
    }

    @Test
    public void testNumericEqualIncorrectExpression() {
        final Map<String, Object> data = new HashMap<>();
        data.put("age", 26);
        final Try<Boolean> booleanOptional = booleanExpressionEvaluator.evaluate("age = 24", data);
        assertTrue(booleanOptional.isSuccess());
        assertFalse(booleanOptional.get());
    }

    @Test
    public void testNumericGreaterThanCorrectExpression() {
        final Map<String, Object> data = new HashMap<>();
        data.put("age", 24);
        final Try<Boolean> booleanOptional = booleanExpressionEvaluator.evaluate("age > 20", data);
        assertTrue(booleanOptional.isSuccess());
        assertTrue(booleanOptional.get());
    }

    @Test
    public void testNumericGreaterThanIncorrectExpression() {
        final Map<String, Object> data = new HashMap<>();
        data.put("age", 26);
        final Try<Boolean> booleanOptional = booleanExpressionEvaluator.evaluate("age > 27", data);
        assertTrue(booleanOptional.isSuccess());
        assertFalse(booleanOptional.get());
    }

    @Test
    public void testNumericGreaterThanEqualCorrectExpression() {
        final Map<String, Object> data = new HashMap<>();
        data.put("age", 24);
        final Try<Boolean> booleanOptional = booleanExpressionEvaluator.evaluate("age >= 20", data);
        assertTrue(booleanOptional.isSuccess());
        assertTrue(booleanOptional.get());
    }

    @Test
    public void testNumericGreaterThanEqualIncorrectExpression() {
        final Map<String, Object> data = new HashMap<>();
        data.put("age", 26);
        final Try<Boolean> booleanOptional = booleanExpressionEvaluator.evaluate("age >= 27", data);
        assertTrue(booleanOptional.isSuccess());
        assertFalse(booleanOptional.get());
    }

    @Test
    public void testNumericLessThanEqualCorrectExpression() {
        final Map<String, Object> data = new HashMap<>();
        data.put("age", 24);
        final Try<Boolean> booleanOptional = booleanExpressionEvaluator.evaluate("age < 30", data);
        assertTrue(booleanOptional.isSuccess());
        assertTrue(booleanOptional.get());
    }

    @Test
    public void testNumericLessThanEqualIncorrectExpression() {
        final Map<String, Object> data = new HashMap<>();
        data.put("age", 26);
        final Try<Boolean> booleanOptional = booleanExpressionEvaluator.evaluate("age <= 20", data);
        assertTrue(booleanOptional.isSuccess());
        assertFalse(booleanOptional.get());
    }

    @Test
    public void testNumericNotEqualCorrectExpression() {
        final Map<String, Object> data = new HashMap<>();
        data.put("age", 24);
        final Try<Boolean> booleanOptional = booleanExpressionEvaluator.evaluate("age != 30", data);
        assertTrue(booleanOptional.isSuccess());
        assertTrue(booleanOptional.get());
    }

    @Test
    public void testNumericNotEqualIncorrectExpression() {
        final Map<String, Object> data = new HashMap<>();
        data.put("age", 26);
        final Try<Boolean> booleanOptional = booleanExpressionEvaluator.evaluate("age != 26", data);
        assertTrue(booleanOptional.isSuccess());
        assertFalse(booleanOptional.get());
    }

    @Test
    public void testSimpleNotStringExpression() {
        final Map<String, Object> data = new HashMap<>();
        data.put("name", "abc");
        final Try<Boolean> booleanOptional = booleanExpressionEvaluator.evaluate("NOT (name = abc)", data);
        assertTrue(booleanOptional.isSuccess());
        assertFalse(booleanOptional.get());
    }

    @Test
    public void testSimpleNotNumericExpression() {
        final Map<String, Object> data = new HashMap<>();
        data.put("age", 24);
        final Try<Boolean> booleanOptional = booleanExpressionEvaluator.evaluate("NOT (age = 24)", data);
        assertTrue(booleanOptional.isSuccess());
        assertFalse(booleanOptional.get());
    }

    @Test
    public void testComplexAndCorrectExpression() {
        final Map<String, Object> data = new HashMap<>();
        data.put("age", 25);
        data.put("name", "sid");
        final Try<Boolean> booleanOptional = booleanExpressionEvaluator.evaluate("name = sid AND age = 25", data);
        assertTrue(booleanOptional.isSuccess());
        assertTrue(booleanOptional.get());
    }

    @Test
    public void testComplexAndIncorrectExpression() {
        final Map<String, Object> data = new HashMap<>();
        data.put("age", 25);
        data.put("name", "sid");
        final Try<Boolean> booleanOptional = booleanExpressionEvaluator.evaluate("name = sid AND age = 23", data);
        assertTrue(booleanOptional.isSuccess());
        assertFalse(booleanOptional.get());
    }

    @Test
    public void testComplexORCorrectExpression() {
        final Map<String, Object> data = new HashMap<>();
        data.put("age", 25);
        data.put("name", "sid");
        final Try<Boolean> booleanOptional = booleanExpressionEvaluator.evaluate("name = sid OR age = 23", data);
        assertTrue(booleanOptional.isSuccess());
        assertTrue(booleanOptional.get());
    }

    @Test
    public void testComplexORIncorrectExpression() {
        final Map<String, Object> data = new HashMap<>();
        data.put("age", 25);
        data.put("name", "sidh");
        final Try<Boolean> booleanOptional = booleanExpressionEvaluator.evaluate("name = sid OR age = 23", data);
        assertTrue(booleanOptional.isSuccess());
        assertFalse(booleanOptional.get());
    }

    @Test
    public void testCorrectComplexExpressionWithParenthesis() {
        final Map<String, Object> data = new HashMap<>();
        data.put("age", 25);
        data.put("name", "sid");
        data.put("num", 45);
        final Try<Boolean> booleanOptional = booleanExpressionEvaluator.evaluate("name = sid AND (age = 25 OR num = 44)", data);
        assertTrue(booleanOptional.isSuccess());
        assertTrue(booleanOptional.get());
    }

    @Test
    public void testNegativeInClauseForIntegers() {
        final Map<String, Object> data = new HashMap<>();
        data.put("age", 25);
        final Try<Boolean> booleanOptional = booleanExpressionEvaluator.evaluate("age in (26,56,34)", data);
        assertTrue(booleanOptional.isSuccess());
        assertFalse(booleanOptional.get());
    }

    @Test
    public void testPositiveInClauseForIntegers() {
        final Map<String, Object> data = new HashMap<>();
        data.put("age", 25);
        final Try<Boolean> booleanOptional = booleanExpressionEvaluator.evaluate("age in (26,25,34)", data);
        assertTrue(booleanOptional.isSuccess());
        assertTrue(booleanOptional.get());
    }

    @Test
    public void testPositiveInClauseForDecimals() {
        final Map<String, Object> data = new HashMap<>();
        data.put("num", 25.3);
        final Try<Boolean> booleanOptional = booleanExpressionEvaluator.evaluate("num in (26,25,34, 25.3)", data);
        assertTrue(booleanOptional.isSuccess());
        assertTrue(booleanOptional.get());
    }

    @Test
    public void testNegativeInClauseForStrings() {
        final Map<String, Object> data = new HashMap<>();
        data.put("name", "test");
        final Try<Boolean> booleanOptional = booleanExpressionEvaluator.evaluate("name in (tes, abc)", data);
        assertTrue(booleanOptional.isSuccess());
        assertFalse(booleanOptional.get());
    }

    @Test
    public void testPositiveInClauseForStrings() {
        final Map<String, Object> data = new HashMap<>();
        data.put("name", "test");
        final Try<Boolean> booleanOptional = booleanExpressionEvaluator.evaluate("name in (abc, test)", data);
        assertTrue(booleanOptional.isSuccess());
        assertTrue(booleanOptional.get());
    }

    @Test
    public void testCorrectComplexExpressionWithParenthesis1() {
        final Map<String, Object> data = new HashMap<>();
        data.put("age", 25);
        data.put("name", "sid");
        data.put("num", 45);
        final Try<Boolean> booleanOptional = booleanExpressionEvaluator.evaluate("name = sidh OR (age = 25 AND num = 45)", data);
        assertTrue(booleanOptional.isSuccess());
        assertTrue(booleanOptional.get());
    }

    @Test
    public void testIncorrectComplexExpressionWithParenthesis() {
        final Map<String, Object> data = new HashMap<>();
        data.put("age", 25);
        data.put("name", "sid");
        data.put("num", 45);
        final Try<Boolean> booleanOptional = booleanExpressionEvaluator.evaluate("name = sid AND (age = 23 OR num = 44)", data);
        assertTrue(booleanOptional.isSuccess());
        assertFalse(booleanOptional.get());
    }

    @Test
    public void testWrongDataType() {
        final Map<String, Object> data = new HashMap<>();
        data.put("age", 24);
        final Try<Boolean> booleanOptional = booleanExpressionEvaluator.evaluate("age = dsf", data);
        assertTrue(booleanOptional.isSuccess());
        assertFalse(booleanOptional.get());
    }

    @Test
    public void testWrongDataType1() {
        final Map<String, Object> data = new HashMap<>();
        data.put("age", "sf");
        final Try<Boolean> booleanOptional = booleanExpressionEvaluator.evaluate("age = 24", data);
        assertTrue(booleanOptional.isSuccess());
        assertFalse(booleanOptional.get());
    }

    @Test
    public void testKeyMissing() {
        final Map<String, Object> data = new HashMap<>();
        data.put("agee", 34);
        final Try<Boolean> booleanOptional = booleanExpressionEvaluator.evaluate("age = 24", data);
        assertTrue(booleanOptional.isSuccess());
        assertFalse(booleanOptional.get());
    }

    @Test
    public void testAppVersionGraterThan() {
        final Map<String, Object> data = new HashMap<>();
        data.put("app_version", "1.0.6");
        final Try<Boolean> booleanOptional = booleanExpressionEvaluator.evaluate("app_version > 1.0.5", data);
        assertTrue(booleanOptional.isSuccess());
        assertTrue(booleanOptional.get());
    }

    @Test
    public void testAppVersionGraterThan1() {
        final Map<String, Object> data = new HashMap<>();
        data.put("app_version", "1.0.6.15");
        final Try<Boolean> booleanOptional = booleanExpressionEvaluator.evaluate("app_version > 1.0.6.14", data);
        assertTrue(booleanOptional.isSuccess());
        assertTrue(booleanOptional.get());
    }

    @Test
    public void testAppVersionGraterThan2() {
        final Map<String, Object> data = new HashMap<>();
        data.put("app_version", "1.54");
        final Try<Boolean> booleanOptional = booleanExpressionEvaluator.evaluate("app_version > 1.53", data);
        assertTrue(booleanOptional.isSuccess());
        assertTrue(booleanOptional.get());
    }

    @Test
    public void testAppVersionGraterThanEqualTo() {
        final Map<String, Object> data = new HashMap<>();
        data.put("app_version", "1.0.6");
        final Try<Boolean> booleanOptional = booleanExpressionEvaluator.evaluate("app_version >= 1.0.6", data);
        assertTrue(booleanOptional.isSuccess());
        assertTrue(booleanOptional.get());
    }

    @Test
    public void testAppVersionLessThan() {
        final Map<String, Object> data = new HashMap<>();
        data.put("app_version", "1.5.9");
        final Try<Boolean> booleanOptional = booleanExpressionEvaluator.evaluate("app_version < 1.5.10", data);
        assertTrue(booleanOptional.isSuccess());
        assertTrue(booleanOptional.get());
    }

    @Test
    public void testAppVersionLessThanEqualTo() {
        final Map<String, Object> data = new HashMap<>();
        data.put("app_version", "1.5.9");
        final Try<Boolean> booleanOptional = booleanExpressionEvaluator.evaluate("app_version <= 1.5.9", data);
        assertTrue(booleanOptional.isSuccess());
        assertTrue(booleanOptional.get());
    }

    @Test
    public void testStringEqualityWithQuotes() {
        final Map<String, Object> data = new HashMap<>();
        data.put("name", "sidhant aggarwal");
        final Try<Boolean> booleanOptional = booleanExpressionEvaluator.evaluate("name = 'sidhant aggarwal'", data);
        assertTrue(booleanOptional.isSuccess());
        assertTrue(booleanOptional.get());
    }
}

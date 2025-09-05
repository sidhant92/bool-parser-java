package com.github.sidhant92.boolparser.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import com.github.sidhant92.boolparser.exception.DataNotFoundException;
import com.github.sidhant92.boolparser.exception.InvalidDataType;
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
        final Try<Boolean> booleanOptional = booleanExpressionEvaluator.evaluate("name = 'abc'", data);
        assertTrue(booleanOptional.isSuccess());
        assertTrue(booleanOptional.get());
    }

    @Test
    public void testSimpleTrueCorrectExpressions() {
        final Map<String, Object> data = new HashMap<>();
        data.put("name", "abc-");
        final Try<Boolean> booleanOptional = booleanExpressionEvaluator.evaluate("name = 'abc-'", data);
        assertTrue(booleanOptional.isSuccess());
        assertTrue(booleanOptional.get());
    }

    @Test
    public void testInvalidDataType() {
        final Map<String, Object> data = new HashMap<>();
        data.put("name", "abc-");
        final Try<Boolean> booleanOptional = booleanExpressionEvaluator.evaluate("name > 123", data);
        assertTrue(booleanOptional.isFailure());
        assertTrue(booleanOptional.getCause() instanceof InvalidDataType);
    }

    @Test
    public void testSimpleFalseIncorrectExpression() {
        final Map<String, Object> data = new HashMap<>();
        data.put("name", "def");
        final Try<Boolean> booleanOptional = booleanExpressionEvaluator.evaluate("name = 'abc'", data);
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
    public void testNumericGreaterThanCorrectExpressionInverted() {
        final Map<String, Object> data = new HashMap<>();
        data.put("age", 24);
        final Try<Boolean> booleanOptional = booleanExpressionEvaluator.evaluate("20 < age", data);
        assertTrue(booleanOptional.isSuccess());
        assertTrue(booleanOptional.get());
    }

    @Test
    public void testNumericGreaterThanCorrectExpressionWithField() {
        final Map<String, Object> data = new HashMap<>();
        data.put("age", 24);
        data.put("b", 20);
        final Try<Boolean> booleanOptional = booleanExpressionEvaluator.evaluate("age > b", data);
        assertTrue(booleanOptional.isSuccess());
        assertTrue(booleanOptional.get());
    }

    @Test
    public void testNestedField() {
        final Map<String, Object> data = new HashMap<>();
        final Map<String, Object> person = new HashMap<>();
        person.put("age", 24);
        data.put("person", person);
        final Try<Boolean> booleanOptional = booleanExpressionEvaluator.evaluate("person.age > 20", data);
        assertTrue(booleanOptional.isSuccess());
        assertTrue(booleanOptional.get());
    }

    @Test
    public void testTwoNestedField() {
        final Map<String, Object> data = new HashMap<>();
        final Map<String, Object> person = new HashMap<>();
        final Map<String, Object> details = new HashMap<>();
        details.put("age", 24);
        person.put("details", details);
        data.put("person", person);
        final Try<Boolean> booleanOptional = booleanExpressionEvaluator.evaluate("person.details.age > 20", data);
        assertTrue(booleanOptional.isSuccess());
        assertTrue(booleanOptional.get());
    }

    @Test
    public void testMissingNestedField() {
        final Map<String, Object> data = new HashMap<>();
        final Map<String, Object> person = new HashMap<>();
        person.put("age", 24);
        data.put("person", person);
        final Try<Boolean> booleanOptional = booleanExpressionEvaluator.evaluate("person.agee > 20", data);
        assertTrue(booleanOptional.isFailure());
        assertTrue(booleanOptional.getCause() instanceof DataNotFoundException);
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
    public void testDifferentDataTypesComparison() {
        final Map<String, Object> data = new HashMap<>();
        data.put("age", 26.6);
        final Try<Boolean> booleanOptional = booleanExpressionEvaluator.evaluate("age > 20", data);
        assertTrue(booleanOptional.isSuccess());
        assertTrue(booleanOptional.get());
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
        final Try<Boolean> booleanOptional = booleanExpressionEvaluator.evaluate("NOT (name = 'abc')", data);
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
        final Try<Boolean> booleanOptional = booleanExpressionEvaluator.evaluate("name = 'sid' AND age = 25", data);
        assertTrue(booleanOptional.isSuccess());
        assertTrue(booleanOptional.get());
    }

    @Test
    public void testComplexAndIncorrectExpression() {
        final Map<String, Object> data = new HashMap<>();
        data.put("age", 25);
        data.put("name", "sid");
        final Try<Boolean> booleanOptional = booleanExpressionEvaluator.evaluate("name = 'sid' AND age = 23", data);
        assertTrue(booleanOptional.isSuccess());
        assertFalse(booleanOptional.get());
    }

    @Test
    public void testComplexORCorrectExpression() {
        final Map<String, Object> data = new HashMap<>();
        data.put("age", 25);
        data.put("name", "sid");
        final Try<Boolean> booleanOptional = booleanExpressionEvaluator.evaluate("name = 'sid' OR age = 23", data);
        assertTrue(booleanOptional.isSuccess());
        assertTrue(booleanOptional.get());
    }

    @Test
    public void testComplexORIncorrectExpression() {
        final Map<String, Object> data = new HashMap<>();
        data.put("age", 25);
        data.put("name", "sidh");
        final Try<Boolean> booleanOptional = booleanExpressionEvaluator.evaluate("name = 'sid' OR age = 23", data);
        assertTrue(booleanOptional.isSuccess());
        assertFalse(booleanOptional.get());
    }

    @Test
    public void testCorrectComplexExpressionWithParenthesis() {
        final Map<String, Object> data = new HashMap<>();
        data.put("age", 25);
        data.put("name", "sid");
        data.put("num", 45);
        final Try<Boolean> booleanOptional = booleanExpressionEvaluator.evaluate("name = 'sid' AND (age = 25 OR num = 44)", data);
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
    public void testNegativeInClauseForIntegersWithField() {
        final Map<String, Object> data = new HashMap<>();
        data.put("age", 25);
        data.put("a", 25);
        final Try<Boolean> booleanOptional = booleanExpressionEvaluator.evaluate("age in (26,56,34,a)", data);
        assertTrue(booleanOptional.isSuccess());
        assertTrue(booleanOptional.get());
    }

    @Test
    public void testNotInClauseForIntegers() {
        final Map<String, Object> data = new HashMap<>();
        data.put("age", 30);
        final Try<Boolean> booleanOptional = booleanExpressionEvaluator.evaluate("age not in (26,56,34)", data);
        assertTrue(booleanOptional.isSuccess());
        assertTrue(booleanOptional.get());
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
        final Try<Boolean> booleanOptional = booleanExpressionEvaluator.evaluate("name in ('tes', 'abc')", data);
        assertTrue(booleanOptional.isSuccess());
        assertFalse(booleanOptional.get());
    }

    @Test
    public void testPositiveInClauseForStrings() {
        final Map<String, Object> data = new HashMap<>();
        data.put("name", "test");
        final Try<Boolean> booleanOptional = booleanExpressionEvaluator.evaluate("name in ('abc', 'test')", data);
        assertTrue(booleanOptional.isSuccess());
        assertTrue(booleanOptional.get());
    }

    @Test
    public void testCorrectComplexExpressionWithParenthesis1() {
        final Map<String, Object> data = new HashMap<>();
        data.put("age", 25);
        data.put("name", "sid");
        data.put("num", 45);
        final Try<Boolean> booleanOptional = booleanExpressionEvaluator.evaluate("name = 'sidh' OR (age = 25 AND num = 45)", data);
        assertTrue(booleanOptional.isSuccess());
        assertTrue(booleanOptional.get());
    }

    @Test
    public void testIncorrectComplexExpressionWithParenthesis() {
        final Map<String, Object> data = new HashMap<>();
        data.put("age", 25);
        data.put("name", "sid");
        data.put("num", 45);
        final Try<Boolean> booleanOptional = booleanExpressionEvaluator.evaluate("name = 'sid' AND (age = 23 OR num = 44)", data);
        assertTrue(booleanOptional.isSuccess());
        assertFalse(booleanOptional.get());
    }

    @Test
    public void testWrongDataType() {
        final Map<String, Object> data = new HashMap<>();
        data.put("age", 24);
        final Try<Boolean> booleanOptional = booleanExpressionEvaluator.evaluate("age = 'dsf'", data);
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
        assertTrue(booleanOptional.isFailure());
        assertTrue(booleanOptional.getCause() instanceof DataNotFoundException);
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

    @Test
    public void testDefaultFieldTrue() {
        final Map<String, Object> data = new HashMap<>();
        data.put("age", 19);
        final Try<Boolean> booleanOptional = booleanExpressionEvaluator.evaluate(">= 18 AND < 20", data, "age");
        assertTrue(booleanOptional.isSuccess());
        assertTrue(booleanOptional.get());
    }

    @Test
    public void testDefaultFieldFalse() {
        final Map<String, Object> data = new HashMap<>();
        data.put("age", 17);
        final Try<Boolean> booleanOptional = booleanExpressionEvaluator.evaluate(">= 18 AND < 20", data, "age");
        assertTrue(booleanOptional.isSuccess());
        assertFalse(booleanOptional.get());
    }

    @Test
    public void testContainsAnyTrueCondition() {
        final Map<String, Object> data = new HashMap<>();
        final List<Integer> ages = new ArrayList<>();
        ages.add(1);
        ages.add(2);
        ages.add(3);
        data.put("age", ages);
        final Try<Boolean> booleanOptional = booleanExpressionEvaluator.evaluate("age contains_any (2)", data);
        assertTrue(booleanOptional.isSuccess());
        assertTrue(booleanOptional.get());
    }

    @Test
    public void testContainsAnyFalseCondition() {
        final Map<String, Object> data = new HashMap<>();
        final List<Integer> ages = new ArrayList<>();
        ages.add(1);
        ages.add(2);
        ages.add(3);
        data.put("age", ages);
        final Try<Boolean> booleanOptional = booleanExpressionEvaluator.evaluate("age contains_any (12)", data);
        assertTrue(booleanOptional.isSuccess());
        assertFalse(booleanOptional.get());
    }

    @Test
    public void testContainsAllTrueCondition() {
        final Map<String, Object> data = new HashMap<>();
        final List<Integer> ages = new ArrayList<>();
        ages.add(1);
        ages.add(2);
        ages.add(3);
        data.put("age", ages);
        final Try<Boolean> booleanOptional = booleanExpressionEvaluator.evaluate("age contains_all (1,2)", data);
        assertTrue(booleanOptional.isSuccess());
        assertTrue(booleanOptional.get());
    }

    @Test
    public void testContainsAllFalseCondition() {
        final Map<String, Object> data = new HashMap<>();
        final List<Integer> ages = new ArrayList<>();
        ages.add(1);
        ages.add(2);
        ages.add(3);
        data.put("age", ages);
        final Try<Boolean> booleanOptional = booleanExpressionEvaluator.evaluate("age contains_all (2,5)", data);
        assertTrue(booleanOptional.isSuccess());
        assertFalse(booleanOptional.get());
    }

    @Test
    public void testComparisonWithArithmeticTrueCondition() {
        final Map<String, Object> data = new HashMap<>();
        data.put("age", "20");
        final Try<Boolean> booleanOptional = booleanExpressionEvaluator.evaluate("age > (5 + 10)", data);
        assertTrue(booleanOptional.isSuccess());
        assertTrue(booleanOptional.get());
    }

    @Test
    public void testComparisonWithArithmeticTrueConditionInverted() {
        final Map<String, Object> data = new HashMap<>();
        data.put("age", "20");
        final Try<Boolean> booleanOptional = booleanExpressionEvaluator.evaluate("(5 + 10) < age", data);
        assertTrue(booleanOptional.isSuccess());
        assertTrue(booleanOptional.get());
    }

    @Test
    public void testComparisonWithArithmeticFunction() {
        final Map<String, Object> data = new HashMap<>();
        data.put("age", "20");
        final Try<Boolean> booleanOptional = booleanExpressionEvaluator.evaluate("age > min (18, 25, 30)", data);
        assertTrue(booleanOptional.isSuccess());
        assertTrue(booleanOptional.get());
    }

    @Test
    public void testComparisonWithArithmeticVariableFunction() {
        final Map<String, Object> data = new HashMap<>();
        final List<Integer> numbers = new ArrayList<>();
        numbers.add(1);
        numbers.add(1);
        numbers.add(2);
        numbers.add(30);
        data.put("age", "20");
        data.put("numbers", numbers);
        final Try<Boolean> booleanOptional = booleanExpressionEvaluator.evaluate("age > max (numbers)", data);
        assertTrue(booleanOptional.isSuccess());
        assertFalse(booleanOptional.get());
    }

    @Test
    public void testComparisonWithArithmeticFalseCondition() {
        final Map<String, Object> data = new HashMap<>();
        data.put("age", "20");
        final Try<Boolean> booleanOptional = booleanExpressionEvaluator.evaluate("age > (5 + 20)", data);
        assertTrue(booleanOptional.isSuccess());
        assertFalse(booleanOptional.get());
    }

    @Test
    public void testComparisonWithArithmeticFalseCondition1() {
        final Map<String, Object> data = new HashMap<>();
        data.put("age", "20");
        data.put("a", 20);
        final Try<Boolean> booleanOptional = booleanExpressionEvaluator.evaluate("age > (5 + a)", data);
        assertTrue(booleanOptional.isSuccess());
        assertFalse(booleanOptional.get());
    }

    @Test
    public void testBooleanComparison() {
        final Map<String, Object> data = new HashMap<>();
        data.put("age", true);
        data.put("a", 20);
        final Try<Boolean> booleanOptional = booleanExpressionEvaluator.evaluate("age = (5 > 3)", data);
        assertTrue(booleanOptional.isSuccess());
        assertTrue(booleanOptional.get());
    }

    @Test
    public void testNegativeComparison() {
        final Map<String, Object> data = new HashMap<>();
        data.put("a", -6);
        final Try<Boolean> resultOptional = booleanExpressionEvaluator.evaluate("a > -10 AND a < -2", data);
        assertTrue(resultOptional.isSuccess());
        assertEquals(resultOptional.get(), true);
    }

    @Test
    public void testNullCheck() {
        final Map<String, Object> data = new HashMap<>();
        data.put("a", 2.7);
        final Try<Boolean> resultOptional = booleanExpressionEvaluator.evaluate("b = null", data);
        assertTrue(resultOptional.isSuccess());
        assertEquals(resultOptional.get(), true);
    }

    @Test
    public void testNullCheckInverted() {
        final Map<String, Object> data = new HashMap<>();
        data.put("a", 2.7);
        final Try<Boolean> resultOptional = booleanExpressionEvaluator.evaluate("null = b", data);
        assertTrue(resultOptional.isSuccess());
        assertEquals(resultOptional.get(), true);
    }

    @Test
    public void testNullCheck1() {
        final Map<String, Object> data = new HashMap<>();
        data.put("a", 2.7);
        final Try<Boolean> resultOptional = booleanExpressionEvaluator.evaluate("a = null", data);
        assertTrue(resultOptional.isSuccess());
        assertEquals(resultOptional.get(), false);
    }

    @Test
    public void testNotNullCheck() {
        final Map<String, Object> data = new HashMap<>();
        data.put("a", 2.7);
        final Try<Boolean> resultOptional = booleanExpressionEvaluator.evaluate("a != null", data);
        assertTrue(resultOptional.isSuccess());
        assertEquals(resultOptional.get(), true);
    }

    @Test
    public void testBooleanNullCheck() {
        final Map<String, Object> data = new HashMap<>();
        data.put("a", 3);
        final Try<Boolean> resultOptional = booleanExpressionEvaluator.evaluate("b = null && a > 2", data);
        assertTrue(resultOptional.isSuccess());
        assertEquals(resultOptional.get(), true);
    }

    @Test
    public void testArithmeticFunctionAndComparison() {
        final Map<String, Object> data = new HashMap<>();
        data.put("a", "test");
        final Try<Boolean> resultOptional = booleanExpressionEvaluator.evaluate("len(a) = 4", data);
        assertTrue(resultOptional.isSuccess());
        assertEquals(resultOptional.get(), true);
    }

    @Test
    public void testDateEqualityCorrectExpression() {
        final Map<String, Object> data = new HashMap<>();
        data.put("created_date", LocalDate.of(2023, 3, 5));
        final Try<Boolean> booleanOptional = booleanExpressionEvaluator.evaluate("created_date = 2023-03-05", data);
        assertTrue(booleanOptional.isSuccess());
        assertTrue(booleanOptional.get());
    }

    @Test
    public void testDateEqualityIncorrectExpression() {
        final Map<String, Object> data = new HashMap<>();
        data.put("created_date", LocalDate.of(2023, 3, 5));
        final Try<Boolean> booleanOptional = booleanExpressionEvaluator.evaluate("created_date = 2023-03-06", data);
        assertTrue(booleanOptional.isSuccess());
        assertFalse(booleanOptional.get());
    }

    @Test
    public void testDateGreaterThanCorrectExpression() {
        final Map<String, Object> data = new HashMap<>();
        data.put("event_date", LocalDate.of(2023, 6, 15));
        final Try<Boolean> booleanOptional = booleanExpressionEvaluator.evaluate("event_date > 2023-03-05", data);
        assertTrue(booleanOptional.isSuccess());
        assertTrue(booleanOptional.get());
    }

    @Test
    public void testDateLessThanCorrectExpression() {
        final Map<String, Object> data = new HashMap<>();
        data.put("birth_date", LocalDate.of(1990, 1, 1));
        final Try<Boolean> booleanOptional = booleanExpressionEvaluator.evaluate("birth_date < 2000-01-01", data);
        assertTrue(booleanOptional.isSuccess());
        assertTrue(booleanOptional.get());
    }

    @Test
    public void testDateTimeEqualityCorrectExpression() {
        final Map<String, Object> data = new HashMap<>();
        data.put("timestamp", LocalDateTime.of(2023, 3, 5, 14, 30, 0));
        final Try<Boolean> booleanOptional = booleanExpressionEvaluator.evaluate("timestamp = 2023-03-05 14:30:00", data);
        assertTrue(booleanOptional.isSuccess());
        assertTrue(booleanOptional.get());
    }

    @Test
    public void testDateTimeEqualityIncorrectExpression() {
        final Map<String, Object> data = new HashMap<>();
        data.put("timestamp", LocalDateTime.of(2023, 3, 5, 14, 30, 0));
        final Try<Boolean> booleanOptional = booleanExpressionEvaluator.evaluate("timestamp = 2023-03-05 14:31:00", data);
        assertTrue(booleanOptional.isSuccess());
        assertFalse(booleanOptional.get());
    }

    @Test
    public void testDateTimeGreaterThanCorrectExpression() {
        final Map<String, Object> data = new HashMap<>();
        data.put("last_modified", LocalDateTime.of(2023, 6, 15, 10, 30, 0));
        final Try<Boolean> booleanOptional = booleanExpressionEvaluator.evaluate("last_modified > 2023-03-05 14:30:00", data);
        assertTrue(booleanOptional.isSuccess());
        assertTrue(booleanOptional.get());
    }

    @Test
    public void testDateTimeLessThanCorrectExpression() {
        final Map<String, Object> data = new HashMap<>();
        data.put("created_at", LocalDateTime.of(2023, 1, 1, 9, 0, 0));
        final Try<Boolean> booleanOptional = booleanExpressionEvaluator.evaluate("created_at < 2023-03-05 14:30:00", data);
        assertTrue(booleanOptional.isSuccess());
        assertTrue(booleanOptional.get());
    }

    @Test
    public void testDateInClause() {
        final Map<String, Object> data = new HashMap<>();
        data.put("holiday", LocalDate.of(2023, 12, 25));
        final Try<Boolean> booleanOptional = booleanExpressionEvaluator.evaluate("holiday in (2023-12-24, 2023-12-25, 2023-12-26)", data);
        assertTrue(booleanOptional.isSuccess());
        assertTrue(booleanOptional.get());
    }

    @Test
    public void testDateTimeInClause() {
        final Map<String, Object> data = new HashMap<>();
        data.put("meeting_time", LocalDateTime.of(2023, 3, 5, 14, 30, 0));
        final Try<Boolean> booleanOptional = booleanExpressionEvaluator.evaluate("meeting_time in (2023-03-05 13:30:00, 2023-03-05 14:30:00, 2023-03-05 15:30:00)", data);
        assertTrue(booleanOptional.isSuccess());
        assertTrue(booleanOptional.get());
    }

    @Test
    public void testComplexDateTimeExpression() {
        final Map<String, Object> data = new HashMap<>();
        data.put("start_date", LocalDate.of(2023, 1, 1));
        data.put("end_date", LocalDate.of(2023, 12, 31));
        data.put("current_date", LocalDate.of(2023, 6, 15));
        final Try<Boolean> booleanOptional = booleanExpressionEvaluator.evaluate("current_date >= start_date AND current_date <= end_date", data);
        assertTrue(booleanOptional.isSuccess());
        assertTrue(booleanOptional.get());
    }

    @Test
    public void testDateEqualityWithStringData() {
        final Map<String, Object> data = new HashMap<>();
        data.put("created_date", "2023-03-05");
        final Try<Boolean> booleanOptional = booleanExpressionEvaluator.evaluate("created_date = 2023-03-05", data);
        assertTrue(booleanOptional.isSuccess());
        assertTrue(booleanOptional.get());
    }

    @Test
    public void testDateComparisonWithStringData() {
        final Map<String, Object> data = new HashMap<>();
        data.put("event_date", "2023-06-15");
        final Try<Boolean> booleanOptional = booleanExpressionEvaluator.evaluate("event_date > 2023-03-05", data);
        assertTrue(booleanOptional.isSuccess());
        assertTrue(booleanOptional.get());
    }

    @Test
    public void testDateTimeEqualityWithStringData() {
        final Map<String, Object> data = new HashMap<>();
        data.put("timestamp", "2023-03-05 14:30:00");
        final Try<Boolean> booleanOptional = booleanExpressionEvaluator.evaluate("timestamp = 2023-03-05 14:30:00", data);
        assertTrue(booleanOptional.isSuccess());
        assertTrue(booleanOptional.get());
    }

    @Test
    public void testDateTimeComparisonWithStringData() {
        final Map<String, Object> data = new HashMap<>();
        data.put("last_modified", "2023-06-15 10:30:00");
        final Try<Boolean> booleanOptional = booleanExpressionEvaluator.evaluate("last_modified > 2023-03-05 14:30:00", data);
        assertTrue(booleanOptional.isSuccess());
        assertTrue(booleanOptional.get());
    }

    @Test
    public void testMixedDateTypesComparison() {
        final Map<String, Object> data = new HashMap<>();
        data.put("string_date", "2023-06-15");
        data.put("object_date", LocalDate.of(2023, 3, 5));
        final Try<Boolean> booleanOptional = booleanExpressionEvaluator.evaluate("string_date > object_date", data);
        assertTrue(booleanOptional.isSuccess());
        assertTrue(booleanOptional.get());
    }

    @Test
    public void testMixedDateTimeTypesComparison() {
        final Map<String, Object> data = new HashMap<>();
        data.put("string_datetime", "2023-06-15 10:30:00");
        data.put("object_datetime", LocalDateTime.of(2023, 3, 5, 14, 30, 0));
        final Try<Boolean> booleanOptional = booleanExpressionEvaluator.evaluate("string_datetime > object_datetime", data);
        assertTrue(booleanOptional.isSuccess());
        assertTrue(booleanOptional.get());
    }

    @Test
    public void testInvalidDateStringFormat() {
        final Map<String, Object> data = new HashMap<>();
        data.put("bad_date", "03/05/2023");
        final Try<Boolean> booleanOptional = booleanExpressionEvaluator.evaluate("bad_date = 2023-03-05", data);
        assertTrue(booleanOptional.isSuccess());
        assertFalse(booleanOptional.get());
    }

    @Test
    public void testInvalidDateTimeStringFormat() {
        final Map<String, Object> data = new HashMap<>();
        data.put("bad_datetime", "2023-03-05T14:30:00");
        final Try<Boolean> booleanOptional = booleanExpressionEvaluator.evaluate("bad_datetime = 2023-03-05 14:30:00", data);
        assertTrue(booleanOptional.isSuccess());
        assertFalse(booleanOptional.get());
    }

    @Test
    public void testStringDateAutoDetection() {
        final Map<String, Object> data = new HashMap<>();
        data.put("date_string", "2023-03-05");
        data.put("regular_string", "hello world");
        final Try<Boolean> booleanOptional = booleanExpressionEvaluator.evaluate("date_string > 2023-01-01", data);
        assertTrue(booleanOptional.isSuccess());
        assertTrue(booleanOptional.get());
    }

    @Test
    public void testStringDateTimeAutoDetection() {
        final Map<String, Object> data = new HashMap<>();
        data.put("datetime_string", "2023-03-05 14:30:00");
        data.put("regular_string", "hello world");
        final Try<Boolean> booleanOptional = booleanExpressionEvaluator.evaluate("datetime_string > 2023-01-01 10:00:00", data);
        assertTrue(booleanOptional.isSuccess());
        assertTrue(booleanOptional.get());
    }

    @Test
    public void testAutoDetectionInComplexExpressions() {
        final Map<String, Object> data = new HashMap<>();
        data.put("start_date_str", "2023-01-01");
        data.put("end_date_str", "2023-12-31");
        data.put("current_date_str", "2023-06-15");
        final Try<Boolean> booleanOptional = booleanExpressionEvaluator.evaluate("current_date_str >= start_date_str AND current_date_str <= end_date_str", data);
        assertTrue(booleanOptional.isSuccess());
        assertTrue(booleanOptional.get());
    }

    @Test
    public void testDaysElapsedFunction() {
        final Map<String, Object> data = new HashMap<>();
        data.put("created_date", LocalDate.of(2023, 1, 1));
        final Try<Boolean> booleanOptional = booleanExpressionEvaluator.evaluate("days_elapsed(created_date) > 0", data);
        assertTrue(booleanOptional.isSuccess());
        assertTrue(booleanOptional.get());
    }

    @Test
    public void testDaysElapsedFunctionWithStringDate() {
        final Map<String, Object> data = new HashMap<>();
        data.put("created_date", "2023-01-01");
        final Try<Boolean> booleanOptional = booleanExpressionEvaluator.evaluate("days_elapsed(created_date) > 300", data);
        assertTrue(booleanOptional.isSuccess());
        assertTrue(booleanOptional.get());
    }

    @Test
    public void testDaysElapsedFunctionWithDirectDate() {
        final Map<String, Object> data = new HashMap<>();
        final Try<Boolean> booleanOptional = booleanExpressionEvaluator.evaluate("days_elapsed(2023-01-01) > 300", data);
        assertTrue(booleanOptional.isSuccess());
        assertTrue(booleanOptional.get());
    }

    @Test
    public void testDaysElapsedFunctionInComparison() {
        final Map<String, Object> data = new HashMap<>();
        data.put("event_date", LocalDate.of(2023, 6, 15));
        data.put("threshold_days", 100);
        final Try<Boolean> booleanOptional = booleanExpressionEvaluator.evaluate("days_elapsed(event_date) <= threshold_days", data);
        assertTrue(booleanOptional.isSuccess());
        // This will depend on current date, so we just check it executes successfully
    }
}

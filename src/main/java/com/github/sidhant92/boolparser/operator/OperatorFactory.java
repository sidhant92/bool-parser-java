package com.github.sidhant92.boolparser.operator;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import com.github.sidhant92.boolparser.constant.Operator;
import com.github.sidhant92.boolparser.operator.arithmetic.AddOperator;
import com.github.sidhant92.boolparser.operator.arithmetic.DivideOperator;
import com.github.sidhant92.boolparser.operator.arithmetic.ExponentOperator;
import com.github.sidhant92.boolparser.operator.arithmetic.ModulusOperator;
import com.github.sidhant92.boolparser.operator.arithmetic.MultiplyOperator;
import com.github.sidhant92.boolparser.operator.arithmetic.SubtractOperator;
import com.github.sidhant92.boolparser.operator.arithmetic.UnaryOperator;
import com.github.sidhant92.boolparser.operator.comparison.AbstractOperator;
import com.github.sidhant92.boolparser.operator.comparison.ContainsAllOperator;
import com.github.sidhant92.boolparser.operator.comparison.ContainsAnyOperator;
import com.github.sidhant92.boolparser.operator.comparison.EqualsOperator;
import com.github.sidhant92.boolparser.operator.comparison.GreaterThanEqualOperator;
import com.github.sidhant92.boolparser.operator.comparison.GreaterThanOperator;
import com.github.sidhant92.boolparser.operator.comparison.InOperator;
import com.github.sidhant92.boolparser.operator.comparison.LessThanEqualOperator;
import com.github.sidhant92.boolparser.operator.comparison.LessThanOperator;
import com.github.sidhant92.boolparser.operator.comparison.NotEqualsOperator;

/**
 * @author sidhant.aggarwal
 * @since 05/03/2023
 */
public class OperatorFactory {
    private static final Map<Operator, AbstractOperator> logicalOperatorMap = new EnumMap<>(Operator.class);

    private static final Map<Operator, com.github.sidhant92.boolparser.operator.arithmetic.AbstractOperator> arithmeticOperatorMap = new EnumMap<>(
            Operator.class);

    private OperatorFactory() {
        super();
    }

    public static void initialize() {
        final EqualsOperator equalsOperator = new EqualsOperator();
        final InOperator inOperator = new InOperator(equalsOperator);
        logicalOperatorMap.put(Operator.EQUALS, equalsOperator);
        logicalOperatorMap.put(Operator.GREATER_THAN, new GreaterThanOperator());
        logicalOperatorMap.put(Operator.GREATER_THAN_EQUAL, new GreaterThanEqualOperator());
        logicalOperatorMap.put(Operator.LESS_THAN, new LessThanOperator());
        logicalOperatorMap.put(Operator.LESS_THAN_EQUAL, new LessThanEqualOperator());
        logicalOperatorMap.put(Operator.NOT_EQUAL, new NotEqualsOperator());
        logicalOperatorMap.put(Operator.IN, new InOperator(equalsOperator));
        logicalOperatorMap.put(Operator.CONTAINS_ALL, new ContainsAllOperator(inOperator));
        logicalOperatorMap.put(Operator.CONTAINS_ANY, new ContainsAnyOperator(inOperator));

        arithmeticOperatorMap.put(Operator.ADD, new AddOperator());
        arithmeticOperatorMap.put(Operator.SUBTRACT, new SubtractOperator());
        arithmeticOperatorMap.put(Operator.DIVIDE, new DivideOperator());
        arithmeticOperatorMap.put(Operator.MULTIPLY, new MultiplyOperator());
        arithmeticOperatorMap.put(Operator.EXPONENT, new ExponentOperator());
        arithmeticOperatorMap.put(Operator.MODULUS, new ModulusOperator());
        arithmeticOperatorMap.put(Operator.UNARY, new UnaryOperator());
    }

    public static AbstractOperator getLogicalOperator(final Operator operator) {
        return logicalOperatorMap.get(operator);
    }

    public static com.github.sidhant92.boolparser.operator.arithmetic.AbstractOperator getArithmeticOperator(final Operator operator) {
        return arithmeticOperatorMap.get(operator);
    }

    public static List<AbstractOperator> getAllLogicalOperators() {
        return new ArrayList<>(logicalOperatorMap.values());
    }

    public static List<com.github.sidhant92.boolparser.operator.arithmetic.AbstractOperator> getAllArithmeticOperators() {
        return new ArrayList<>(arithmeticOperatorMap.values());
    }
}

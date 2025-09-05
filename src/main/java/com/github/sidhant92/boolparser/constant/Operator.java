package com.github.sidhant92.boolparser.constant;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import com.github.sidhant92.boolparser.operator.comparison.AbstractOperator;
import com.github.sidhant92.boolparser.operator.OperatorFactory;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author sidhant.aggarwal
 * @since 05/03/2023
 */
@AllArgsConstructor (access = AccessLevel.PRIVATE)
@Getter
public enum Operator {
    EQUALS,
    GREATER_THAN,
    GREATER_THAN_EQUAL,
    LESS_THAN,
    LESS_THAN_EQUAL,
    NOT_EQUAL,
    IN,

    ADD,
    SUBTRACT,
    MULTIPLY,
    DIVIDE,
    MODULUS,
    EXPONENT,
    UNARY,

    CONTAINS_ALL,
    CONTAINS_ANY;

    public static Optional<Operator> getOperatorFromSymbol(final String symbol) {
        final String symbolLowerCase = symbol.toLowerCase();
        final Optional<Operator> operator = OperatorFactory.getAllLogicalOperators()
                .stream()
                .filter(op -> op.getSymbol().toLowerCase().equals(symbolLowerCase))
                .map(AbstractOperator::getOperator)
                .findFirst();
        if (operator.isPresent()) {
            return operator;
        }
        return OperatorFactory.getAllArithmeticOperators()
                .stream()
                .filter(op -> op.getSymbol().toLowerCase().equals(symbolLowerCase))
                .map(com.github.sidhant92.boolparser.operator.arithmetic.AbstractOperator::getOperator)
                .findFirst();
    }

    public static List<Operator> getEqualityOperators() {
        return Arrays.asList(EQUALS, NOT_EQUAL);
    }
}

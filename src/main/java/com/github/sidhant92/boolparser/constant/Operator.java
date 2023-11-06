package com.github.sidhant92.boolparser.constant;

import java.util.Optional;
import com.github.sidhant92.boolparser.operator.AbstractOperator;
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
    CONTAINS_ALL,
    CONTAINS_ANY;

    public static Optional<Operator> getOperatorFromSymbol(final String symbol) {
        final String symbolLowerCase = symbol.toLowerCase();
        return OperatorFactory.getAllOperators()
                .stream()
                .filter(operator -> operator.getSymbol().toLowerCase().equals(symbolLowerCase))
                .map(AbstractOperator::getOperator)
                .findFirst();
    }
}

package com.github.sidhant92.boolparser.constant;

import java.util.Arrays;
import java.util.Optional;

/**
 * @author sidhant.aggarwal
 * @since 21/05/2024
 */
public enum FunctionType {
    MIN,
    MAX,
    AVG,
    SUM,
    MEAN,
    MODE,
    MEDIAN,
    INT,
    LEN;

    public static Optional<FunctionType> getArrayFunctionFromSymbol(final String symbol) {
        final String symbolUpperCase = symbol.toUpperCase();
        return Arrays
                .stream(FunctionType.values())
                .filter(function -> function.name().equals(symbolUpperCase))
                .findFirst();
    }
}

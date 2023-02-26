package com.github.sidhant92.boolparser.constant;

import lombok.AllArgsConstructor;

/**
 * @author sidhant.aggarwal
 * @since 05/03/2023
 */
@AllArgsConstructor
public enum OperatorType {
    binary(1, 1),
    ternary(2, 2),
    n_ary(1, Integer.MAX_VALUE);

    private final int minOperandLength;

    private final int maxOperandLength;

    public boolean isOperandListValid(Object... operands) {
        return (operands != null && operands.length >= minOperandLength && operands.length <= maxOperandLength);
    }
}

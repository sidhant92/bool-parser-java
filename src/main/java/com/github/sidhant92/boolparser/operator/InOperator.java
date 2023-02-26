package com.github.sidhant92.boolparser.operator;

import java.util.Arrays;
import com.github.sidhant92.boolparser.constant.ContainerDataType;
import com.github.sidhant92.boolparser.constant.DataType;
import com.github.sidhant92.boolparser.constant.Operator;
import lombok.AllArgsConstructor;

/**
 * @author sidhant.aggarwal
 * @since 05/03/2023
 */
@AllArgsConstructor
public class InOperator extends AbstractOperator {
    private final EqualsOperator equalsOperator;

    @Override
    public <T extends Comparable<? super T>> boolean evaluate(final ContainerDataType containerDataType, final DataType dataType,
                                                              final Object leftOperand, final Object... rightOperands) {
        return Arrays
                .stream(rightOperands).anyMatch(a -> equalsOperator.evaluate(ContainerDataType.primitive, dataType, leftOperand, a));
    }

    @Override
    public Operator getOperator() {
        return Operator.IN;
    }

    @Override
    public String getSymbol() {
        return "IN";
    }
}

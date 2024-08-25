package com.github.sidhant92.boolparser.operator.comparison;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.apache.commons.lang3.tuple.Pair;
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
    public <T extends Comparable<? super T>> boolean evaluate(final ContainerDataType containerDataType, final Object leftOperand,
                                                              final DataType leftOperandDataType, final List<Pair<Object, DataType>> rightOperands) {
        return rightOperands
                .stream().anyMatch(a -> equalsOperator.evaluate(containerDataType, leftOperand, leftOperandDataType, Collections.singletonList(a)));
    }

    @Override
    public Operator getOperator() {
        return Operator.IN;
    }

    @Override
    public String getSymbol() {
        return "IN";
    }

    @Override
    public List<ContainerDataType> getAllowedContainerTypes() {
        return Collections.singletonList(ContainerDataType.PRIMITIVE);
    }

    @Override
    public List<DataType> getAllowedDataTypes() {
        return Arrays.asList(DataType.values());
    }
}

package com.github.sidhant92.boolparser.operator.logical;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import com.github.sidhant92.boolparser.constant.ContainerDataType;
import com.github.sidhant92.boolparser.constant.DataType;
import com.github.sidhant92.boolparser.constant.Operator;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ContainsAnyOperator extends AbstractOperator {
    private final InOperator inOperator;

    @Override
    public <T extends Comparable<? super T>> boolean evaluate(final ContainerDataType containerDataType, final DataType dataType,
                                                              final Object leftOperand, final Object... rightOperands) {
        if (!containerDataType.isValid(dataType, leftOperand)) {
            return false;
        }
        final Object[] leftOperandArray = ((List<?>) leftOperand).toArray();
        return Arrays
                .stream(rightOperands)
                .anyMatch(rightOperand -> inOperator.evaluate(ContainerDataType.PRIMITIVE, dataType, rightOperand, leftOperandArray));
    }

    @Override
    public Operator getOperator() {
        return Operator.CONTAINS_ANY;
    }

    @Override
    public String getSymbol() {
        return "CONTAINS_ANY";
    }

    @Override
    public List<ContainerDataType> getAllowedContainerTypes() {
        return Collections.singletonList(ContainerDataType.LIST);
    }

    @Override
    public List<DataType> getAllowedDataTypes() {
        return Arrays.asList(DataType.values());
    }
}

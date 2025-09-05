package com.github.sidhant92.boolparser.operator.comparison;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.lang3.tuple.Pair;
import com.github.sidhant92.boolparser.constant.ContainerDataType;
import com.github.sidhant92.boolparser.constant.DataType;
import com.github.sidhant92.boolparser.constant.Operator;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ContainsAnyOperator extends AbstractOperator {
    private final InOperator inOperator;

    @Override
    public <T extends Comparable<? super T>> boolean evaluate(final ContainerDataType containerDataType, final Object leftOperand,
                                                              final DataType leftOperandDataType, final List<Pair<Object, DataType>> rightOperands) {
        if (!containerDataType.isValid(leftOperandDataType, leftOperand)) {
            return false;
        }
        final List<Pair<Object, DataType>> leftOperandArray = Arrays
                .stream(((List<?>) leftOperand).toArray())
                .map(a -> Pair.of(a, leftOperandDataType))
                .collect(Collectors.toList());
        return rightOperands
                .stream().anyMatch(rightOperand -> inOperator.evaluate(ContainerDataType.PRIMITIVE, rightOperand.getLeft(), rightOperand.getRight(),
                                                                       leftOperandArray));
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

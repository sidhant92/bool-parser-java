package com.github.sidhant92.boolparser.operator.comparison;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.apache.commons.lang3.tuple.Pair;
import com.github.sidhant92.boolparser.constant.ContainerDataType;
import com.github.sidhant92.boolparser.constant.DataType;
import com.github.sidhant92.boolparser.constant.Operator;

/**
 * @author sidhant.aggarwal
 * @since 05/03/2023
 */
public class NotEqualsOperator extends AbstractOperator {
    @Override
    public <T extends Comparable<? super T>> boolean evaluate(final ContainerDataType containerDataType, final Object leftOperand,
                                                              final DataType leftOperandDataType, final List<Pair<Object, DataType>> rightOperands) {
        final DataType comparisonType = getComparableDataType(leftOperandDataType, rightOperands.get(0).getRight());
        final Optional<T> leftValueOptional = containerDataType.getValue(comparisonType, leftOperand);
        final Optional<T> rightValueOptional = containerDataType.getValue(comparisonType, rightOperands.get(0).getLeft());
        return leftValueOptional
                .flatMap(leftValue -> rightValueOptional
                        .map(leftValue::compareTo)
                        .map(a -> a != 0)).orElse(false);
    }

    private DataType getComparableDataType(final DataType leftOperandDataType, final DataType rightOperandDataType) {
        if (leftOperandDataType.priority > rightOperandDataType.priority) {
            return leftOperandDataType;
        }
        return rightOperandDataType;
    }

    @Override
    public Operator getOperator() {
        return Operator.NOT_EQUAL;
    }

    @Override
    public String getSymbol() {
        return "!=";
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

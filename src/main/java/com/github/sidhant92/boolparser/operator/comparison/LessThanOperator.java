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
public class LessThanOperator extends AbstractOperator implements NumericOperator {
    @Override
    public <T extends Comparable<? super T>> boolean evaluate(final ContainerDataType containerDataType, final Object leftOperand,
                                                              final DataType leftOperandDataType, final List<Pair<Object, DataType>> rightOperands) {
        validate(leftOperand, leftOperandDataType, rightOperands.get(0).getLeft(), rightOperands.get(0).getRight(), containerDataType);
        final DataType comparisonType = getComparableDataType(leftOperandDataType, rightOperands.get(0).getRight());
        final Optional<T> leftValueOptional = containerDataType.getValue(comparisonType, leftOperand);
        final Optional<T> rightValueOptional = containerDataType.getValue(comparisonType, rightOperands.get(0).getLeft());
        return leftValueOptional
                .flatMap(leftValue -> rightValueOptional
                        .map(leftValue::compareTo)
                        .map(a -> a < 0)).orElse(false);
    }

    @Override
    public Operator getOperator() {
        return Operator.LESS_THAN;
    }

    @Override
    public String getSymbol() {
        return "<";
    }

    @Override
    public List<ContainerDataType> getAllowedContainerTypes() {
        return Collections.singletonList(ContainerDataType.PRIMITIVE);
    }

    @Override
    public List<DataType> getAllowedDataTypes() {
        return Arrays.asList(DataType.INTEGER, DataType.LONG, DataType.DECIMAL, DataType.STRING, DataType.VERSION, DataType.DATE, DataType.DATETIME);
    }
}

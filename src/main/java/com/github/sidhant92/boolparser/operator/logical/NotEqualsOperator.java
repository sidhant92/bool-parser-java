package com.github.sidhant92.boolparser.operator.logical;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import com.github.sidhant92.boolparser.constant.ContainerDataType;
import com.github.sidhant92.boolparser.constant.DataType;
import com.github.sidhant92.boolparser.constant.Operator;

/**
 * @author sidhant.aggarwal
 * @since 05/03/2023
 */
public class NotEqualsOperator extends AbstractOperator {
    @Override
    public <T extends Comparable<? super T>> boolean evaluate(final ContainerDataType containerDataType, final DataType dataType,
            final Object leftOperand, final Object... rightOperands) {
        final Optional<T> leftValueOptional = containerDataType.getValue(dataType, leftOperand);
        final Optional<T> rightValueOptional = containerDataType.getValue(dataType, rightOperands[0]);
        return leftValueOptional.flatMap(leftValue -> rightValueOptional.map(leftValue::compareTo).map(a -> a != 0)).orElse(false);
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

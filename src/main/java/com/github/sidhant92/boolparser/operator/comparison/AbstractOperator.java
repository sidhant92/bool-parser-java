package com.github.sidhant92.boolparser.operator.comparison;

import java.util.List;
import org.apache.commons.lang3.tuple.Pair;
import com.github.sidhant92.boolparser.constant.ContainerDataType;
import com.github.sidhant92.boolparser.constant.DataType;
import com.github.sidhant92.boolparser.constant.Operator;

/**
 * @author sidhant.aggarwal
 * @since 05/03/2023
 */
public abstract class AbstractOperator {
    public abstract <T extends Comparable<? super T>> boolean evaluate(final ContainerDataType containerDataType, final Object leftOperand,
                                                                       final DataType leftOperandDataType,
                                                                       final List<Pair<Object, DataType>> rightOperands);

    public abstract Operator getOperator();

    public abstract String getSymbol();

    public abstract List<ContainerDataType> getAllowedContainerTypes();

    public abstract List<DataType> getAllowedDataTypes();
}

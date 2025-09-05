package com.github.sidhant92.boolparser.operator.arithmetic;

import java.util.List;
import com.github.sidhant92.boolparser.constant.ContainerDataType;
import com.github.sidhant92.boolparser.constant.DataType;
import com.github.sidhant92.boolparser.constant.Operator;

/**
 * @author sidhant.aggarwal
 * @since 15/03/2024
 */
public abstract class AbstractOperator {
    public abstract Object evaluate(final Object leftOperand, final DataType leftOperandDataType, final Object rightOperand,
                                    final DataType rightOperandDataType);

    public abstract Operator getOperator();

    public abstract String getSymbol();

    public abstract List<ContainerDataType> getAllowedContainerTypes();

    public abstract List<DataType> getAllowedDataTypes();
}

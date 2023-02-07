package com.github.sidhant92.boolparser.operator;

import com.github.sidhant92.boolparser.constant.ContainerDataType;
import com.github.sidhant92.boolparser.constant.DataType;
import com.github.sidhant92.boolparser.constant.Operator;

/**
 * @author sidhant.aggarwal
 * @since 05/03/2023
 */
public abstract class AbstractOperator {
    public abstract <T extends Comparable<? super T>> boolean evaluate(final ContainerDataType containerDataType, final DataType dataType, final Object leftOperand,
            final Object... rightOperands);

    public abstract Operator getOperator();

    public abstract String getSymbol();

    //public boolean validate(final ContainerDataType containerDataType, final DataType dataType, final Object leftOperand)
}

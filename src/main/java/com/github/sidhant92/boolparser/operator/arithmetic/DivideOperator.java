package com.github.sidhant92.boolparser.operator.arithmetic;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import com.github.sidhant92.boolparser.constant.ContainerDataType;
import com.github.sidhant92.boolparser.constant.DataType;
import com.github.sidhant92.boolparser.constant.Operator;
import com.github.sidhant92.boolparser.exception.InvalidDataType;

/**
 * @author sidhant.aggarwal
 * @since 15/03/2024
 */
public class DivideOperator extends AbstractOperator {
    @Override
    public Object evaluate(final Object leftOperand, final DataType leftOperandDataType, final Object rightOperand,
                           final DataType rightOperandDataType) {
        final double res = Double.parseDouble(leftOperand.toString()) / Double.parseDouble(rightOperand.toString());
        if ((int) res == res) {
            return (int) res;
        }
        return res;
    }

    @Override
    public Operator getOperator() {
        return Operator.DIVIDE;
    }

    @Override
    public String getSymbol() {
        return "/";
    }

    @Override
    public List<ContainerDataType> getAllowedContainerTypes() {
        return Collections.singletonList(ContainerDataType.PRIMITIVE);
    }

    @Override
    public List<DataType> getAllowedDataTypes() {
        return Arrays.asList(DataType.INTEGER, DataType.LONG, DataType.DECIMAL);
    }
}

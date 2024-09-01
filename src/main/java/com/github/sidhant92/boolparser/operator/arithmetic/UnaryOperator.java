package com.github.sidhant92.boolparser.operator.arithmetic;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import com.github.sidhant92.boolparser.constant.ContainerDataType;
import com.github.sidhant92.boolparser.constant.DataType;
import com.github.sidhant92.boolparser.constant.Operator;

/**
 * @author sidhant.aggarwal
 * @since 15/03/2024
 */
public class UnaryOperator extends AbstractOperator {
    @Override
    public Object evaluate(final Object leftOperand, final DataType leftOperandDataType, final Object rightOperand,
                           final DataType rightOperandDataType) {
        if (leftOperandDataType.equals(DataType.DECIMAL)) {
            return ((BigDecimal) leftOperand).negate();
        }
        if (leftOperandDataType.equals(DataType.LONG)) {
            return -(long) leftOperand;
        }
        return -(int) leftOperand;
    }

    @Override
    public Operator getOperator() {
        return Operator.UNARY;
    }

    @Override
    public String getSymbol() {
        return "UNARY";
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

package com.github.sidhant92.boolparser.operator.arithmetic;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import com.github.sidhant92.boolparser.constant.ContainerDataType;
import com.github.sidhant92.boolparser.constant.DataType;
import com.github.sidhant92.boolparser.constant.Operator;
import com.github.sidhant92.boolparser.datatype.DataTypeFactory;
import com.github.sidhant92.boolparser.datatype.DecimalDataType;
import com.github.sidhant92.boolparser.datatype.LongDataType;
import com.github.sidhant92.boolparser.util.ValueUtils;

/**
 * @author sidhant.aggarwal
 * @since 15/03/2024
 */
public class MultiplyOperator extends AbstractOperator {
    @Override
    public Object evaluate(final Object leftOperand, final DataType leftOperandDataType, final Object rightOperand,
                           final DataType rightOperandDataType) {
        if (leftOperandDataType.equals(DataType.DECIMAL) || rightOperandDataType.equals(DataType.DECIMAL)) {
            final DecimalDataType decimalDataType = (DecimalDataType) DataTypeFactory.getDataType(DataType.DECIMAL);
            return decimalDataType.getValue(leftOperand).get().multiply(decimalDataType.getValue(rightOperand).get());
        }
        final LongDataType longDataType = (LongDataType) DataTypeFactory.getDataType(DataType.LONG);
        return ValueUtils.castLong(longDataType.getValue(leftOperand).get() * longDataType.getValue(rightOperand).get());
    }

    @Override
    public Operator getOperator() {
        return Operator.MULTIPLY;
    }

    @Override
    public String getSymbol() {
        return "*";
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

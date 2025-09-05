package com.github.sidhant92.boolparser.function.arithmetic;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import com.github.sidhant92.boolparser.constant.ContainerDataType;
import com.github.sidhant92.boolparser.constant.DataType;
import com.github.sidhant92.boolparser.constant.FunctionType;
import com.github.sidhant92.boolparser.datatype.DataTypeFactory;
import com.github.sidhant92.boolparser.domain.EvaluatedNode;

/**
 * @author sidhant.aggarwal
 * @since 21/05/2024
 */
public class IntFunction extends AbstractFunction {
    @Override
    public Object evaluate(final List<EvaluatedNode> items) {
        final EvaluatedNode item = items.get(0);
        if (item.getDataType() == DataType.DECIMAL) {
            return ((BigDecimal) DataTypeFactory.getDataType(DataType.DECIMAL).getValue(item.getValue()).get()).intValue();
        }
        if (item.getDataType() == DataType.LONG) {
            return ((Long) item.getValue()).intValue();
        }
        return item.getValue();
    }

    @Override
    public FunctionType getFunctionType() {
        return FunctionType.INT;
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

package com.github.sidhant92.boolparser.function.arithmetic;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.apache.commons.lang3.tuple.Pair;
import com.github.sidhant92.boolparser.constant.ContainerDataType;
import com.github.sidhant92.boolparser.constant.DataType;
import com.github.sidhant92.boolparser.constant.FunctionType;
import com.github.sidhant92.boolparser.domain.EvaluatedNode;
import com.github.sidhant92.boolparser.util.ValueUtils;

/**
 * @author sidhant.aggarwal
 * @since 21/05/2024
 */
public class AvgFunction extends AbstractFunction {
    @Override
    public Object evaluate(final List<EvaluatedNode> items) {
        if (items
                .stream().anyMatch(a -> a.getDataType().equals(DataType.DECIMAL))) {
            return ValueUtils.caseDouble(items
                                                 .stream().mapToDouble(a -> Double.parseDouble(a.getValue().toString())).average().getAsDouble());
        }
        if (items
                .stream().anyMatch(a -> a.getDataType().equals(DataType.LONG))) {
            return ValueUtils.caseDouble(items
                                                 .stream().mapToLong(a -> Long.parseLong(a.getValue().toString())).average().getAsDouble());
        }
        return ValueUtils.caseDouble(items
                                             .stream().mapToInt(a -> Integer.parseInt(a.getValue().toString())).average().getAsDouble());
    }

    @Override
    public FunctionType getFunctionType() {
        return FunctionType.AVG;
    }

    @Override
    public List<ContainerDataType> getAllowedContainerTypes() {
        return Collections.singletonList(ContainerDataType.LIST);
    }

    @Override
    public List<DataType> getAllowedDataTypes() {
        return Arrays.asList(DataType.INTEGER, DataType.LONG, DataType.DECIMAL);
    }
}

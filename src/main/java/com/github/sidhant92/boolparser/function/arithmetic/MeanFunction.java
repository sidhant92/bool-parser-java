package com.github.sidhant92.boolparser.function.arithmetic;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.math3.stat.StatUtils;
import org.apache.commons.math3.stat.descriptive.moment.Mean;
import com.github.sidhant92.boolparser.constant.ContainerDataType;
import com.github.sidhant92.boolparser.constant.DataType;
import com.github.sidhant92.boolparser.constant.FunctionType;

/**
 * @author sidhant.aggarwal
 * @since 21/05/2024
 */
public class MeanFunction extends AbstractFunction {
    @Override
    public Object evaluate(final List<Pair<Object, DataType>> items) {
        final double mean = StatUtils.mean(items
                                                   .stream().mapToDouble(a -> Double.parseDouble(a.getKey().toString())).toArray());
        if ((int) mean == mean) {
            return (int) mean;
        }
        return mean;
    }

    @Override
    public FunctionType getFunctionType() {
        return FunctionType.MEAN;
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

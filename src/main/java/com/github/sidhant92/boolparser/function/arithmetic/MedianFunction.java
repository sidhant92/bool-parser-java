package com.github.sidhant92.boolparser.function.arithmetic;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.math3.stat.descriptive.rank.Median;
import com.github.sidhant92.boolparser.constant.ContainerDataType;
import com.github.sidhant92.boolparser.constant.DataType;
import com.github.sidhant92.boolparser.constant.FunctionType;
import com.github.sidhant92.boolparser.util.ValueUtils;

/**
 * @author sidhant.aggarwal
 * @since 21/05/2024
 */
public class MedianFunction extends AbstractFunction {
    private final Median median;

    public MedianFunction() {
        this.median = new Median();
    }

    @Override
    public Object evaluate(final List<Pair<Object, DataType>> items) {
        final double res = median.evaluate(items
                                                   .stream().mapToDouble(a -> Double.parseDouble(a.getKey().toString())).toArray());
        return ValueUtils.caseDouble(res);
    }

    @Override
    public FunctionType getFunctionType() {
        return FunctionType.MEDIAN;
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

package com.github.sidhant92.boolparser.function.arithmetic;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import com.github.sidhant92.boolparser.constant.ContainerDataType;
import com.github.sidhant92.boolparser.constant.DataType;
import com.github.sidhant92.boolparser.constant.FunctionType;
import com.github.sidhant92.boolparser.datatype.DataTypeFactory;
import com.github.sidhant92.boolparser.datatype.DecimalDataType;
import com.github.sidhant92.boolparser.domain.EvaluatedNode;
import com.github.sidhant92.boolparser.util.ValueUtils;

/**
 * @author sidhant.aggarwal
 * @since 21/05/2024
 */
public class MedianFunction extends AbstractFunction {
    public MedianFunction() {
    }

    @Override
    public Object evaluate(final List<EvaluatedNode> items) {
        final DecimalDataType decimalDataType = (DecimalDataType) DataTypeFactory.getDataType(DataType.DECIMAL);
        final List<BigDecimal> itemsConverted = items
                .stream()
                .map(item -> decimalDataType.getValue(item.getValue()))
                .map(Optional::get)
                .collect(Collectors.toList());
        Collections.sort(itemsConverted);
        if (items.size() % 2 == 0) {
            final BigDecimal res = itemsConverted.get(items.size() / 2).add(itemsConverted.get(items.size() / 2 - 1))
                                                 .divide(new BigDecimal("2"), 2, BigDecimal.ROUND_DOWN);
            return ValueUtils.castDecimal(res);
        } else {
            return ValueUtils.castDecimal(itemsConverted.get(items.size() / 2));
        }
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

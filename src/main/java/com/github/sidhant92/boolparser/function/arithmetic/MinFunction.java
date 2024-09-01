package com.github.sidhant92.boolparser.function.arithmetic;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import com.github.sidhant92.boolparser.constant.ContainerDataType;
import com.github.sidhant92.boolparser.constant.DataType;
import com.github.sidhant92.boolparser.constant.FunctionType;
import com.github.sidhant92.boolparser.datatype.DataTypeFactory;
import com.github.sidhant92.boolparser.datatype.DecimalDataType;
import com.github.sidhant92.boolparser.datatype.IntegerDataType;
import com.github.sidhant92.boolparser.datatype.LongDataType;
import com.github.sidhant92.boolparser.domain.EvaluatedNode;
import com.github.sidhant92.boolparser.util.ValueUtils;

/**
 * @author sidhant.aggarwal
 * @since 21/05/2024
 */
public class MinFunction extends AbstractFunction {
    @Override
    public Object evaluate(final List<EvaluatedNode> items) {
        if (items
                .stream().anyMatch(a -> a.getDataType().equals(DataType.DECIMAL))) {
            final DecimalDataType decimalDataType = (DecimalDataType) DataTypeFactory.getDataType(DataType.DECIMAL);
            final List<BigDecimal> itemsConverted = items
                    .stream()
                    .map(item -> decimalDataType.getValue(item.getValue()))
                    .map(Optional::get)
                    .collect(Collectors.toList());
            final BigDecimal min = itemsConverted
                    .stream().min(Comparator.naturalOrder()).get();
            return ValueUtils.castDecimal(min);
        }
        if (items
                .stream().anyMatch(a -> a.getDataType().equals(DataType.LONG))) {
            final LongDataType longDataType = (LongDataType) DataTypeFactory.getDataType(DataType.LONG);
            return ValueUtils.castDecimal(items
                                                  .stream().mapToLong(a -> longDataType.getValue(a.getValue()).get()).min().getAsLong());
        }
        final IntegerDataType integerDataType = (IntegerDataType) DataTypeFactory.getDataType(DataType.INTEGER);
        return ValueUtils.castDecimal(items
                                              .stream().mapToInt(a -> integerDataType.getValue(a.getValue()).get()).min().getAsInt());
    }

    @Override
    public FunctionType getFunctionType() {
        return FunctionType.MIN;
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

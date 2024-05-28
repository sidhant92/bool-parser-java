package com.github.sidhant92.boolparser.function.arithmetic;

import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang3.tuple.Pair;
import com.github.sidhant92.boolparser.constant.ContainerDataType;
import com.github.sidhant92.boolparser.constant.DataType;
import com.github.sidhant92.boolparser.constant.FunctionType;
import com.github.sidhant92.boolparser.domain.EvaluatedNode;

/**
 * @author sidhant.aggarwal
 * @since 21/05/2024
 */
public class LenFunction extends AbstractFunction {
    @Override
    public Object evaluate(final List<EvaluatedNode> items) {
        if (items.size() == 1 && items.get(0).getValue() instanceof String) {
            return ((String) items.get(0).getValue()).length();
        }
        return items.size();
    }

    @Override
    public FunctionType getFunctionType() {
        return FunctionType.LEN;
    }

    @Override
    public List<ContainerDataType> getAllowedContainerTypes() {
        return Arrays.asList(ContainerDataType.values());
    }

    @Override
    public List<DataType> getAllowedDataTypes() {
        return Arrays.asList(DataType.values());
    }
}

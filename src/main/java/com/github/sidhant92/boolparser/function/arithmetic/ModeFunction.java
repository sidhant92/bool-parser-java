package com.github.sidhant92.boolparser.function.arithmetic;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.github.sidhant92.boolparser.constant.ContainerDataType;
import com.github.sidhant92.boolparser.constant.DataType;
import com.github.sidhant92.boolparser.constant.FunctionType;
import com.github.sidhant92.boolparser.domain.EvaluatedNode;
import com.github.sidhant92.boolparser.util.ValueUtils;

/**
 * @author sidhant.aggarwal
 * @since 21/05/2024
 */
public class ModeFunction extends AbstractFunction {
    @Override
    public Object evaluate(final List<EvaluatedNode> items) {
        Map<Object, Integer> hm = new HashMap<Object, Integer>();
        int max = 1;
        Object temp = items.get(0).getValue();

        for (int i = 0; i < items.size(); i++) {
            final Object value = items.get(i).getValue();

            if (hm.get(value) != null) {

                int count = hm.get(value);
                count++;
                hm.put(value, count);

                if (count > max) {
                    max = count;
                    temp = value;
                }
            } else {
                hm.put(value, 1);
            }
        }
        if (temp instanceof BigDecimal) {
            return ValueUtils.castDecimal((BigDecimal) temp);
        }
        if (temp instanceof Long) {
            return ValueUtils.castLong((Long) temp);
        }
        return temp;
    }

    @Override
    public FunctionType getFunctionType() {
        return FunctionType.MODE;
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

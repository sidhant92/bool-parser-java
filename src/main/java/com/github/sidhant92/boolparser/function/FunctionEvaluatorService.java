package com.github.sidhant92.boolparser.function;

import java.util.List;
import org.apache.commons.lang3.tuple.Pair;
import com.github.sidhant92.boolparser.constant.ContainerDataType;
import com.github.sidhant92.boolparser.constant.DataType;
import com.github.sidhant92.boolparser.constant.FunctionType;
import com.github.sidhant92.boolparser.exception.InvalidContainerTypeException;
import com.github.sidhant92.boolparser.exception.InvalidDataType;
import com.github.sidhant92.boolparser.exception.InvalidExpressionException;
import com.github.sidhant92.boolparser.function.arithmetic.AbstractFunction;
import lombok.extern.slf4j.Slf4j;

/**
 * @author sidhant.aggarwal
 * @since 21/05/2024
 */
@Slf4j
public class FunctionEvaluatorService {
    public FunctionEvaluatorService() {
        FunctionFactory.initialize();
    }

    public Object evaluateArithmeticFunction(final FunctionType functionType, final List<Pair<Object, DataType>> items) {
        final AbstractFunction abstractFunction = FunctionFactory.getArithmeticFunction(functionType);
        if (items.isEmpty()) {
            log.error("Empty items not allowed");
            throw new InvalidExpressionException();
        }
        final ContainerDataType containerDataType = items.size() > 1 ? ContainerDataType.LIST : ContainerDataType.PRIMITIVE;
        if (!abstractFunction.getAllowedContainerTypes().contains(containerDataType)) {
            log.error("Invalid container type {} for function {}", containerDataType, functionType.name());
            throw new InvalidContainerTypeException();
        }
        final boolean validDataType = items
                .stream().allMatch(item -> abstractFunction.getAllowedDataTypes().contains(item.getValue()));
        if (!validDataType) {
            log.error("Invalid data type {} for function {}", items, functionType.name());
            throw new InvalidDataType();
        }

        items.forEach(item -> {
            if (!ContainerDataType.PRIMITIVE.isValid(item.getValue(), item.getKey())) {
                log.error("Validation failed for the function {} for the operand {}", functionType.name(), item.getKey());
                throw new InvalidDataType();
            }
        });
        return abstractFunction.evaluate(items);
    }
}

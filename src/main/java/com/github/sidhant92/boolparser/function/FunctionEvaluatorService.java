package com.github.sidhant92.boolparser.function;

import java.util.List;
import org.apache.commons.lang3.tuple.Pair;
import com.github.sidhant92.boolparser.constant.ContainerDataType;
import com.github.sidhant92.boolparser.constant.DataType;
import com.github.sidhant92.boolparser.constant.FunctionType;
import com.github.sidhant92.boolparser.domain.EvaluatedNode;
import com.github.sidhant92.boolparser.exception.InvalidContainerTypeException;
import com.github.sidhant92.boolparser.exception.InvalidDataType;
import com.github.sidhant92.boolparser.exception.InvalidExpressionException;
import com.github.sidhant92.boolparser.function.arithmetic.AbstractFunction;

/**
 * @author sidhant.aggarwal
 * @since 21/05/2024
 */
public class FunctionEvaluatorService {
    public FunctionEvaluatorService() {
        FunctionFactory.initialize();
    }

    public Object evaluateArithmeticFunction(final FunctionType functionType, final List<EvaluatedNode> items) {
        final AbstractFunction abstractFunction = FunctionFactory.getArithmeticFunction(functionType);
        if (items.isEmpty()) {
            throw new InvalidExpressionException("Empty items not allowed");
        }
        final ContainerDataType containerDataType = items.size() > 1 ? ContainerDataType.LIST : ContainerDataType.PRIMITIVE;
        if (!abstractFunction.getAllowedContainerTypes().contains(containerDataType)) {
            throw new InvalidContainerTypeException(
                    String.format("Invalid container type %s for function %s", containerDataType, functionType.name()));
        }
        final boolean validDataType = items
                .stream().allMatch(item -> abstractFunction.getAllowedDataTypes().contains(item.getDataType()));
        if (!validDataType) {
            throw new InvalidDataType(String.format("Invalid data type for function %s", functionType.name()));
        }

        items.forEach(item -> {
            if (!ContainerDataType.PRIMITIVE.isValid(item.getDataType(), item.getValue())) {
                throw new InvalidDataType(
                        String.format("Validation failed for the function %s for the operand %s", functionType.name(), item.getValue()));
            }
        });
        return abstractFunction.evaluate(items);
    }
}

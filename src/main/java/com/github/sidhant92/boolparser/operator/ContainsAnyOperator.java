package com.github.sidhant92.boolparser.operator;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import com.github.sidhant92.boolparser.constant.ContainerDataType;
import com.github.sidhant92.boolparser.constant.DataType;
import com.github.sidhant92.boolparser.constant.Operator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
public class ContainsAnyOperator extends AbstractOperator {
    private final InOperator inOperator;

    @Override
    public <T extends Comparable<? super T>> boolean evaluate(final ContainerDataType containerDataType, final DataType dataType,
                                                              final Object leftOperand, final Object... rightOperands) {
        if (!containerDataType.isValid(dataType, leftOperand)) {
            log.error("Validation failed for any operator for the operand {}", leftOperand);
            return false;
        }
        final Object[] leftOperandArray = ((List<?>) leftOperand).toArray();
        return Arrays
                .stream(rightOperands)
                .anyMatch(rightOperand -> inOperator.evaluate(ContainerDataType.PRIMITIVE, dataType, rightOperand, leftOperandArray));
    }

    @Override
    public Operator getOperator() {
        return Operator.CONTAINS_ANY;
    }

    @Override
    public String getSymbol() {
        return "CONTAINS_ANY";
    }

    @Override
    public List<ContainerDataType> getAllowedContainerTypes() {
        return Collections.singletonList(ContainerDataType.LIST);
    }

    @Override
    public List<DataType> getAllowedDataTypes() {
        return Arrays.asList(DataType.values());
    }
}

package com.github.sidhant92.boolparser.operator;

import com.github.sidhant92.boolparser.constant.ContainerDataType;
import com.github.sidhant92.boolparser.constant.DataType;
import com.github.sidhant92.boolparser.constant.Operator;
import com.github.sidhant92.boolparser.datatype.DataTypeFactory;
import com.github.sidhant92.boolparser.exception.InvalidContainerTypeException;
import com.github.sidhant92.boolparser.exception.InvalidDataType;
import lombok.extern.slf4j.Slf4j;

/**
 * @author sidhant.aggarwal
 * @since 05/03/2023
 */
@Slf4j
public class OperatorService {
    public OperatorService() {
        DataTypeFactory.initialize();
        OperatorFactory.initialize();
    }

    public boolean evaluate(final Operator operator, final ContainerDataType containerDataType, final DataType dataType, final Object leftOperand,
                            final Object... rightOperands) {
        final AbstractOperator abstractOperator = OperatorFactory.getOperator(operator);
        if (!abstractOperator.getAllowedContainerTypes().contains(containerDataType)) {
            log.error("Invalid left container type {} for operator {}", containerDataType, operator);
            throw new InvalidContainerTypeException();
        }
        if (!abstractOperator.getAllowedDataTypes().contains(dataType)) {
            log.error("Invalid left operand data type {} for operator {}", dataType, operator);
            throw new InvalidDataType();
        }
        if (!containerDataType.isValid(dataType, leftOperand)) {
            log.error("Validation failed for the operator {} for the operand {}", operator, leftOperand);
            throw new InvalidDataType();
        }
        return OperatorFactory.getOperator(operator).evaluate(containerDataType, dataType, leftOperand, rightOperands);
    }
}

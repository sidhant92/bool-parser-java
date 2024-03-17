package com.github.sidhant92.boolparser.operator;

import java.util.Objects;
import com.github.sidhant92.boolparser.constant.ContainerDataType;
import com.github.sidhant92.boolparser.constant.DataType;
import com.github.sidhant92.boolparser.constant.Operator;
import com.github.sidhant92.boolparser.datatype.DataTypeFactory;
import com.github.sidhant92.boolparser.exception.InvalidContainerTypeException;
import com.github.sidhant92.boolparser.exception.InvalidDataType;
import com.github.sidhant92.boolparser.operator.logical.AbstractOperator;
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

    public boolean evaluateLogicalOperator(final Operator operator, final ContainerDataType containerDataType, final DataType dataType,
                                           final Object leftOperand, final Object... rightOperands) {
        final AbstractOperator abstractOperator = OperatorFactory.getLogicalOperator(operator);
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
        return OperatorFactory.getLogicalOperator(operator).evaluate(containerDataType, dataType, leftOperand, rightOperands);
    }

    public Object evaluateArithmeticOperator(final Object leftOperand, final DataType leftDataType, final Object rightOperand,
                                             final DataType rightDataType, final Operator operator, final ContainerDataType containerDataType) {
        final com.github.sidhant92.boolparser.operator.arithmetic.AbstractOperator abstractOperator = OperatorFactory.getArithmeticOperator(operator);
        if (!abstractOperator.getAllowedContainerTypes().contains(containerDataType)) {
            log.error("Invalid left container type {} for operator {}", containerDataType, operator);
            throw new InvalidContainerTypeException();
        }
        if (!abstractOperator.getAllowedDataTypes().contains(leftDataType)) {
            log.error("Invalid left operand data type {} for operator {}", leftDataType, operator);
            throw new InvalidDataType();
        }
        if (!containerDataType.isValid(leftDataType, leftOperand)) {
            log.error("Validation failed for the operator {} for the operand {}", operator, leftOperand);
            throw new InvalidDataType();
        }
        if (Objects.nonNull(rightDataType) && !abstractOperator.getAllowedDataTypes().contains(rightDataType)) {
            log.error("Invalid left operand data type {} for operator {}", rightDataType, operator);
            throw new InvalidDataType();
        }
        if (Objects.nonNull(rightOperand) && !containerDataType.isValid(rightDataType, rightOperand)) {
            log.error("Validation failed for the operator {} for the operand {}", operator, rightDataType);
            throw new InvalidDataType();
        }
        return OperatorFactory.getArithmeticOperator(operator).evaluate(leftOperand, leftDataType, rightOperand, rightDataType);
    }
}

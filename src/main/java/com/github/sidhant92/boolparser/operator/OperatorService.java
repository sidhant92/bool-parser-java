package com.github.sidhant92.boolparser.operator;

import java.util.List;
import java.util.Objects;
import org.apache.commons.lang3.tuple.Pair;
import com.github.sidhant92.boolparser.constant.ContainerDataType;
import com.github.sidhant92.boolparser.constant.DataType;
import com.github.sidhant92.boolparser.constant.Operator;
import com.github.sidhant92.boolparser.datatype.DataTypeFactory;
import com.github.sidhant92.boolparser.exception.InvalidContainerTypeException;
import com.github.sidhant92.boolparser.exception.InvalidDataType;
import com.github.sidhant92.boolparser.operator.comparison.AbstractOperator;

/**
 * @author sidhant.aggarwal
 * @since 05/03/2023
 */
public class OperatorService {
    public OperatorService() {
        DataTypeFactory.initialize();
        OperatorFactory.initialize();
    }

    public boolean evaluateLogicalOperator(final Operator operator, final ContainerDataType containerDataType, final Object leftOperand,
                                           final DataType leftOperandDataType, final List<Pair<Object, DataType>> rightOperands) {
        final AbstractOperator abstractOperator = OperatorFactory.getLogicalOperator(operator);
        if (!abstractOperator.getAllowedContainerTypes().contains(containerDataType)) {
            throw new InvalidContainerTypeException(String.format("Invalid left container type %s for operator %s", containerDataType, operator));
        }
        if (!abstractOperator.getAllowedDataTypes().contains(leftOperandDataType)) {
            throw new InvalidDataType(String.format("Invalid left operand data type %s for operator %s", leftOperandDataType, operator));
        }
        if (!containerDataType.isValid(leftOperandDataType, leftOperand)) {
            throw new InvalidDataType(String.format("Validation failed for the operator %s for the operand %s", operator, leftOperand));
        }
        return OperatorFactory.getLogicalOperator(operator).evaluate(containerDataType, leftOperand, leftOperandDataType, rightOperands);
    }

    public Object evaluateArithmeticOperator(final Object leftOperand, final DataType leftDataType, final Object rightOperand,
                                             final DataType rightDataType, final Operator operator, final ContainerDataType containerDataType) {
        final com.github.sidhant92.boolparser.operator.arithmetic.AbstractOperator abstractOperator = OperatorFactory.getArithmeticOperator(operator);
        if (!abstractOperator.getAllowedContainerTypes().contains(containerDataType)) {
            throw new InvalidContainerTypeException(String.format("Invalid left container type %s for operator %s", containerDataType, operator));
        }
        if (!abstractOperator.getAllowedDataTypes().contains(leftDataType)) {
            throw new InvalidDataType(String.format("Invalid left operand data type %s for operator %s", leftDataType, operator));
        }
        if (!containerDataType.isValid(leftDataType, leftOperand)) {
            throw new InvalidDataType(String.format("Validation failed for the operator %s for the operand %s", operator, leftOperand));
        }
        if (Objects.nonNull(rightDataType) && !abstractOperator.getAllowedDataTypes().contains(rightDataType)) {
            throw new InvalidDataType(String.format("Invalid left operand data type %s for operator %s", rightDataType, operator));
        }
        if (Objects.nonNull(rightOperand) && !containerDataType.isValid(rightDataType, rightOperand)) {
            throw new InvalidDataType(String.format("Validation failed for the operator %s for the operand %s", operator, rightDataType));
        }
        return OperatorFactory.getArithmeticOperator(operator).evaluate(leftOperand, leftDataType, rightOperand, rightDataType);
    }
}

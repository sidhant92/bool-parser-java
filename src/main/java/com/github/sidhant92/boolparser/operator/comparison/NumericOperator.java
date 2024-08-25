package com.github.sidhant92.boolparser.operator.comparison;

import com.github.sidhant92.boolparser.constant.ContainerDataType;
import com.github.sidhant92.boolparser.constant.DataType;
import com.github.sidhant92.boolparser.exception.InvalidDataType;

public interface NumericOperator {
    default DataType getComparableDataType(final DataType leftOperandDataType, final DataType rightOperandDataType) {
        if ((leftOperandDataType.numeric && !rightOperandDataType.numeric) || (!leftOperandDataType.numeric && rightOperandDataType.numeric)) {
            return leftOperandDataType.numeric ? leftOperandDataType : rightOperandDataType;
        }
        if (leftOperandDataType.priority > rightOperandDataType.priority) {
            return leftOperandDataType;
        }
        return rightOperandDataType;
    }

    default void validate(final Object leftOperand, final DataType leftOperandDataType, final Object rightOperand,
                          final DataType rightOperandDataType, final ContainerDataType containerDataType) {
        if (!leftOperandDataType.numeric && !rightOperandDataType.numeric) {
            return;
        }
        if (leftOperandDataType.numeric && rightOperandDataType.numeric) {
            return;
        }
        if (!leftOperandDataType.numeric && !containerDataType.isValid(rightOperandDataType, leftOperand)) {
            throw new InvalidDataType(String.format("Incompatible data types %s and %s", leftOperandDataType, rightOperandDataType));
        }
        if (!rightOperandDataType.numeric && !containerDataType.isValid(leftOperandDataType, rightOperand)) {
            throw new InvalidDataType(String.format("Incompatible data types %s and %s", leftOperandDataType, rightOperandDataType));
        }
    }
}

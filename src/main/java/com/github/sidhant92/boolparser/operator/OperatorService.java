package com.github.sidhant92.boolparser.operator;

import com.github.sidhant92.boolparser.constant.ContainerDataType;
import com.github.sidhant92.boolparser.constant.DataType;
import com.github.sidhant92.boolparser.constant.Operator;
import com.github.sidhant92.boolparser.datatype.DataTypeFactory;

/**
 * @author sidhant.aggarwal
 * @since 05/03/2023
 */
public class OperatorService {
    public OperatorService() {
        DataTypeFactory.initialize();
        OperatorFactory.initialize();
    }

    public boolean evaluate(final Operator operator, final ContainerDataType containerDataType, final DataType dataType, final Object leftOperand,
            final Object... rightOperands) {
        return OperatorFactory.getOperator(operator).evaluate(containerDataType, dataType, leftOperand, rightOperands);
    }
}

package com.github.sidhant92.boolparser.function.arithmetic;

import java.util.List;
import org.apache.commons.lang3.tuple.Pair;
import com.github.sidhant92.boolparser.constant.ContainerDataType;
import com.github.sidhant92.boolparser.constant.DataType;
import com.github.sidhant92.boolparser.constant.FunctionType;
import com.github.sidhant92.boolparser.domain.EvaluatedNode;
import com.github.sidhant92.boolparser.domain.arithmetic.ArithmeticFunctionNode;

/**
 * @author sidhant.aggarwal
 * @since 21/05/2024
 */
public abstract class AbstractFunction {
    public abstract Object evaluate(final List<EvaluatedNode> items);

    public abstract FunctionType getFunctionType();

    public abstract List<ContainerDataType> getAllowedContainerTypes();

    public abstract List<DataType> getAllowedDataTypes();
}

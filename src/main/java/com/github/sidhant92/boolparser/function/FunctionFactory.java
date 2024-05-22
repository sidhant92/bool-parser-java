package com.github.sidhant92.boolparser.function;

import java.util.EnumMap;
import java.util.Map;
import com.github.sidhant92.boolparser.constant.FunctionType;
import com.github.sidhant92.boolparser.function.arithmetic.AbstractFunction;
import com.github.sidhant92.boolparser.function.arithmetic.AvgFunction;
import com.github.sidhant92.boolparser.function.arithmetic.IntFunction;
import com.github.sidhant92.boolparser.function.arithmetic.LenFunction;
import com.github.sidhant92.boolparser.function.arithmetic.MaxFunction;
import com.github.sidhant92.boolparser.function.arithmetic.MeanFunction;
import com.github.sidhant92.boolparser.function.arithmetic.MedianFunction;
import com.github.sidhant92.boolparser.function.arithmetic.MinFunction;
import com.github.sidhant92.boolparser.function.arithmetic.ModeFunction;
import com.github.sidhant92.boolparser.function.arithmetic.SumFunction;

/**
 * @author sidhant.aggarwal
 * @since 21/05/2024
 */
public class FunctionFactory {
    private static final Map<FunctionType, AbstractFunction> arithmeticFunctionrMap = new EnumMap<>(FunctionType.class);

    private FunctionFactory() {
        super();
    }

    public static void initialize() {
        arithmeticFunctionrMap.put(FunctionType.MIN, new MinFunction());
        arithmeticFunctionrMap.put(FunctionType.MAX, new MaxFunction());
        arithmeticFunctionrMap.put(FunctionType.AVG, new AvgFunction());
        arithmeticFunctionrMap.put(FunctionType.SUM, new SumFunction());
        arithmeticFunctionrMap.put(FunctionType.MEAN, new MeanFunction());
        arithmeticFunctionrMap.put(FunctionType.MEDIAN, new MedianFunction());
        arithmeticFunctionrMap.put(FunctionType.MODE, new ModeFunction());
        arithmeticFunctionrMap.put(FunctionType.INT, new IntFunction());
        arithmeticFunctionrMap.put(FunctionType.LEN, new LenFunction());
    }

    public static AbstractFunction getArithmeticFunction(final FunctionType functionType) {
        return arithmeticFunctionrMap.get(functionType);
    }
}

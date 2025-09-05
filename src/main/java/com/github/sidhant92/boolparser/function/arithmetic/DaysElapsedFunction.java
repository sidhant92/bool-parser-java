package com.github.sidhant92.boolparser.function.arithmetic;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;
import com.github.sidhant92.boolparser.constant.ContainerDataType;
import com.github.sidhant92.boolparser.constant.DataType;
import com.github.sidhant92.boolparser.constant.FunctionType;
import com.github.sidhant92.boolparser.domain.EvaluatedNode;
import com.github.sidhant92.boolparser.exception.InvalidFunctionArgument;

/**
 * Function to calculate the number of days elapsed since a given date.
 * Returns a positive number for dates in the past, negative for dates in the future.
 * 
 * @author sidhant.aggarwal
 * @since 05/03/2023
 */
public class DaysElapsedFunction extends AbstractFunction {
    
    @Override
    public Object evaluate(final List<EvaluatedNode> items) {
        if (items.size() != 1) {
            throw new InvalidFunctionArgument("DAYS_ELAPSED function requires exactly one argument");
        }
        
        final EvaluatedNode item = items.get(0);
        final Object value = item.getValue();
        
        LocalDate inputDate;
        if (value instanceof LocalDate) {
            inputDate = (LocalDate) value;
        } else if (value instanceof String) {
            // Try to parse string as date if the DataType detection missed it
            try {
                inputDate = LocalDate.parse((String) value);
            } catch (Exception e) {
                throw new InvalidFunctionArgument("DAYS_ELAPSED function requires a valid date argument, got: " + value);
            }
        } else {
            throw new InvalidFunctionArgument("DAYS_ELAPSED function requires a date argument, got: " + value.getClass().getSimpleName());
        }
        
        final LocalDate today = LocalDate.now();
        return ChronoUnit.DAYS.between(inputDate, today);
    }

    @Override
    public FunctionType getFunctionType() {
        return FunctionType.DAYS_ELAPSED;
    }

    @Override
    public List<ContainerDataType> getAllowedContainerTypes() {
        return Arrays.asList(ContainerDataType.PRIMITIVE);
    }

    @Override
    public List<DataType> getAllowedDataTypes() {
        return Arrays.asList(DataType.DATE);
    }
}
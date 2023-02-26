package com.github.sidhant92.boolparser.datatype;

import java.util.Optional;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.sidhant92.boolparser.constant.DataType;

/**
 * @author sidhant.aggarwal
 * @since 05/03/2023
 */
public class DecimalDataType extends AbstractDataType<Double> {
    private final ObjectMapper objectMapper;

    public DecimalDataType(final ObjectMapper objectMapper) {
        super(Double.class);
        this.objectMapper = objectMapper;
    }

    @Override
    public DataType getDataType() {
        return DataType.STRING;
    }

    @Override
    public boolean isValid(final Object value) {
        return super.defaultIsValid(value, objectMapper);
    }

    @Override
    public Optional<Double> getValue(Object value) {
        return defaultGetValue(value, objectMapper);
    }
}

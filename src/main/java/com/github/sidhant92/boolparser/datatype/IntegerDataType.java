package com.github.sidhant92.boolparser.datatype;

import java.util.Optional;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.sidhant92.boolparser.constant.DataType;

/**
 * @author sidhant.aggarwal
 * @since 05/03/2023
 */
public class IntegerDataType extends AbstractDataType<Integer> {
    private final ObjectMapper objectMapper;

    public IntegerDataType(final ObjectMapper objectMapper) {
        super(Integer.class);
        this.objectMapper = objectMapper;
    }

    @Override
    public DataType getDataType() {
        return DataType.INTEGER;
    }

    @Override
    public boolean isValid(final Object value) {
        return super.defaultIsValid(value, objectMapper);
    }

    @Override
    public Optional<Integer> getValue(Object value) {
        return defaultGetValue(value, objectMapper);
    }
}

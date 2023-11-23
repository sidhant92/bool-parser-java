package com.github.sidhant92.boolparser.datatype;

import java.util.Optional;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.sidhant92.boolparser.constant.DataType;

/**
 * @author sidhant.aggarwal
 * @since 05/03/2023
 */
public class BooleanDataType extends AbstractDataType<Boolean> {
    private final ObjectMapper objectMapper;

    public BooleanDataType(final ObjectMapper objectMapper) {
        super(Boolean.class);
        this.objectMapper = objectMapper;
    }

    @Override
    public DataType getDataType() {
        return DataType.BOOLEAN;
    }

    @Override
    public boolean isValid(final Object value) {
        return super.defaultIsValid(value, objectMapper);
    }

    @Override
    public boolean isValid(final Object value, final boolean useStrictValidation) {
        return super.defaultIsValid(value, objectMapper, useStrictValidation);
    }

    @Override
    public Optional<Boolean> getValue(Object value) {
        return defaultGetValue(value, objectMapper);
    }
}

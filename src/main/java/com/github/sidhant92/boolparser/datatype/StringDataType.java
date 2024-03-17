package com.github.sidhant92.boolparser.datatype;

import java.util.Optional;
import com.github.sidhant92.boolparser.constant.DataType;

/**
 * @author sidhant.aggarwal
 * @since 05/03/2023
 */
public class StringDataType extends AbstractDataType<String> {
    public StringDataType() {
        super(String.class);
    }

    @Override
    public DataType getDataType() {
        return DataType.STRING;
    }

    @Override
    public boolean isValid(final Object value) {
        return true;
    }

    @Override
    public boolean isValid(final Object value, final boolean useStrictValidation) {
        if (!useStrictValidation) {
            return isValid(value);
        }
        return super.defaultIsValid(value);
    }

    @Override
    public Optional<String> getValue(Object value) {
        final Optional<String> result = defaultGetValue(value);
        if (result.isPresent()) {
            return result;
        }
        return Optional.of(value.toString());
    }
}

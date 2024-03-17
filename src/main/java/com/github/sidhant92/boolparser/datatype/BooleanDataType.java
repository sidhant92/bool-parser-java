package com.github.sidhant92.boolparser.datatype;

import java.util.Optional;
import com.github.sidhant92.boolparser.constant.DataType;

/**
 * @author sidhant.aggarwal
 * @since 05/03/2023
 */
public class BooleanDataType extends AbstractDataType<Boolean> {
    public BooleanDataType() {
        super(Boolean.class);
    }

    @Override
    public DataType getDataType() {
        return DataType.BOOLEAN;
    }

    @Override
    public boolean isValid(final Object value) {
        boolean isValid = super.defaultIsValid(value);
        if (!isValid) {
            final String lowercase = value.toString().toLowerCase();
            return lowercase.equals("true") || lowercase.equals("false");
        }
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
    public Optional<Boolean> getValue(Object value) {
        final Optional<Boolean> result = defaultGetValue(value);
        if (result.isPresent()) {
            return result;
        }
        if (this.isValid(value)) {
            return Optional.of(Boolean.parseBoolean(value.toString()));
        }
        return Optional.empty();
    }
}

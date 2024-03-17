package com.github.sidhant92.boolparser.datatype;

import java.util.Optional;
import com.github.sidhant92.boolparser.constant.DataType;

/**
 * @author sidhant.aggarwal
 * @since 05/03/2023
 */
public class LongDataType extends AbstractDataType<Long> {
    public LongDataType() {
        super(Long.class);
    }

    @Override
    public DataType getDataType() {
        return DataType.LONG;
    }

    @Override
    public boolean isValid(final Object value) {
        boolean isValid = super.defaultIsValid(value);
        if (!isValid) {
            try {
                Long.parseLong(value.toString());
                return true;
            } catch (Exception ex) {
                return false;
            }
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
    public Optional<Long> getValue(Object value) {
        final Optional<Long> result = defaultGetValue(value);
        if (result.isPresent()) {
            return result;
        }
        try {
            return Optional.of(Long.parseLong(value.toString()));
        } catch (final Exception ignored) {
        }
        return Optional.empty();
    }
}

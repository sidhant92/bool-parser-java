package com.github.sidhant92.boolparser.datatype;

import java.util.Optional;
import com.github.sidhant92.boolparser.constant.DataType;

/**
 * @author sidhant.aggarwal
 * @since 05/03/2023
 */
public class IntegerDataType extends AbstractDataType<Integer> {
    public IntegerDataType() {
        super(Integer.class);
    }

    @Override
    public DataType getDataType() {
        return DataType.INTEGER;
    }

    @Override
    public boolean isValid(final Object value) {
        boolean isValid = super.defaultIsValid(value);
        if (!isValid) {
            try {
                Integer.parseInt(value.toString());
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
    public Optional<Integer> getValue(Object value) {
        final Optional<Integer> result = defaultGetValue(value);
        if (result.isPresent()) {
            return result;
        }
        try {
            return Optional.of(Integer.parseInt(value.toString()));
        } catch (final Exception ignored) {
        }
        return Optional.empty();
    }
}

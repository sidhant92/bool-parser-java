package com.github.sidhant92.boolparser.datatype;

import java.math.BigDecimal;
import java.util.Optional;
import com.github.sidhant92.boolparser.constant.DataType;

/**
 * @author sidhant.aggarwal
 * @since 05/03/2023
 */
public class DecimalDataType extends AbstractDataType<BigDecimal> {
    public DecimalDataType() {
    super(BigDecimal.class);
    }

    @Override
    public DataType getDataType() {
        return DataType.DECIMAL;
    }

    @Override
    public boolean isValid(final Object value) {
        boolean isValid = super.defaultIsValid(value);
        if (!isValid) {
            try {
                new BigDecimal(value.toString());
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
    public Optional<BigDecimal> getValue(Object value) {
        final Optional<BigDecimal> result = defaultGetValue(value);
        if (result.isPresent()) {
            return result;
        }
        try {
            return Optional.of(new BigDecimal(value.toString()));
        } catch (final Exception ignored) {
        }
        return Optional.empty();
    }
}

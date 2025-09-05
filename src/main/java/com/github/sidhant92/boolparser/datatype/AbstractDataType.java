package com.github.sidhant92.boolparser.datatype;

import java.util.Optional;
import com.github.sidhant92.boolparser.constant.DataType;

/**
 * @author sidhant.aggarwal
 * @since 05/03/2023
 */
public abstract class AbstractDataType<T extends Comparable<? super T>> {
    private final Class<T> clazz;

    public AbstractDataType(final Class<T> clazz) {
        this.clazz = clazz;
    }

    protected boolean defaultIsValid(final Object value) {
        return defaultIsValid(value, false);
    }

    protected boolean defaultIsValid(final Object value, final boolean useStrictValidation) {
        return clazz.isInstance(value);
    }

    protected Optional<T> defaultGetValue(final Object value) {
        if (clazz.isInstance(value)) {
            return Optional.of(clazz.cast(value));
        }
        return Optional.empty();
    }

    public abstract DataType getDataType();

    public abstract boolean isValid(final Object value);

    public abstract boolean isValid(final Object value, final boolean useStrictValidation);

    public abstract Optional<T> getValue(final Object value);
}

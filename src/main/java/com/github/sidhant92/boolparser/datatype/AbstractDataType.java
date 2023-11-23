package com.github.sidhant92.boolparser.datatype;

import java.util.Optional;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.sidhant92.boolparser.constant.DataType;
import lombok.extern.slf4j.Slf4j;

/**
 * @author sidhant.aggarwal
 * @since 05/03/2023
 */
@Slf4j
public abstract class AbstractDataType<T extends Comparable<? super T>> {
    private final Class<T> clazz;

    public AbstractDataType(final Class<T> clazz) {
        this.clazz = clazz;
    }

    public boolean defaultIsValid(final Object value, final ObjectMapper objectMapper) {
       return defaultIsValid(value, objectMapper, false);
    }

    public boolean defaultIsValid(final Object value, final ObjectMapper objectMapper, final boolean useStrictValidation) {
        try {
            if (clazz.isInstance(value)) {
                return true;
            }
            if (useStrictValidation) {
                return false;
            }
            return objectMapper.convertValue(value, clazz) != null;
        } catch (final Exception ex) {
            log.error("Unable to convert value = {} to type = {}", value, clazz);
        }
        return false;
    }

    public Optional<T> defaultGetValue(final Object value, final ObjectMapper objectMapper) {
        try {
            if (clazz.isInstance(value)) {
                return Optional.of(clazz.cast(value));
            }
            return Optional.of(objectMapper.convertValue(value, clazz));
        } catch (final Exception ex) {
            log.error("Unable to convert value = {} to type = {}", value, clazz);
        }
        return Optional.empty();
    }

    public abstract DataType getDataType();

    public abstract boolean isValid(final Object value);

    public abstract boolean isValid(final Object value, final boolean useStrictValidation);

    public abstract Optional<T> getValue(final Object value);
}

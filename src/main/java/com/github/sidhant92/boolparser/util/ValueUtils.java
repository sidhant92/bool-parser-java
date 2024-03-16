package com.github.sidhant92.boolparser.util;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.apache.maven.artifact.versioning.ComparableVersion;
import com.github.sidhant92.boolparser.constant.DataType;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ValueUtils {
    public static Optional<Object> getValueFromMap(final String key, final Map<String, Object> data) {
        final String[] keys = key.split("\\.");
        final int size = keys.length;
        final Optional<Object> fieldData = Optional.ofNullable(data.get(keys[0]));
        if (size == 1) {
            return fieldData;
        }
        if (fieldData.isPresent() && fieldData.get() instanceof Map) {
            try {
                final String newKey = Arrays
                        .stream(keys).skip(1)
                        .collect(Collectors.joining("."));
                return getValueFromMap(newKey, (Map<String, Object>) fieldData.get());
            } catch (ClassCastException ex) {
                return Optional.empty();
            }
        }
        log.error("could not find key {} for the data {}", key, data);
        return Optional.empty();
    }

    public static Object convertValue(final String value, final DataType dataType) {
        switch (dataType) {
            case INTEGER:
                return Integer.parseInt(value);
            case LONG:
                return Long.parseLong(value);
            case DECIMAL:
                return Double.parseDouble(value);
            case BOOLEAN:
                return Boolean.parseBoolean(value);
            case VERSION:
                new ComparableVersion(value);
            default:
                if (value.startsWith("'") && value.endsWith("'")) {
                    return value.substring(1, value.length() - 1);
                }
                if (value.startsWith("\"") && value.endsWith("\"")) {
                    return value.substring(1, value.length() - 1);
                }
                return value;
        }
    }

    public static DataType getNumericDataType(final String value) {
        final Optional<Integer> integerOptional = parseInteger(value);
        return integerOptional.isPresent() ? DataType.INTEGER : DataType.LONG;
    }

    public static Optional<Integer> parseInteger(final String number) {
        try {
            return Optional.of(Integer.parseInt(number));
        } catch (NumberFormatException ex) {
            return Optional.empty();
        }
    }

    public static DataType getDataType(final Object value) {
        if (value instanceof Boolean) {
            return DataType.BOOLEAN;
        }
        if (value instanceof Float || value instanceof Double) {
            return DataType.DECIMAL;
        }
        if (value instanceof Integer) {
            return DataType.INTEGER;
        }
        if (value instanceof Long) {
            return DataType.LONG;
        }
        return DataType.STRING;
    }
}

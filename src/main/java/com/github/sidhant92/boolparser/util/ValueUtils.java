package com.github.sidhant92.boolparser.util;

import java.util.Optional;
import org.apache.maven.artifact.versioning.ComparableVersion;
import com.github.sidhant92.boolparser.constant.DataType;

public class ValueUtils {
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
            case APP_VERSION:
                new ComparableVersion(value);
            default:
                if (value.startsWith("'") && value.endsWith("'")) {
                    return value.substring(1, value.length() -1);
                }
                if (value.startsWith("\"") && value.endsWith("\"")) {
                    return value.substring(1, value.length() -1);
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
}

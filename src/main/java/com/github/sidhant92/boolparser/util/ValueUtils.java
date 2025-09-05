package com.github.sidhant92.boolparser.util;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.apache.maven.artifact.versioning.ComparableVersion;
import com.github.sidhant92.boolparser.constant.DataType;
import com.github.sidhant92.boolparser.domain.EvaluatedNode;

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
        return Optional.empty();
    }

    public static List<EvaluatedNode> mapToEvaluatedNodes(final List<Object> items) {
        final List<EvaluatedNode> flattenedValues = new ArrayList<>();
        items.forEach(value -> {
            if (value instanceof EvaluatedNode) {
                final EvaluatedNode node = (EvaluatedNode) value;
                if (node.getValue() instanceof Collection) {
                    ((Collection<?>) node.getValue()).forEach(
                            v -> flattenedValues.add(EvaluatedNode.builder().value(v).dataType(ValueUtils.getDataType(v)).build()));
                } else {
                    flattenedValues.add(node);
                }
            }
            if (value instanceof Collection) {
                ((Collection<?>) value).forEach(
                        v -> flattenedValues.add(EvaluatedNode.builder().value(v).dataType(ValueUtils.getDataType(v)).build()));
            } else {
                flattenedValues.add(EvaluatedNode.builder().value(value).dataType(ValueUtils.getDataType(value)).build());
            }
        });
        return flattenedValues;
    }

    public static Object convertValue(final String value, final DataType dataType) {
        switch (dataType) {
            case INTEGER:
                return Integer.parseInt(value);
            case LONG:
                return Long.parseLong(value);
            case DECIMAL:
                return new BigDecimal(value);
            case BOOLEAN:
                return Boolean.parseBoolean(value);
            case VERSION:
                return new ComparableVersion(value);
            case DATE:
                return parseDate(value).orElse(null);
            case DATETIME:
                return parseDateTime(value).orElse(null);
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

    public static Object castDecimal(final double value) {
        if ((int) value == value) {
            return (int) value;
        }
        return new BigDecimal(value);
    }

    public static Object castLong(final long value) {
        if ((int) value == value) {
            return (int) value;
        }
        return value;
    }

    public static Object castDecimal(final BigDecimal value) {
        if (value.signum() == 0 || value.scale() <= 0 || value.stripTrailingZeros().scale() <= 0) {
            return value.intValueExact();
        }
        return value;
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
        if (value instanceof Float || value instanceof Double || value instanceof BigDecimal) {
            return DataType.DECIMAL;
        }
        if (value instanceof Integer) {
            return DataType.INTEGER;
        }
        if (value instanceof Long) {
            return DataType.LONG;
        }
        if (value instanceof ComparableVersion) {
            return DataType.VERSION;
        }
        if (value instanceof LocalDate) {
            return DataType.DATE;
        }
        if (value instanceof LocalDateTime) {
            return DataType.DATETIME;
        }
        if (value instanceof String) {
            String stringValue = (String) value;
            // Check if string represents a datetime first (more specific)
            if (parseDateTime(stringValue).isPresent()) {
                return DataType.DATETIME;
            }
            // Then check if it's a date
            if (parseDate(stringValue).isPresent()) {
                return DataType.DATE;
            }
        }
        return DataType.STRING;
    }

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private static Optional<LocalDate> parseDate(String dateString) {
        try {
            return Optional.of(LocalDate.parse(dateString, DATE_FORMATTER));
        } catch (DateTimeParseException ignored) {
            return Optional.empty();
        }
    }

    private static Optional<LocalDateTime> parseDateTime(String dateTimeString) {
        try {
            return Optional.of(LocalDateTime.parse(dateTimeString, DATETIME_FORMATTER));
        } catch (DateTimeParseException ignored) {
            return Optional.empty();
        }
    }
}

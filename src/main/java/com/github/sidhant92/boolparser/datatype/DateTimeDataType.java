package com.github.sidhant92.boolparser.datatype;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;
import com.github.sidhant92.boolparser.constant.DataType;

/**
 * @author sidhant.aggarwal
 * @since 05/03/2023
 */
public class DateTimeDataType extends AbstractDataType<LocalDateTime> {
    private static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public DateTimeDataType() {
        super(LocalDateTime.class);
    }

    @Override
    public DataType getDataType() {
        return DataType.DATETIME;
    }

    @Override
    public boolean isValid(final Object value) {
        boolean isValid = super.defaultIsValid(value);
        if (!isValid) {
            return parseDateTime(value.toString()).isPresent();
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
    public Optional<LocalDateTime> getValue(Object value) {
        final Optional<LocalDateTime> result = defaultGetValue(value);
        if (result.isPresent()) {
            return result;
        }
        return parseDateTime(value.toString());
    }

    private Optional<LocalDateTime> parseDateTime(String dateTimeString) {
        try {
            return Optional.of(LocalDateTime.parse(dateTimeString, DATETIME_FORMATTER));
        } catch (DateTimeParseException ignored) {
            return Optional.empty();
        }
    }
}
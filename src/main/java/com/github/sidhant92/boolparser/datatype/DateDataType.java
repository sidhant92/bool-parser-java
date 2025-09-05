package com.github.sidhant92.boolparser.datatype;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;
import com.github.sidhant92.boolparser.constant.DataType;

/**
 * @author sidhant.aggarwal
 * @since 05/03/2023
 */
public class DateDataType extends AbstractDataType<LocalDate> {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public DateDataType() {
        super(LocalDate.class);
    }

    @Override
    public DataType getDataType() {
        return DataType.DATE;
    }

    @Override
    public boolean isValid(final Object value) {
        boolean isValid = super.defaultIsValid(value);
        if (!isValid) {
            return parseDate(value.toString()).isPresent();
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
    public Optional<LocalDate> getValue(Object value) {
        final Optional<LocalDate> result = defaultGetValue(value);
        if (result.isPresent()) {
            return result;
        }
        return parseDate(value.toString());
    }

    private Optional<LocalDate> parseDate(String dateString) {
        try {
            return Optional.of(LocalDate.parse(dateString, DATE_FORMATTER));
        } catch (DateTimeParseException ignored) {
            return Optional.empty();
        }
    }
}
package com.github.sidhant92.boolparser.constant;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import com.github.sidhant92.boolparser.datatype.DataTypeFactory;
import com.github.sidhant92.boolparser.exception.InvalidDataType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author sidhant.aggarwal
 * @since 05/02/2023
 */
@Getter
@AllArgsConstructor
@Slf4j
public enum ContainerDataType {
    PRIMITIVE() {
        @Override
        public <T> Optional<T> getValue(final DataType dataType, final Object value) {
            final Optional<T> result = DataTypeFactory.getDataType(dataType).getValue(value);
            if (!result.isPresent()) {
                log.error("Invalid data type for value {} for data type {}", value.toString(), dataType.name());
                throw new InvalidDataType(String.format("Invalid data type for value %s for data type %s", value, dataType.name()));
            }
            return result;
        }

        @Override
        public boolean isValid(final DataType dataType, final Object value) {
            return DataTypeFactory.getDataType(dataType).isValid(value);
        }
    },
    LIST() {
        @Override
        public Optional<List<?>> getValue(final DataType dataType, final Object value) {
            if (Objects.isNull(value) || !(value instanceof Collection<?> || value instanceof Object[])) {
                return Optional.empty();
            }
            if (value instanceof Object[]) {
                return Optional.of(Stream.of((Object[]) value)
                                           .map(v -> DataTypeFactory.getDataType(dataType).getValue(v))
                                           .filter(Optional::isPresent)
                                           .map(Optional::get)
                                           .collect(Collectors.toList()));
            }
            return Optional.of(((Collection<?>) value)
                                       .stream()
                                       .map(v -> DataTypeFactory.getDataType(dataType).getValue(v))
                                       .filter(Optional::isPresent)
                                       .map(Optional::get)
                                       .collect(Collectors.toList()));
        }

        @Override
        public boolean isValid(final DataType dataType, final Object value) {
            if (Objects.isNull(value) || !(value instanceof Collection<?> || value instanceof Object[])) {
                return false;
            }
            if (value instanceof Object[]) {
                return Stream.of((Object[]) value).allMatch(v -> DataTypeFactory.getDataType(dataType).isValid(v));
            }
            return ((Collection<?>) value)
                    .stream().allMatch(v -> DataTypeFactory.getDataType(dataType).isValid(v));
        }
    };

    public abstract <T> Optional<T> getValue(final DataType dataType, final Object value);

    public abstract boolean isValid(final DataType dataType, final Object value);
}

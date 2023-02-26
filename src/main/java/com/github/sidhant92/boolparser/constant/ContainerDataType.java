package com.github.sidhant92.boolparser.constant;

import java.util.Optional;
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
    primitive() {
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
    };

    public abstract <T> Optional<T> getValue(final DataType dataType, final Object value);

    public abstract boolean isValid(final DataType dataType, final Object value);
}

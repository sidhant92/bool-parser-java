package com.github.sidhant92.boolparser.constant;

import java.util.Optional;
import com.github.sidhant92.boolparser.datatype.DataTypeFactory;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author sidhant.aggarwal
 * @since 05/02/2023
 */
@Getter
@AllArgsConstructor
public enum ContainerDataType {
    primitive() {
        @Override
        public <T> Optional<T> getValue(final DataType dataType, final Object value) {
            return DataTypeFactory.getDataType(dataType).getValue(value);
        }

        @Override
        public boolean isValid(final DataType dataType, final Object value) {
            return DataTypeFactory.getDataType(dataType).isValid(value);
        }
    };

    public abstract <T> Optional<T> getValue(final DataType dataType, final Object value);

    public abstract boolean isValid(final DataType dataType, final Object value);
}

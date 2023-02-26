package com.github.sidhant92.boolparser.datatype;

import java.util.EnumMap;
import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.sidhant92.boolparser.constant.DataType;

/**
 * @author sidhant.aggarwal
 * @since 05/03/2023
 */
public class DataTypeFactory {
    private static final Map<DataType, AbstractDataType<?>> abstractDataTypeMap = new EnumMap<>(DataType.class);

    private DataTypeFactory() {
        super();
    }

    public static void initialize() {
        final ObjectMapper objectMapper = new ObjectMapper();
        abstractDataTypeMap.put(DataType.STRING, new StringDataType(objectMapper));
        abstractDataTypeMap.put(DataType.INTEGER, new IntegerDataType(objectMapper));
        abstractDataTypeMap.put(DataType.DECIMAL, new DecimalDataType(objectMapper));
        abstractDataTypeMap.put(DataType.LONG, new LongDataType(objectMapper));
        abstractDataTypeMap.put(DataType.VERSION, new VersionDataType(objectMapper));
        abstractDataTypeMap.put(DataType.BOOLEAN, new BooleanDataType(objectMapper));
    }

    public static AbstractDataType getDataType(final DataType dataType) {
        return abstractDataTypeMap.get(dataType);
    }

    public static void register(final AbstractDataType abstractDataType) {
        abstractDataTypeMap.put(abstractDataType.getDataType(), abstractDataType);
    }
}

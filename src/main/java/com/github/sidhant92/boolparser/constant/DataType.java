package com.github.sidhant92.boolparser.constant;

import lombok.AllArgsConstructor;

/**
 * @author sidhant.aggarwal
 * @since 05/03/2023
 */
@AllArgsConstructor
public enum DataType {
    STRING(8, false),
    INTEGER(3, true),
    LONG(4, true),
    DECIMAL(5, true),
    VERSION(2, true),
    BOOLEAN(1, false),
    DATE(6, false),
    DATETIME(7, false);

    public final int priority;

    public final boolean numeric;
}

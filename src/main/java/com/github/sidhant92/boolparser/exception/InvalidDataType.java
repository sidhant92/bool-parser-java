package com.github.sidhant92.boolparser.exception;

public class InvalidDataType extends RuntimeException {
    public InvalidDataType(final String message) {
        super(message);
    }

    public InvalidDataType() {
        super();
    }

    @Override
    public String getMessage() {
        return "Invalid Data Type";
    }
}

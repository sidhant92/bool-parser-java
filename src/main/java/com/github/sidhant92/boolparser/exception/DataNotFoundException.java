package com.github.sidhant92.boolparser.exception;

public class DataNotFoundException extends RuntimeException {
    private static final String MESSAGE = "Data not found";

    public DataNotFoundException(final String key) {
        super(String.format(MESSAGE + " for the key %s", key));
    }

    @Override
    public String getMessage() {
        return MESSAGE;
    }
}

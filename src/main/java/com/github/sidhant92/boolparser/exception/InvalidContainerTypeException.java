package com.github.sidhant92.boolparser.exception;

public class InvalidContainerTypeException extends RuntimeException {
    public InvalidContainerTypeException(final String message) {
        super(message);
    }

    public InvalidContainerTypeException() {
        super();
    }

    @Override
    public String getMessage() {
        return "Invalid Container Type";
    }
}

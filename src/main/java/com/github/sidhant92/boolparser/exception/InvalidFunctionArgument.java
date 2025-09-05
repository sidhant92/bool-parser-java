package com.github.sidhant92.boolparser.exception;

public class InvalidFunctionArgument extends RuntimeException {
    public InvalidFunctionArgument(final String message) {
        super(message);
    }

    public InvalidFunctionArgument() {
        super();
    }
}
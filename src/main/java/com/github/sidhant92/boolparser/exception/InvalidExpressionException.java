package com.github.sidhant92.boolparser.exception;

public class InvalidExpressionException extends RuntimeException {
    public InvalidExpressionException(final String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return "Invalid Expression";
    }
}

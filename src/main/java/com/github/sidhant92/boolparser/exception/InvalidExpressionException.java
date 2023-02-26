package com.github.sidhant92.boolparser.exception;

public class InvalidExpressionException extends RuntimeException {
    @Override
    public String getMessage() {
        return "Invalid Expression";
    }
}

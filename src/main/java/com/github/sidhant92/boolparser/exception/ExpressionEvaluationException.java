package com.github.sidhant92.boolparser.exception;

public class ExpressionEvaluationException extends RuntimeException {
    @Override
    public String getMessage() {
        return "Error evaluating boolean expression";
    }
}

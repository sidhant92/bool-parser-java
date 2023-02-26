package com.github.sidhant92.boolparser.exception;

public class InvalidUnaryOperand extends RuntimeException {
    @Override
    public String getMessage() {
        return "Invalid operand data type in unary expression";
    }
}

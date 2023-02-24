package com.github.sidhant92.boolparser.exception;

public class DataNotFoundException extends RuntimeException {
    @Override
    public String getMessage() {
        return "Data not found";
    }
}

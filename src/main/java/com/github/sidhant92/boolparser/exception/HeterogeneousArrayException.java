package com.github.sidhant92.boolparser.exception;

public class HeterogeneousArrayException extends RuntimeException{
    @Override
    public String getMessage() {
        return "Heterogeneous input of array not allowed";
    }
}

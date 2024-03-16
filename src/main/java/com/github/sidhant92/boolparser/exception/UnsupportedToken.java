package com.github.sidhant92.boolparser.exception;

public class UnsupportedToken extends RuntimeException {
    public UnsupportedToken(final String message) {
        super(message);
    }

    public UnsupportedToken() {
        super();
    }

    @Override
    public String getMessage() {
        return "Unsupported Token";
    }
}

package com.github.sidhant92.boolparser.exception;

public class UnsupportedToken extends RuntimeException {
    private static final String MESSAGE = "Unsupported Token";

    public UnsupportedToken(final String token) {
        super(String.format(MESSAGE + " %s", token));
    }

    public UnsupportedToken() {
        super();
    }

    @Override
    public String getMessage() {
        return MESSAGE;
    }
}

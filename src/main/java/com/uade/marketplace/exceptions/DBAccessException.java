package com.uade.marketplace.exceptions;

public class DBAccessException extends RuntimeException {
    public DBAccessException(String message) {
        super(message);
    }

    public DBAccessException(String message, Throwable cause) {
        super(message, cause);
    }
}

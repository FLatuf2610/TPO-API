package com.uade.marketplace.exceptions.cart;

public class NoActiveCartException extends RuntimeException {
    public NoActiveCartException(String message) {
        super(message);
    }
}

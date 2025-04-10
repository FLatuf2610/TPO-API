package com.uade.marketplace.exceptions.order;

public class OrderCanNotBePayedException extends RuntimeException {
    public OrderCanNotBePayedException(String message) {
        super(message);
    }
}

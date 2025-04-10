package com.uade.marketplace.exceptions.product;

public class UserCanNotBuyItsOwnProductsException extends RuntimeException {
    public UserCanNotBuyItsOwnProductsException(String message) {
        super(message);
    }
}

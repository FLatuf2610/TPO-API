package com.uade.marketplace.exceptions.product;

public class UserNotAllowedToModifyOtherUserProductException extends RuntimeException {
    public UserNotAllowedToModifyOtherUserProductException(String message) {
        super(message);
    }
}

package com.uade.marketplace.service.cart;

import com.uade.marketplace.controller.dto.request.cart.CartItemRequest;
import com.uade.marketplace.controller.dto.response.cart.CartResponse;
import com.uade.marketplace.data.entities.UserEntity;
import com.uade.marketplace.models.Cart;

import java.util.List;

public interface CartService {
    Double checkout(UserEntity userEntity);

    CartResponse getCartByUser(UserEntity userEntity);

    CartResponse addItemToCart(UserEntity user, CartItemRequest request);

    CartResponse updateCartItem(UserEntity user, Long itemId, CartItemRequest request);

    CartResponse removeItemFromCart(UserEntity user, Long itemId);
}

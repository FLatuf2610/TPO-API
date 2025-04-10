package com.uade.marketplace.controller.controllers;

import com.uade.marketplace.controller.dto.request.cart.CartItemRequest;
import com.uade.marketplace.controller.dto.response.cart.CartResponse;
import com.uade.marketplace.controller.dto.response.cart.CheckoutResponse;
import com.uade.marketplace.data.entities.UserEntity;
import com.uade.marketplace.service.cart.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping
    public ResponseEntity<CartResponse> getCart(Authentication authentication) {
        UserEntity user = (UserEntity) authentication.getPrincipal();
        return ResponseEntity.ok(cartService.getCartByUser(user));
    }

    @PostMapping("/items")
    public ResponseEntity<CartResponse> addItemToCart(
            @RequestBody CartItemRequest request,
            Authentication authentication) {
        UserEntity user = (UserEntity) authentication.getPrincipal();
        return ResponseEntity.ok(cartService.addItemToCart(user, request));
    }

    @PutMapping("/items/{itemId}")
    public ResponseEntity<CartResponse> updateCartItem(
            @PathVariable Long itemId,
            @RequestBody CartItemRequest request,
            Authentication authentication) {
        UserEntity user = (UserEntity) authentication.getPrincipal();
        return ResponseEntity.ok(cartService.updateCartItem(user, itemId, request));
    }

    @DeleteMapping("/items/{itemId}")
    public ResponseEntity<CartResponse> removeItemFromCart(
            @PathVariable Long itemId,
            Authentication authentication) {
        UserEntity user = (UserEntity) authentication.getPrincipal();
        return ResponseEntity.ok(cartService.removeItemFromCart(user, itemId));
    }

    @PostMapping("/checkout")
    public ResponseEntity<?> checkout(Authentication authentication) {
        UserEntity user = (UserEntity) authentication.getPrincipal();
        Double total = cartService.checkout(user);

        CheckoutResponse response = CheckoutResponse.builder()
                .message("Checkout completado con éxito")
                .total(total)
                .build();

        return ResponseEntity.ok(response);
    }

}

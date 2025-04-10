package com.uade.marketplace.controller.dto.request.cart;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartProductRequest {
    private Long productId;
    private Integer quantity;
}
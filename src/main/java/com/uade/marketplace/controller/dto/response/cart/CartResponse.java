package com.uade.marketplace.controller.dto.response.cart;

import com.uade.marketplace.models.CartProduct;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartResponse {
    private Long id;
    private List<CartProduct> products;
    private Double total;
}
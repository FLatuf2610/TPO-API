package com.uade.marketplace.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class Cart {
    private Long id;
    private List<CartProduct> products;
    private Double total;
    private Long userId;

    public Cart(Long id, List<CartProduct> products, Long userId) {
        this.id = id;
        this.products = products;
        this.total = calculateTotal(products);
        this.userId = userId;
    }

    public Double calculateTotal(List<CartProduct> products) {
        return products
                .stream()
                .mapToDouble(product -> product.getProduct().getPrice() * product.getQuantity())
                .sum();
    }
}

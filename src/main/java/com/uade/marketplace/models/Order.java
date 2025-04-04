package com.uade.marketplace.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private Long id;
    private List<Product> products;
    private Double total;
    private Long userId;

    public Order(Long id, List<Product> products, Long userId) {
        this.id = id;
        this.products = products;
        this.total = calculateTotal(products);
        this.userId = userId;
    }

    public Double calculateTotal(List<Product> products) {
        return products.stream().map(Product::getPrice).reduce(0.0, Double::sum);
    }
}

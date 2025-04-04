package com.uade.marketplace.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class Order {
    private Long id;
    private List<OrderProduct> products;
    private Double total;
    private OrderStatus status;
    private Long userId;

    public Order(Long id, List<OrderProduct> products, OrderStatus status,Long userId) {
        this.id = id;
        this.products = products;
        this.status = status;
        this.total = calculateTotal(products);
        this.userId = userId;
    }

    public Double calculateTotal(List<OrderProduct> products) {
        return products
                .stream()
                .map(OrderProduct::getPrice)
                .reduce(0.0, Double::sum);
    }
}

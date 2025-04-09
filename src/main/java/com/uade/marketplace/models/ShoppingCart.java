package com.uade.marketplace.models;

import lombok.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ShoppingCart {
    private Long id;
    private List<Product> products = new ArrayList<>();

    // Add a product to the cart
    public void addProduct(Product product) {
        this.products.add(product);
    }

    // Remove a product from the cart
    public void removeProduct(Product product) {
        this.products.remove(product);
    }

    // Calculate the total price of all products in the cart
    public double calculateTotalPrice() {
        return products.stream()
                .mapToDouble(Product::getPrice)
                .sum();
    }

    // Get the total quantity of items in the cart
    public int calculateTotalQuantity() {
        return products.stream()
                .mapToInt(Product::getQuantity)
                .sum();
    }
}

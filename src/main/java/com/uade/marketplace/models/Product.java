package com.uade.marketplace.models;

import com.uade.marketplace.controller.dto.request.product.CreateProductRequest;
import com.uade.marketplace.exceptions.product.NotEnoughStockException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private int quantity;
    private Category category;
    private Long userId;

    public void sell(int quantityToSell) {
        int newStock = quantity - quantityToSell;
        if (newStock < 0) {
            throw new NotEnoughStockException("No hay suficiente stock del producto " + getName() + "para continuar la venta");
        }
        quantity = newStock;
    }

    public void update(CreateProductRequest request) {
        setName(request.getName());
        setDescription(request.getDescription());
        setPrice(request.getPrice());
        setQuantity(request.getQuantity());
        setCategory(request.getCategory());
    }
}

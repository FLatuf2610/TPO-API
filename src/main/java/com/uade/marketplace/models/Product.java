package com.uade.marketplace.models;

import com.uade.marketplace.controller.dto.request.product.CreateProductRequest;
import com.uade.marketplace.exceptions.product.NotEnoughStockException;
import com.uade.marketplace.exceptions.product.InvalidQuantityException;
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
    private String imageUrl;
    private Category category;
    private Long userId;

    public void sell(int quantityToSell, Long buyerId) {
        if (quantityToSell <= 0) {
            throw new InvalidQuantityException("La cantidad a vender debe ser mayor a cero");
        }
        int newStock = quantity - quantityToSell;
        if (newStock < 0) {
            throw new NotEnoughStockException("No hay suficiente stock del producto " + getName() + "para continuar la venta");
        }
        if (buyerId.equals(userId)) {
            throw new RuntimeException("");
        }
        quantity = newStock;
    }

    public void update(CreateProductRequest request) {
        setName(request.getName());
        setDescription(request.getDescription());
        setPrice(request.getPrice());
        setQuantity(request.getQuantity());
        setImageUrl(request.getImageUrl());
        setCategory(request.getCategory());
    }
}

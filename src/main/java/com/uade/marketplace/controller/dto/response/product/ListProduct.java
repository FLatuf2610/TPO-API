package com.uade.marketplace.controller.dto.response.product;

import com.uade.marketplace.models.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListProduct {
    private Long id;
    private String name;
    private double price;
    private int quantity;
    private String imageUrl;
    private Category category;
}

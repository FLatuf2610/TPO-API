package com.uade.marketplace.controller.dto.response.product;

import com.uade.marketplace.controller.dto.response.user.UserNoProducts;
import com.uade.marketplace.models.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {
    private Long id;
    private String name;
    private Double price;
    private int quantity;
    private Category category;
    private UserNoProducts user;
}

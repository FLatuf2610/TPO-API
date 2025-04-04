package com.uade.marketplace.controller.dto.request.product;

import com.uade.marketplace.models.Category;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpdateProductRequest {
    @NotNull(message = "La id de producto no debe ser nula")
    private Long id;

    @NotBlank(message = "El producto debe tener nombre")
    private String name;

    @NotNull(message = "El precio del producto debe ser mayor a 0")
    @DecimalMin(value = "0.01", message = "El precio del producto debe ser mayor a 0")
    private Double price;

    @NotNull(message = "La cantidad del producto debe ser mayor a 0")
    @DecimalMin(value = "La cantidad del producto debe ser mayor a 0")
    private int quantity;

    @NotBlank(message = "La categoria no puede ser nula")
    private Category category;

    @NotNull(message = "La id de usuario no puede ser nula")
    private Long userId;
}

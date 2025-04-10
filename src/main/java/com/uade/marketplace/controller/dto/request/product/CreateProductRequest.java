package com.uade.marketplace.controller.dto.request.product;

import com.uade.marketplace.models.Category;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.NoArgsConstructor;
import lombok.Data;

@Data
@NoArgsConstructor
public class CreateProductRequest {
    @NotBlank(message = "El producto debe tener nombre")
    private String name;

    @NotBlank(message = "El producto debe tener una descripcion")
    private String description;

    @NotNull(message = "El precio del producto debe ser mayor a 0")
    @DecimalMin(value = "0.01", message = "El precio del producto debe ser mayor a 0")
    private Double price;

    @NotNull(message = "La cantidad del producto debe ser mayor a 0")
    @DecimalMin(value = "La cantidad del producto debe ser mayor a 0")
    private int quantity;

    @NotBlank(message = "El producto debe contener una imagen")
    private String imageUrl;

    @NotBlank(message = "La categoria no puede ser nula")
    private Category category;

    @NotNull(message = "La id de usuario no puede ser nula")
    private Long userId;
}

package com.uade.marketplace.controller.dto.request.category;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateCategoryRequest {
    @NotBlank(message = "La Category debe tener nombre")
    private String name;
}

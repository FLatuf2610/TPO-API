package com.uade.marketplace.controller.dto.request.order;

import com.uade.marketplace.models.OrderProduct;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderRequest {
    @NotEmpty(message = "La orden debe contener al menos un producto")
    private List<OrderProduct> products;
    @NotNull(message = "La orden debe tener la id del usuario")
    private Long userId;
}

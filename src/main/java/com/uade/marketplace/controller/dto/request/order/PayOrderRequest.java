package com.uade.marketplace.controller.dto.request.order;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PayOrderRequest {
    @NotBlank(message = "Debe contener un metodo de pago")
    private String paymentMethod;
    @NotBlank(message = "Debe contener una id de transaccion")
    private String transactionId;
    @NotBlank(message = "Debe contener una fecha")
    private String date;
}

package com.uade.marketplace.controller.dto.request.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PayOrderRequest {
    private String paymentMethod;
    private String transactionId;
    private String date;
}

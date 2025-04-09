package com.uade.marketplace.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentInfo {
    private String method;
    private String transactionId;
    private String paymentDate;
}

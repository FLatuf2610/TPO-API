package com.uade.marketplace.data.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "order_payment_info")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentInfoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String method;
    private String transactionId;
    private String paymentDate;

    @OneToOne(mappedBy = "paymentInfo")
    private OrderEntity order;

    public PaymentInfoEntity(String method, String transactionId, String paymentDate) {
        this.method = method;
        this.transactionId = transactionId;
        this.paymentDate = paymentDate;
    }
}

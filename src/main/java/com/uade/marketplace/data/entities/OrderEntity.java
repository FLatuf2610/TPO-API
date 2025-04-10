package com.uade.marketplace.data.entities;

import com.uade.marketplace.data.converter.OrderProductConverter;
import com.uade.marketplace.data.converter.PaymentInfoConverter;
import com.uade.marketplace.models.OrderProduct;
import com.uade.marketplace.models.OrderStatus;
import com.uade.marketplace.models.PaymentInfo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Convert(converter = OrderProductConverter.class)
    @Column(columnDefinition = "TEXT")
    private List<OrderProduct> products;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Convert(converter = PaymentInfoConverter.class)
    @Column(columnDefinition = "TEXT")
    private PaymentInfo paymentInfo;
}

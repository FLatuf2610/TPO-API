package com.uade.marketplace.models;

import com.uade.marketplace.controller.dto.request.order.PayOrderRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {
    private Long id;
    private List<OrderProduct> products;
    private Double total;
    private OrderStatus status;
    private PaymentInfo paymentInfo;
    private Long userId;

    public Order(Long id, List<OrderProduct> products, OrderStatus status, PaymentInfo paymentInfo, Long userId) {
        this.id = id;
        this.products = products;
        this.status = status;
        this.total = calculateTotal(products);
        this.paymentInfo = paymentInfo;
        this.userId = userId;
    }

    public Double calculateTotal(List<OrderProduct> products) {
        return products
                .stream()
                .map(OrderProduct::getPrice)
                .reduce(0.0, Double::sum);
    }

    public void payOrder(PayOrderRequest request) {
        if (status != OrderStatus.PENDING) {
            throw new RuntimeException("La orden ya no puede ser pagada");
        }
        paymentInfo = new PaymentInfo(
                request.getPaymentMethod(),
                request.getTransactionId(),
                request.getDate()
        );
        status = OrderStatus.COMPLETED;
    }

    public void cancelOrder() {
        status = OrderStatus.CANCELLED;
    }
}

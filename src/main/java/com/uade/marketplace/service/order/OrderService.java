package com.uade.marketplace.service.order;

import com.uade.marketplace.models.Order;

import java.util.List;

public interface OrderService {
    List<Order> getUserOrders(Long userId);
    Order createOrder(Order order);
    void payOrder(Long orderId);
}

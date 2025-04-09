package com.uade.marketplace.service.order;

import com.uade.marketplace.controller.dto.request.order.CreateOrderRequest;
import com.uade.marketplace.controller.dto.request.order.PayOrderRequest;
import com.uade.marketplace.models.Order;

import java.util.List;

public interface OrderService {
    List<Order> getUserOrders(Long userId);
    Order getOrderById(Long id);
    Order createOrder(CreateOrderRequest request);
    Order payOrder(Long orderId, PayOrderRequest request);
    void deleteOrder(Long orderId);
}

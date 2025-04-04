package com.uade.marketplace.service.order;

import com.uade.marketplace.models.Order;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class OrderServiceImpl implements OrderService {
    private final OrderService orderService;

    @Autowired
    public OrderServiceImpl(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public List<Order> getUserOrders(Long userId) {
        return List.of();
    }

    @Override
    public Order createOrder(Order order) {
        return null;
    }

    @Override
    public void payOrder(Long orderId) {

    }
}

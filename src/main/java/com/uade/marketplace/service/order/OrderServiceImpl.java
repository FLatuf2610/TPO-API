package com.uade.marketplace.service.order;

import com.uade.marketplace.data.repositories.OrderRepository;
import com.uade.marketplace.models.Order;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
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

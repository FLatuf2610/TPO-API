package com.uade.marketplace.service.order;

import com.uade.marketplace.controller.dto.request.order.CreateOrderRequest;
import com.uade.marketplace.controller.dto.request.order.PayOrderRequest;
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
    public Order getOrderById(Long id) {
        return null;
    }

    @Override
    public Order createOrder(CreateOrderRequest request) {
        return null;
    }

    @Override
    public Order payOrder(Long orderId, PayOrderRequest request) {
        return null;
    }

    @Override
    public void deleteOrder(Long orderId) {

    }
}

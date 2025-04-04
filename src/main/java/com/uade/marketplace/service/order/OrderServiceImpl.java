package com.uade.marketplace.service.order;

import com.uade.marketplace.data.entities.OrderEntity;
import com.uade.marketplace.data.entities.ProductEntity;
import com.uade.marketplace.data.entities.UserEntity;
import com.uade.marketplace.data.repositories.OrderRepository;
import com.uade.marketplace.data.repositories.ProductRepository;
import com.uade.marketplace.data.repositories.UserRepository;
import com.uade.marketplace.exceptions.product.ProductNotFoundException;
import com.uade.marketplace.exceptions.user.UserNotFoundException;
import com.uade.marketplace.mappers.OrderMapper;
import com.uade.marketplace.mappers.ProductMapper;
import com.uade.marketplace.models.Order;
import com.uade.marketplace.models.Product;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, ProductRepository productRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Order> getUserOrders(Long userId) {
        UserEntity userEntity = this.userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("El usuario no existe"));

        return userEntity.getOrders().stream()
                .map(OrderMapper::toDomain)
                .toList();
    }

    @Override
    public Order createOrder(Order order) {
        order.getProducts().forEach((p) -> {
            if (!this.productRepository.existsById(p.getId())) {
                throw new ProductNotFoundException("El producto " + p.getName() + "no existe");
            }
        });

        UserEntity userEntity = this.userRepository.findById(order.getUserId())
                .orElseThrow(() -> new UserNotFoundException("El usuario no existe"));

        OrderEntity orderEntity = this.orderRepository.save(OrderMapper.toEntity(order, userEntity));
        return OrderMapper.toDomain(orderEntity);
    }

    @Override
    public void payOrder(Long orderId) {

    }
}

package com.uade.marketplace.service.order;

import com.uade.marketplace.controller.dto.request.order.CreateOrderRequest;
import com.uade.marketplace.controller.dto.request.order.PayOrderRequest;
import com.uade.marketplace.data.entities.OrderEntity;
import com.uade.marketplace.data.entities.ProductEntity;
import com.uade.marketplace.data.entities.UserEntity;
import com.uade.marketplace.data.repositories.OrderRepository;
import com.uade.marketplace.data.repositories.ProductRepository;
import com.uade.marketplace.data.repositories.UserRepository;
import com.uade.marketplace.exceptions.order.OrderNotFoundException;
import com.uade.marketplace.exceptions.product.ProductNotFoundException;
import com.uade.marketplace.exceptions.user.UserNotFoundException;
import com.uade.marketplace.mappers.OrderMapper;
import com.uade.marketplace.mappers.ProductMapper;
import com.uade.marketplace.mappers.UserMapper;
import com.uade.marketplace.models.Order;
import com.uade.marketplace.models.OrderProduct;
import com.uade.marketplace.models.OrderStatus;
import com.uade.marketplace.models.Product;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, UserRepository userRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    @Override
    public List<Order> getUserOrders(Long userId) {
        return orderRepository.findOrdersByUserId(userId)
                .stream()
                .map(OrderMapper::toDomain)
                .toList();
    }

    @Override
    public Order getOrderById(Long id) {
        OrderEntity order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(""));
        return OrderMapper.toDomain(order);
    }

    @Override
    public Order createOrder(CreateOrderRequest request) {
        UserEntity userEntity = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new UserNotFoundException("El usuario no existe"));

        OrderEntity entity = OrderEntity.builder()
                .products(request.getProducts())
                .status(OrderStatus.PENDING)
                .user(userEntity)
                .build();

        return OrderMapper.toDomain(orderRepository.save(entity));
    }

    @Override
    public Order payOrder(Long orderId, PayOrderRequest request) {
        OrderEntity orderEntity = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("La orden no existe"));

        Order order = OrderMapper.toDomain(orderEntity);
        order.payOrder(request);

        List<Product> productsToUpdate = new ArrayList<>();

        for (OrderProduct op : order.getProducts()) {
            Product product = ProductMapper.toDomain(
                    productRepository.findById(op.getProductId())
                            .orElseThrow(() -> new ProductNotFoundException("El producto " + op.getProductName() + " no existe"))
            );
            product.sell(op.getQuantity(), order.getUserId());
            productsToUpdate.add(product);
        }

        for (Product p : productsToUpdate) {
            ProductEntity productEntity = productRepository.findById(p.getId())
                    .orElseThrow(() -> new ProductNotFoundException("El usuario no existe"));
            productEntity.setQuantity(p.getQuantity());
            productRepository.save(productEntity);
        }

        orderEntity.getPaymentInfo().setMethod(order.getPaymentInfo().getMethod());
        orderEntity.getPaymentInfo().setTransactionId(order.getPaymentInfo().getTransactionId());
        orderEntity.getPaymentInfo().setPaymentDate(order.getPaymentInfo().getPaymentDate());
        orderEntity.setStatus(order.getStatus());

        return OrderMapper.toDomain(orderRepository.save(orderEntity));
    }

    @Override
    public void deleteOrder(Long orderId) {
        orderRepository.deleteById(orderId);
    }
}

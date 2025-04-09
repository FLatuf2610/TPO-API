package com.uade.marketplace.mappers;

import com.uade.marketplace.data.entities.OrderEntity;
import com.uade.marketplace.data.entities.OrderProductEntity;
import com.uade.marketplace.data.entities.UserEntity;
import com.uade.marketplace.models.Order;
import com.uade.marketplace.models.OrderProduct;
import com.uade.marketplace.models.User;

import java.util.List;

public class OrderMapper {
    public static Order toDomain(OrderEntity entity) {
        List<OrderProduct> products = entity.getProducts()
                .stream()
                .map(OrderMapper::toDomain)
                .toList();

        return new Order(entity.getId(), products, entity.getStatus(), entity.getUser().getId());
    }

    public static OrderEntity toEntity(Order order, User user) {
        List<OrderProductEntity> productEntities = order.getProducts()
                .stream()
                .map(OrderMapper::toEntity)
                .toList();
        UserEntity userEntity = UserMapper.toEntity(user);
        return new OrderEntity(order.getId(), order.getStatus(), productEntities, userEntity);
    }

    public static OrderProduct toDomain(OrderProductEntity entity) {
        return new OrderProduct(entity.getProductId(), entity.getProductName(), entity.getQuantity(), entity.getPrice());
    }

    public static OrderProductEntity toEntity(OrderProduct orderProduct) {
        return OrderProductEntity.builder()
                .productId(orderProduct.getProductId())
                .productName(orderProduct.getProductName())
                .price(orderProduct.getPrice())
                .quantity(orderProduct.getQuantity())
                .build();

    }
}

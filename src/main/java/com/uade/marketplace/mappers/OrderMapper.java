package com.uade.marketplace.mappers;

import com.uade.marketplace.data.entities.OrderEntity;
import com.uade.marketplace.data.entities.ProductEntity;
import com.uade.marketplace.data.entities.UserEntity;
import com.uade.marketplace.models.Order;
import com.uade.marketplace.models.Product;

import java.util.List;

public class OrderMapper {
    public static Order toDomain(OrderEntity entity) {
        List<Product> products = entity.getProducts()
                .stream()
                .map(ProductMapper::toDomain)
                .toList();
        return new Order(entity.getId(), products, entity.getUser().getId());
    }

    public static OrderEntity toEntity(Order order, UserEntity user) {
        List<ProductEntity> productEntities = order.getProducts()
                .stream()
                .map(ProductMapper::toEntity)
                .toList();
        return new OrderEntity(order.getId(), productEntities, user);
    }
}

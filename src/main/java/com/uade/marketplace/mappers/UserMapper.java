package com.uade.marketplace.mappers;

import com.uade.marketplace.data.entities.OrderEntity;
import com.uade.marketplace.data.entities.ProductEntity;
import com.uade.marketplace.data.entities.UserEntity;
import com.uade.marketplace.models.Order;
import com.uade.marketplace.models.Product;
import com.uade.marketplace.models.User;

import java.util.List;

public class UserMapper {
    public static User toDomain(UserEntity entity, List<Product> products, List<Order> orders) {
        return new User(
                entity.getId(),
                entity.getName(),
                entity.getLastName(),
                entity.getEmail(),
                entity.getPassword(),
                products,
                orders
        );
    }

    public static UserEntity toEntity(User domain, List<ProductEntity> productEntities, List<OrderEntity> orderEntities) {
        UserEntity entity = new UserEntity();
        entity.setId(domain.getId());
        entity.setName(domain.getName());
        entity.setLastName(domain.getLastName());
        entity.setEmail(domain.getEmail());
        entity.setPassword(domain.getPassword());
        entity.setProducts(productEntities);
        entity.setOrders(orderEntities);
        return entity;
    }
}

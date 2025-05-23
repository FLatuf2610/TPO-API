package com.uade.marketplace.mappers;

import com.uade.marketplace.controller.dto.response.user.UserNoProducts;
import com.uade.marketplace.data.entities.ProductEntity;
import com.uade.marketplace.data.entities.UserEntity;
import com.uade.marketplace.models.Order;
import com.uade.marketplace.models.Product;
import com.uade.marketplace.models.User;

import java.util.List;

public class UserMapper {
    public static UserNoProducts toNoProducts(User user) {
        UserNoProducts userNoProducts = new UserNoProducts();
        userNoProducts.setId(user.getId());
        userNoProducts.setName(user.getName());
        userNoProducts.setLastName(user.getLastName());
        return userNoProducts;
    }

    public static User toDomain(UserEntity userEntity) {
        List<Product> products = userEntity.getProducts()
                .stream()
                .map(ProductMapper::toDomain)
                .toList();
        List<Order> orders = userEntity.getOrders()
                .stream()
                .map(OrderMapper::toDomain)
                .toList();
        return new User(userEntity.getId(), userEntity.getName(), userEntity.getLastName(), userEntity.getEmail(), userEntity.getPassword(), products, orders);
    }
}

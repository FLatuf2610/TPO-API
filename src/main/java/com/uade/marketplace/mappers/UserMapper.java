package com.uade.marketplace.mappers;

import com.uade.marketplace.controller.dto.request.user.UserRequest;
import com.uade.marketplace.controller.dto.response.user.UserNoProducts;
import com.uade.marketplace.data.entities.OrderEntity;
import com.uade.marketplace.data.entities.ProductEntity;
import com.uade.marketplace.data.entities.UserEntity;
import com.uade.marketplace.models.Order;
import com.uade.marketplace.models.Product;
import com.uade.marketplace.models.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
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
        return new User(userEntity.getId(), userEntity.getName(), userEntity.getLastName(), userEntity.getEmail(), userEntity.getPassword(), products, orders, userEntity.getRole());
    }

    public static UserEntity toEntity(User user) {
        List<ProductEntity> products = user
                .getProducts().stream().map(ProductMapper::toEntity).toList();
        List<OrderEntity> orders = user.getOrders().stream()
                .map(order -> OrderMapper.toEntity(order, user))
                .toList();
        return new UserEntity(user.getId(), user.getName(), user.getLastName(), user.getEmail(), user.getPassword(), user.getRole(), products, orders);
    }

    public static UserEntity requestToEntity(UserRequest userRequest) {
        return new UserEntity(
                null,
                userRequest.getName(),
                userRequest.getLastName(),
                userRequest.getEmail(),
                userRequest.getPassword(),
                userRequest.getRole(),
                List.of(),
                List.of()
        );
    }


    public static User requestToDomain(UserRequest userRequest) {
        return new User(
                null,
                userRequest.getName(),
                userRequest.getLastName(),
                userRequest.getEmail(),
                userRequest.getPassword(),
                List.of(),
                List.of(),
                userRequest.getRole()
        );
    }
}

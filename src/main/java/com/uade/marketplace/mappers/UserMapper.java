package com.uade.marketplace.mappers;

import com.uade.marketplace.controller.dto.request.user.UserRequest;
import com.uade.marketplace.controller.dto.response.user.UserNoProducts;
import com.uade.marketplace.data.entities.CartEntity;
import com.uade.marketplace.data.entities.ProductEntity;
import com.uade.marketplace.data.entities.UserEntity;
import com.uade.marketplace.models.Cart;
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

    public static UserNoProducts toNoProducts(UserEntity user) {
        UserNoProducts userNoProducts = new UserNoProducts();
        userNoProducts.setId(user.getId());
        userNoProducts.setEmail(user.getEmail());
        userNoProducts.setName(user.getName());
        userNoProducts.setLastName(user.getLastName());
        return userNoProducts;
    }

    public static User toDomain(UserEntity userEntity) {
        List<Product> products = userEntity.getProducts()
                .stream()
                .map(ProductMapper::toDomain)
                .toList();
        return new User(userEntity.getId(), userEntity.getName(), userEntity.getLastName(), userEntity.getEmail(), userEntity.getPassword(), products, CartMapper.toDomain(userEntity.getCart()), userEntity.getRole());
    }
}

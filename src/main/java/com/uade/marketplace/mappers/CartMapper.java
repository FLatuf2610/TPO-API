package com.uade.marketplace.mappers;

import com.uade.marketplace.data.entities.CartEntity;
import com.uade.marketplace.data.entities.CartProductEntity;
import com.uade.marketplace.data.entities.UserEntity;
import com.uade.marketplace.models.Cart;
import com.uade.marketplace.models.CartProduct;
import com.uade.marketplace.models.User;

import java.util.List;

public class CartMapper {
    public static Cart toDomain(CartEntity entity) {
        List<CartProduct> products = entity.getProducts()
                .stream()
                .map(CartMapper::toDomain)
                .toList();

        return new Cart(entity.getId(), products, entity.getUser().getId());
    }

    public static CartEntity toEntity(Cart cart, User user) {
        List<CartProductEntity> productEntities = cart.getProducts()
                .stream()
                .map(CartMapper::toEntity)
                .toList();
        UserEntity userEntity = UserMapper.toEntity(user);
        return new CartEntity(cart.getId(), productEntities, userEntity);
    }

    public static CartProduct toDomain(CartProductEntity entity) {
        return new CartProduct(ProductMapper.toDomain(entity.getProduct()), entity.getProduct().getQuantity());
    }

    public static CartProductEntity toEntity(CartProduct cartProduct) {
        return CartProductEntity.builder()
                .product(ProductMapper.toEntity(cartProduct.getProduct()))
                .quantity(cartProduct.getQuantity())
                .build();

    }

}

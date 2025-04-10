package com.uade.marketplace.mappers;

import com.uade.marketplace.data.entities.CartEntity;
import com.uade.marketplace.data.entities.CartProductEntity;
import com.uade.marketplace.data.entities.ProductEntity;
import com.uade.marketplace.data.entities.UserEntity;
import com.uade.marketplace.models.Cart;
import com.uade.marketplace.models.CartProduct;

import java.util.List;

public class CartMapper {
    public static Cart toDomain(CartEntity entity) {
        List<CartProduct> products = entity.getProducts()
                .stream()
                .map(CartMapper::toDomain)
                .toList();

        return new Cart(entity.getId(), products, entity.getUser().getId());
    }

    public static CartEntity toEntity(Cart cart, UserEntity userEntity, List<CartProductEntity> productEntities) {
        return new CartEntity(cart.getId(), productEntities, userEntity);
    }

    public static CartProduct toDomain(CartProductEntity entity) {
        return new CartProduct(ProductMapper.toDomain(entity.getProduct()), entity.getProduct().getQuantity());
    }

    public static CartProductEntity toEntity(CartProduct cartProduct, ProductEntity productEntity) {
        return CartProductEntity.builder()
                .product(productEntity)
                .quantity(cartProduct.getQuantity())
                .build();

    }
}

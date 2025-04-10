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

    public static CartProduct toDomain(CartProductEntity entity) {
        return new CartProduct(ProductMapper.toDomain(entity.getProduct()), entity.getProduct().getQuantity());
    }


}

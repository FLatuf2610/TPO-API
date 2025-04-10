package com.uade.marketplace.mappers;

import com.uade.marketplace.controller.dto.response.product.ListProduct;
import com.uade.marketplace.controller.dto.response.product.ProductDetail;
import com.uade.marketplace.data.entities.CartEntity;
import com.uade.marketplace.data.entities.ProductEntity;
import com.uade.marketplace.models.CartProduct;
import com.uade.marketplace.models.Product;
import com.uade.marketplace.models.User;

import java.util.List;

public class ProductMapper {
    public static Product toDomain(ProductEntity entity) {
        return Product.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .price(entity.getPrice())
                .quantity(entity.getQuantity())
                .imageUrl(entity.getImageUrl())
                .category(CategoryMapper.toDomain(entity.getCategory()))
                .userId(entity.getUser().getId())
                .build();
    }

    public static ListProduct toListProd(Product product) {
        return new ListProduct(product.getId(), product.getName(), product.getPrice(), product.getQuantity(), product.getImageUrl(), product.getCategory());
    }

    public static ProductDetail toDetail(Product product, User user) {
        return new ProductDetail(product.getId(), product.getName(), product.getDescription(), product.getPrice(), product.getQuantity(), product.getImageUrl(), product.getCategory(), UserMapper.toNoProducts(user));
    }

    public static List<CartProduct> toCartProd(CartEntity cart) {
        return cart.getProducts().stream().map(item -> CartProduct.builder()
                        .product(ProductMapper.toDomain(item.getProduct()))
                        .quantity(item.getQuantity())
                        .build())
                .toList();
    }
}

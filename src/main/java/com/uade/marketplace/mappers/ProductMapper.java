package com.uade.marketplace.mappers;

import com.uade.marketplace.controller.dto.request.product.CreateProductRequest;
import com.uade.marketplace.controller.dto.response.product.ListProduct;
import com.uade.marketplace.controller.dto.response.product.ProductDetail;
import com.uade.marketplace.data.entities.ProductEntity;
import com.uade.marketplace.models.Product;
import com.uade.marketplace.models.User;

public class ProductMapper {
    public static Product toDomain(ProductEntity entity) {
        return Product.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .price(entity.getPrice())
                .quantity(entity.getQuantity())
                .category(CategoryMapper.toDomain(entity.getCategory()))
                .userId(entity.getUser().getId())
                .build();
    }

    public static ProductEntity toEntity(Product domain) {
        return ProductEntity.builder()
                .id(domain.getId())
                .name(domain.getName())
                .description(domain.getDescription())
                .price(domain.getPrice())
                .quantity(domain.getQuantity())
                .category(CategoryMapper.toEntity(domain.getCategory()))
                .build();
    }

    public static ProductEntity toEntity(CreateProductRequest request) {
        return ProductEntity.builder()
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .quantity(request.getQuantity())
                .category(CategoryMapper.toEntity(request.getCategory()))
                .build();
    }

    public static ListProduct toListProd(Product product) {
        return new ListProduct(product.getId(), product.getName(), product.getPrice(), product.getQuantity(), product.getCategory());
    }

    public static ProductDetail toDetail(Product product, User user) {
        return new ProductDetail(product.getId(), product.getName(), product.getDescription(), product.getPrice(), product.getQuantity(), product.getCategory(), UserMapper.toNoProducts(user));
    }
}

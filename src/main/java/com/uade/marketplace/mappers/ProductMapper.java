package com.uade.marketplace.mappers;

import com.uade.marketplace.data.entities.CategoryEntity;
import com.uade.marketplace.data.entities.ProductEntity;
import com.uade.marketplace.models.Product;

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
}

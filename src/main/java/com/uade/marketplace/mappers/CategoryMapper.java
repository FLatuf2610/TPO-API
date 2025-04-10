package com.uade.marketplace.mappers;

import com.uade.marketplace.data.entities.CategoryEntity;
import com.uade.marketplace.models.Category;

public class CategoryMapper {
    public static Category toDomain(CategoryEntity entity) {
        return new Category(entity.getId(), entity.getName());
    }

    public static CategoryEntity toEntity(Category domain) {
        return new CategoryEntity(domain.getId(), domain.getName(), null);
    }
}

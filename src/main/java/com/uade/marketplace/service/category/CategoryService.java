package com.uade.marketplace.service.category;

import com.uade.marketplace.models.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategories();
    Category getCategoryById(Long categoryId);
    Category createCategory(String categoryName);
    Category updateCategory(Category category);
    void deleteCategory(Long categoryId);
    boolean existsById(Long categoryId);
}

package com.uade.marketplace.controller.web_services.category;

import java.util.List;

import com.uade.marketplace.controller.dto.request.category.CreateCategoryRequest;
import com.uade.marketplace.controller.dto.response.category.DeleteCategoryResponse;
import com.uade.marketplace.models.Category;

public interface CategoryWebService {
    List<Category> getAllCategories();

    Category getCategoryById(Long id);

    Category createCategory(CreateCategoryRequest request);

    Category updateCategory(Long id, CreateCategoryRequest request);

    DeleteCategoryResponse deleteCategory(Long id);
}

package com.uade.marketplace.controller.web_services.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uade.marketplace.controller.dto.request.category.CreateCategoryRequest;
import com.uade.marketplace.controller.dto.response.category.DeleteCategoryResponse;
import com.uade.marketplace.models.Category;
import com.uade.marketplace.service.category.CategoryService;

@Service
public class CategoryWebServiceImpl implements CategoryWebService {
    @Autowired
    private CategoryService categoryService;

    @Override
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @Override
    public Category getCategoryById(Long id) {
        return categoryService.getCategoryById(id);
    }

    @Override
    public Category createCategory(CreateCategoryRequest request) {
        return categoryService.createCategory(request.getName());
    }

    @Override
    public Category updateCategory(Long id, CreateCategoryRequest request) {
        return categoryService.updateCategory(id, request);
    }

    @Override
    public DeleteCategoryResponse deleteCategory(Long id) {
        categoryService.deleteCategory(id);
        return new DeleteCategoryResponse("Category eliminado correctamente");
    }
}

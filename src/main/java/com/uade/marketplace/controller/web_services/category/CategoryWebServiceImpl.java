package com.uade.marketplace.controller.web_services.category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uade.marketplace.controller.dto.request.category.CreateCategoryRequest;
import com.uade.marketplace.controller.dto.response.category.DeleteCategoryResponse;
import com.uade.marketplace.models.Category;
import com.uade.marketplace.service.category.CategoryService;

@Service
public class CategoryWebServiceImpl implements CategoryWebService{
    private final CategoryService categoryService;

    @Autowired
    public CategoryWebServiceImpl(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    public Category getCategoryById(Long id) {
        return categoryService.getCategoryById(id);
    }

    public Category createCategory(CreateCategoryRequest request) {
        return categoryService.createCategory(request.getName());
    }

    public Category updateCategory(Long id, CreateCategoryRequest request) {
        return categoryService.updateCategory(id, request);
    }

    public DeleteCategoryResponse deleteCategory(Long id) {
        categoryService.deleteCategory(id);
        return new DeleteCategoryResponse("Category eliminado correctamente");
    }
}

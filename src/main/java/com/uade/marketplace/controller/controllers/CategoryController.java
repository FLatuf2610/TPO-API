package com.uade.marketplace.controller.controllers;

import com.uade.marketplace.controller.dto.request.category.CreateCategoryRequest;
import com.uade.marketplace.controller.dto.response.category.DeleteCategoryResponse;
import com.uade.marketplace.controller.dto.response.category.GetAllCategoryResponse;
import com.uade.marketplace.controller.web_services.category.CategoryWebService;
import com.uade.marketplace.models.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("category")
public class CategoryController {
    @Autowired
    private CategoryWebService categoryWebService;
    
    // GET --> {url}/category
    @GetMapping
    ResponseEntity<GetAllCategoryResponse> getAllCategories() {
        List<Category> categories = categoryWebService.getAllCategories();
        return ResponseEntity.ok(new GetAllCategoryResponse(categories));
    }

    // GET --> {url}/category/id
    @GetMapping("/{id}")
    ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
        Category newCategory = categoryWebService.getCategoryById(id);
        return ResponseEntity.ok(newCategory);
    }

    // POST --> {url}/category
    @PostMapping
    ResponseEntity<Category> createCategory(@RequestBody CreateCategoryRequest category) {
        Category newCategory = categoryWebService.createCategory(category);
        return ResponseEntity.ok(newCategory);
    }

    // PUT --> {url}/category/id
    @PutMapping("/{id}")
    ResponseEntity<Category> editCategory(@PathVariable Long id, @RequestBody CreateCategoryRequest request) {
        Category category = categoryWebService.updateCategory(id, request);
        return ResponseEntity.ok(category);
    }

    // DELETE --> {url}/category/id
    @DeleteMapping("/{id}")
    ResponseEntity<DeleteCategoryResponse> deleteCategory(@PathVariable Long id) {
        return ResponseEntity.ok(categoryWebService.deleteCategory(id));
    }
}

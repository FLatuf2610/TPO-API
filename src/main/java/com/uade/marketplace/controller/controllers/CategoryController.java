package com.uade.marketplace.controller.controllers;

import com.uade.marketplace.models.Category;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("category")
public class CategoryController {

    @GetMapping
    List<Category> getAllCategories() {
        List<Category> categories = null;
        return categories;
    }

    @GetMapping("/{id}")
    Category getCategoryById(@PathVariable Long id) {
        Category category = null;
        return category;
    }

    @PostMapping
    Category createCategory(@RequestBody Category category) {
        Category newCategory;
        newCategory = category;
        return newCategory;
    }
}

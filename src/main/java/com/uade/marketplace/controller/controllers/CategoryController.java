package com.uade.marketplace.controller.controllers;

import com.uade.marketplace.models.Category;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("category")
public class CategoryController {

    // GET --> {url}/category
    @GetMapping
    List<Category> getAllCategories() {
        List<Category> categories = null;
        return categories;
    }
    // GET --> {url}/category/id
    @GetMapping("/{id}")
    Category getCategoryById(@PathVariable Long id) {
        Category category = null;
        return category;
    }
    // POST --> {url}/category
    @PostMapping
    Category createCategory(@RequestBody Category category) {
        Category newCategory;
        newCategory = category;
        return newCategory;
    }
}

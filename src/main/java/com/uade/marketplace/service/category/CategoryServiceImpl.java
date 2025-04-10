package com.uade.marketplace.service.category;

import com.uade.marketplace.controller.dto.request.category.CreateCategoryRequest;
import com.uade.marketplace.data.entities.CategoryEntity;
import com.uade.marketplace.data.repositories.CategoryRepository;
import com.uade.marketplace.exceptions.category.CategoryAlreadyExistsException;
import com.uade.marketplace.exceptions.category.CategoryNotFoundException;
import com.uade.marketplace.models.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategories() {
        List<CategoryEntity> categoryEntities = this.categoryRepository.findAll();
        List<Category> categories = new ArrayList<>();

        categoryEntities.forEach((c) -> categories.add(new Category(c.getId(), c.getName())));

        return categories;
    }

    @Override
    public Category getCategoryById(Long categoryId) {
        CategoryEntity categoryEntity = this.categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException("La categoria que buscas no existe"));
        return new Category(categoryEntity.getId(), categoryEntity.getName());
    }

    @Override
    public Category createCategory(String categoryName) {
        List<CategoryEntity> entities = this.categoryRepository.findByName(categoryName);
        if (!entities.isEmpty()) {
            throw new CategoryAlreadyExistsException("La categoria ya existe");
        }

        CategoryEntity entity = new CategoryEntity();
        entity.setName(categoryName);

        CategoryEntity newEntity = this.categoryRepository.save(entity);
        return new Category(newEntity.getId(), newEntity.getName());
    }

    @Override
    public Category updateCategory(Long id, CreateCategoryRequest category) {
        CategoryEntity entity = this.categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("La categoria no existe"));
        entity.setName(category.getName());

        CategoryEntity updatedEntity = this.categoryRepository.save(entity);
        return new Category(updatedEntity.getId(), updatedEntity.getName());
    }

    @Override
    public void deleteCategory(Long categoryId) {
        CategoryEntity entity = this.categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException("La categoria no existe"));
        this.categoryRepository.delete(entity);
    }

    @Override
    public boolean existsById(Long categoryId) {
        return this.categoryRepository.existsById(categoryId);
    }
}

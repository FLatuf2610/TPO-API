package com.uade.marketplace.service.product;

import com.uade.marketplace.controller.dto.request.product.CreateProductRequest;
import com.uade.marketplace.controller.dto.request.product.DeleteProductRequest;
import com.uade.marketplace.data.entities.CategoryEntity;
import com.uade.marketplace.data.entities.ProductEntity;
import com.uade.marketplace.data.entities.UserEntity;
import com.uade.marketplace.data.repositories.CategoryRepository;
import com.uade.marketplace.data.repositories.ProductRepository;
import com.uade.marketplace.data.repositories.UserRepository;
import com.uade.marketplace.exceptions.category.CategoryNotFoundException;
import com.uade.marketplace.exceptions.product.ProductNotFoundException;
import com.uade.marketplace.exceptions.product.UserNotAllowedToModifyOtherUserProductException;
import com.uade.marketplace.exceptions.user.UserNotFoundException;
import com.uade.marketplace.mappers.CategoryMapper;
import com.uade.marketplace.mappers.ProductMapper;
import com.uade.marketplace.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, UserRepository userRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        return this.productRepository.findAll()
                .stream()
                .map(ProductMapper::toDomain)
                .toList();
    }

    @Override
    public Product getProductById(Long id) {
        ProductEntity productEntity = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("El producto que estas buscando no existe"));
        return ProductMapper.toDomain(productEntity);
    }

    @Override
    public List<Product> getProductsByCategoryId(Long id) {
        return productRepository.findByCategoryId(id)
                .stream()
                .map(ProductMapper::toDomain)
                .toList();
    }

    @Override
    public Product createProduct(CreateProductRequest request) {
        UserEntity userEntity = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new UserNotFoundException("El usuario no existe"));

        CategoryEntity categoryEntity = categoryRepository.findById(request.getCategory().getId())
                .orElseThrow(() -> new CategoryNotFoundException("La categoria no existe"));

        ProductEntity entity = new ProductEntity(
                null,
                request.getName(),
                request.getDescription(),
                request.getPrice(),
                request.getQuantity(),
                request.getImageUrl(),
                categoryEntity,
                userEntity
        );

        return ProductMapper.toDomain(productRepository.save(entity));
    }

    @Override
    public Product updateProduct(Long id, CreateProductRequest request) {
        ProductEntity productEntity = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("El producto no existe"));

        productEntity.setName(request.getName());
        productEntity.setDescription(request.getDescription());
        productEntity.setPrice(request.getPrice());
        productEntity.setQuantity(request.getQuantity());
        productEntity.setImageUrl(request.getImageUrl());
        productEntity.setCategory(CategoryMapper.toEntity(request.getCategory()));

        if (productEntity.getUser().getId() != request.getUserId()) {
            throw new UserNotAllowedToModifyOtherUserProductException("El usuario solo puede modificar sus propios productos");
        }

        return ProductMapper.toDomain(productRepository.save(productEntity));
    }

    @Override
    public void deleteProduct(DeleteProductRequest request) {
        ProductEntity productEntity = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new ProductNotFoundException("El producto a eliminar no existe"));
        if (request.getUserId() != productEntity.getUser().getId()) {
            throw new UserNotAllowedToModifyOtherUserProductException("El usuario solo puede modificar sus propios productos");
        }

        productRepository.delete(productEntity);
    }
}

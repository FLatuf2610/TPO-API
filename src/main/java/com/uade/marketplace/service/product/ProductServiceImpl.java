package com.uade.marketplace.service.product;

import com.uade.marketplace.controller.dto.request.product.CreateProductRequest;
import com.uade.marketplace.controller.dto.request.product.DeleteProductRequest;
import com.uade.marketplace.data.entities.CategoryEntity;
import com.uade.marketplace.data.entities.ProductEntity;
import com.uade.marketplace.data.entities.UserEntity;
import com.uade.marketplace.data.repositories.CategoryRepository;
import com.uade.marketplace.data.repositories.ProductImageRepository;
import com.uade.marketplace.data.repositories.ProductRepository;
import com.uade.marketplace.data.repositories.UserRepository;
import com.uade.marketplace.exceptions.DBAccessException;
import com.uade.marketplace.exceptions.category.CategoryNotFoundException;
import com.uade.marketplace.exceptions.product.ProductNotFoundException;
import com.uade.marketplace.exceptions.product.UserNotAllowedToModifyOtherUserProductException;
import com.uade.marketplace.exceptions.user.UserNotAllowedException;
import com.uade.marketplace.exceptions.user.UserNotFoundException;
import com.uade.marketplace.exceptions.DBAccessException;
import com.uade.marketplace.mappers.CategoryMapper;
import com.uade.marketplace.mappers.ProductMapper;
import com.uade.marketplace.models.Product;
import com.uade.marketplace.models.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.dao.DataAccessException;


import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final ProductImageRepository productImageRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, UserRepository userRepository, CategoryRepository categoryRepository, ProductImageRepository productImageRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.productImageRepository = productImageRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        try {
            return this.productRepository.findAll()
                    .stream()
                    .map(ProductMapper::toDomain)
                    .toList();            
        } catch (DataAccesException e) {
            throw new DBAccessException("No se pudo acceder a la DB", e);
        }
    }

    @Override
    public Product getProductById(Long id) {
        try {
            ProductEntity productEntity = productRepository.findById(id)
                    .orElseThrow(() -> new ProductNotFoundException("El producto que estas buscando no existe"));
            return ProductMapper.toDomain(productEntity);
        } catch (DataAccesException e) {
            throw new DBAccessException("No se pudo acceder a la DB", e);
        }
    }

    @Override
    public List<Product> getProductsByCategoryId(Long id) {
        try {
            return productRepository.findByCategoryId(id)
                    .stream()
                    .map(ProductMapper::toDomain)
                    .toList();            
        } catch (DataAccesException e) {
            throw new DBAccessException("No se pudo acceder a la DB", e);
        }

    }

    @Override
    public Product createProduct(CreateProductRequest request) {
        try {
            UserEntity userEntity = userRepository.findById(request.getUserId())
                    .orElseThrow(() -> new UserNotFoundException("El usuario no existe"));

            CategoryEntity categoryEntity = categoryRepository.findById(request.getCategory().getId())
                    .orElseThrow(() -> new CategoryNotFoundException("La categoria no existe"));

            if (userEntity.getRole() != Role.VENDEDOR) {
                throw new UserNotAllowedException("El usuario no tiene rol de vendedor");
            }

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
        } catch (DataAccesException e) {
            throw new DBAccessException("No se pudo acceder a la DB", e);
        }
    }

    @Override
    public Product updateProduct(Long id, CreateProductRequest request) {
        try {
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

            if (productEntity.getUser().getRole() != Role.VENDEDOR) {
                throw new UserNotAllowedException("El usuario no tiene rol de vendedor");
            }

            return ProductMapper.toDomain(productRepository.save(productEntity));
        } catch (DataAccesException e) {
            throw new DBAccessException("No se pudo acceder a la DB", e);
        }
    }

    @Override
    public void deleteProduct(DeleteProductRequest request) {
        try {
            ProductEntity productEntity = productRepository.findById(request.getProductId())
                    .orElseThrow(() -> new ProductNotFoundException("El producto a eliminar no existe"));

            if (request.getUserId() != productEntity.getUser().getId()) {
                throw new UserNotAllowedToModifyOtherUserProductException("El usuario solo puede eliminar sus propios productos");
            }

            if (productEntity.getUser().getRole() != Role.VENDEDOR) {
                throw new UserNotAllowedException("El usuario no tiene rol de vendedor");
            }

            String imageUrl = productEntity.getImageUrl();
            Long imageId = Long.parseLong(imageUrl.substring(imageUrl.lastIndexOf("/") + 1));
            productImageRepository.deleteById(imageId);

            productRepository.delete(productEntity);
        } catch (DataAccesException e) {
            throw new DBAccessException("No se pudo acceder a la DB", e);
        }
    }
}
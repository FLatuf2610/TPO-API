package com.uade.marketplace.service.product;

import com.uade.marketplace.controller.dto.request.product.CreateProductRequest;
import com.uade.marketplace.data.entities.CategoryEntity;
import com.uade.marketplace.data.entities.ProductEntity;
import com.uade.marketplace.data.entities.UserEntity;
import com.uade.marketplace.data.repositories.ProductRepository;
import com.uade.marketplace.data.repositories.UserRepository;
import com.uade.marketplace.exceptions.product.ProductNotFoundException;
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

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, UserRepository userRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
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

        ProductEntity entity = new ProductEntity(
                null,
                request.getName(),
                request.getDescription(),
                request.getPrice(),
                request.getQuantity(),
                request.getImageUrl(),
                CategoryMapper.toEntity(request.getCategory()),
                userEntity
        );

        return ProductMapper.toDomain(productRepository.save(entity));
    }

    @Override
    public Product updateProduct(Long id, CreateProductRequest request) {
        ProductEntity productEntity = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("El producto no existe"));

        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId(request.getCategory().getId());
        categoryEntity.setName(request.getCategory().getName());

        productEntity.setName(request.getName());
        productEntity.setDescription(request.getDescription());
        productEntity.setPrice(request.getPrice());
        productEntity.setQuantity(request.getQuantity());
        productEntity.setCategory(categoryEntity);

        return ProductMapper.toDomain(productRepository.save(productEntity));
    }

    @Override
    public void deleteProduct(Long id) {
        ProductEntity productEntity = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("El producto a eliminar no existe"));
        productRepository.delete(productEntity);
    }
}

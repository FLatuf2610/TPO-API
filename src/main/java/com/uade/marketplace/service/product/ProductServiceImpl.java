package com.uade.marketplace.service.product;

import com.uade.marketplace.controller.dto.request.product.CreateProductRequest;
import com.uade.marketplace.data.entities.ProductEntity;
import com.uade.marketplace.data.entities.UserEntity;
import com.uade.marketplace.data.repositories.ProductRepository;
import com.uade.marketplace.data.repositories.UserRepository;
import com.uade.marketplace.exceptions.product.ProductNotFoundException;
import com.uade.marketplace.exceptions.user.UserNotFoundException;
import com.uade.marketplace.mappers.CategoryMapper;
import com.uade.marketplace.exceptions.DBAccessException;
import com.uade.marketplace.mappers.ProductMapper;
import com.uade.marketplace.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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
        try {
            ProductEntity productEntity = productRepository.findById(id)
                    .orElseThrow(() -> new ProductNotFoundException("El producto que estas buscando no existe"));
            return ProductMapper.toDomain(productEntity);
        } catch (DataAccessException e) {
            throw new DBAccessException("Error al acceder a la base de datos al buscar el producto", e);
        }      
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
        try {
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
        } catch (DataAccessException e) {
            throw new DBAccessException("Error al intentar conectar con la base de datos");
        }
    }

    @Override
    public Product updateProduct(Long id, CreateProductRequest request) {
        try {
            ProductEntity existingProduct = productRepository.findById(id)
                    .orElseThrow(() -> new ProductNotFoundException("El producto no existe"));

            productEntity.setName(request.getName());
            productEntity.setDescription(request.getDescription());
            productEntity.setPrice(request.getPrice());
            productEntity.setQuantity(request.getQuantity());
            productEntity.setCategory(CategoryMapper.toEntity(request.getCategory()));

            return ProductMapper.toDomain(productRepository.save(productEntity));
        } catch (DataAccessException e) {
            throw new DBAccessException("Error al acceder a la base de datos al actualizar el producto", e);
        }
    }

    @Override
    public void deleteProduct(Long id) {
        try {
        ProductEntity productEntity = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("El producto a eliminar no existe"));
        productRepository.delete(productEntity);
        } catch (DataAccessException e) {
            throw new DBAccessException("Error al acceder a la base de datos", e);
        }
    }

    @Override
    public void sellProduct(Long productId, int quantity) {
        try {
            ProductEntity productEntity = productRepository.findById(productId)
                    .orElseThrow(() -> new ProductNotFoundException("El producto no existe"));

            Product domain = ProductMapper.toDomain(productEntity);
            domain.sell(quantity);

            ProductEntity updatedEntity = ProductMapper.toEntity(domain);
            productRepository.save(updatedEntity);
        } catch (DataAccessException e) {
            throw new DBAccessException("Error al acceder a la base de datos", e);
        }
    }
}

package com.uade.marketplace.service.product;

import com.uade.marketplace.controller.dto.request.product.CreateProductRequest;
import com.uade.marketplace.data.entities.ProductEntity;
import com.uade.marketplace.data.repositories.ProductRepository;
import com.uade.marketplace.exceptions.product.ProductNotFoundException;
import com.uade.marketplace.mappers.ProductMapper;
import com.uade.marketplace.models.Product;
import com.uade.marketplace.service.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
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
        ProductEntity productEntity = ProductMapper.toEntity(request);
        return ProductMapper.toDomain(productRepository.save(productEntity));
    }

    @Override
    public Product updateProduct(Long id, CreateProductRequest request) {
        ProductEntity existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("El producto no existe"));

        Product domain = ProductMapper.toDomain(existingProduct);
        domain.update(request);

        ProductEntity updatedEntity = ProductMapper.toEntity(domain);
        return ProductMapper.toDomain(productRepository.save(updatedEntity));
    }

    @Override
    public void deleteProduct(Long id) {
        ProductEntity productEntity = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("El producto a eliminar no existe"));
        productRepository.delete(productEntity);
    }

    @Override
    public void sellProduct(Long productId, int quantity) {
        ProductEntity productEntity = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("El producto no existe"));

        Product domain = ProductMapper.toDomain(productEntity);
        domain.sell(quantity);

        ProductEntity updatedEntity = ProductMapper.toEntity(domain);
        productRepository.save(updatedEntity);
    }
}

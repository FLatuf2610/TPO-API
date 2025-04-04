package com.uade.marketplace.service.product;

import com.uade.marketplace.controller.dto.request.product.CreateProductRequest;
import com.uade.marketplace.data.entities.ProductEntity;
import com.uade.marketplace.data.repositories.ProductRepository;
import com.uade.marketplace.exceptions.category.CategoryNotFoundException;
import com.uade.marketplace.exceptions.product.NotEnoughStockException;
import com.uade.marketplace.exceptions.product.ProductNotFoundException;
import com.uade.marketplace.mappers.CategoryMapper;
import com.uade.marketplace.mappers.ProductMapper;
import com.uade.marketplace.models.Product;
import com.uade.marketplace.service.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
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
        boolean categoryExists = categoryService.existsById(request.getCategory().getId());
        if (!categoryExists) {
            throw new CategoryNotFoundException("La categoria del producto no existe");
        }

        ProductEntity productEntity = ProductMapper.toEntity(request);
        return ProductMapper.toDomain(productRepository.save(productEntity));
    }

    @Override
    public Product updateProduct(Long id, CreateProductRequest request) {
        ProductEntity existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("El producto no existe"));

        boolean categoryExists = categoryService.existsById(request.getCategory().getId());
        if (!categoryExists) {
            throw new CategoryNotFoundException("La categoria del producto no existe");
        }

        existingProduct.setName(request.getName());
        existingProduct.setDescription(request.getDescription());
        existingProduct.setPrice(request.getPrice());
        existingProduct.setQuantity(request.getQuantity());
        existingProduct.setCategory(CategoryMapper.toEntity(request.getCategory()));

        return ProductMapper.toDomain(productRepository.save(existingProduct));
    }

    @Override
    public void deleteProduct(Long id) {
        ProductEntity productEntity = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("El producto a eliminar no existe"));
        productRepository.delete(productEntity);
    }

    @Override
    public void sellProduct(Product product, int quantity) {
        ProductEntity productEntity = productRepository.findById(product.getId())
                .orElseThrow(() -> new ProductNotFoundException("El producto no existe"));

        int newStock = productEntity.getQuantity() - quantity;
        if (newStock <= 0) {
            throw new NotEnoughStockException("No hay suficiente stock del producto " + productEntity.getName() + "para continuar la venta");
        }

        productEntity.setQuantity(newStock);
        productRepository.save(productEntity);
    }
}

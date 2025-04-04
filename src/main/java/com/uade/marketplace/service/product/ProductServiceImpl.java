package com.uade.marketplace.service.product;

import com.uade.marketplace.data.entities.CategoryEntity;
import com.uade.marketplace.data.entities.ProductEntity;
import com.uade.marketplace.data.repositories.CategoryRepository;
import com.uade.marketplace.data.repositories.ProductRepository;
import com.uade.marketplace.exceptions.category.CategoryNotFoundException;
import com.uade.marketplace.exceptions.product.NotEnoughStockException;
import com.uade.marketplace.exceptions.product.ProductNotFoundException;
import com.uade.marketplace.mappers.CategoryMapper;
import com.uade.marketplace.mappers.ProductMapper;
import com.uade.marketplace.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
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
        ProductEntity productEntity = this.productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("El producto que estas buscando no existe"));
        return ProductMapper.toDomain(productEntity);
    }

    @Override
    public List<Product> getProductsByCategoryId(Long id) {
        return this.productRepository.findByCategoryId(id)
                .stream()
                .map(ProductMapper::toDomain)
                .toList();
    }

    @Override
    public Product createProduct(Product product) {
        boolean categoryExists = this.categoryRepository.existsById(product.getCategory().getId());
        if (!categoryExists) {
            throw new CategoryNotFoundException("La categoria del producto no existe");
        }

        ProductEntity productEntity = ProductMapper.toEntity(product);
        return ProductMapper.toDomain(this.productRepository.save(productEntity));
    }

    @Override
    public Product updateProduct(Product product) {
        boolean productExists = this.productRepository.existsById(product.getId());
        if (!productExists) {
            throw new ProductNotFoundException("El producto no existe");
        }
        boolean categoryExists = this.categoryRepository.existsById(product.getCategory().getId());
        if (!categoryExists) {
            throw new CategoryNotFoundException("La categoria del producto no existe");
        }

        ProductEntity entity = ProductMapper.toEntity(product);
        return ProductMapper.toDomain(this.productRepository.save(entity));
    }

    @Override
    public void deleteProduct(Long id) {
        ProductEntity productEntity = this.productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("El producto a eliminar no existe"));
        this.productRepository.delete(productEntity);
    }

    @Override
    public void sellProduct(Product product, int quantity) {
        ProductEntity productEntity = this.productRepository.findById(product.getId())
                .orElseThrow(() -> new ProductNotFoundException("El producto no existe"));


        int newStock = productEntity.getQuantity() - quantity;
        if (newStock <= 0) {
            throw new NotEnoughStockException("No hay suficiente stock del producto " + productEntity.getName() + "para continuar la venta");
        }

        productEntity.setQuantity(newStock);
        this.productRepository.save(productEntity);
    }
}

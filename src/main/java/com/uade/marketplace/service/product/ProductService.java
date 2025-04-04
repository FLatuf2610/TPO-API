package com.uade.marketplace.service.product;

import com.uade.marketplace.models.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
    Product getProductById(Long id);
    List<Product> getProductsByCategoryId(Long id);
    Product createProduct(Product product);
    Product updateProduct(Product product);
    void deleteProduct(Long id);
    void sellProduct(Product product, int quantity);
}

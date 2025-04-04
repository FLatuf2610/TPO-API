package com.uade.marketplace.service.product;

import com.uade.marketplace.controller.dto.request.product.CreateProductRequest;
import com.uade.marketplace.models.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
    Product getProductById(Long id);
    List<Product> getProductsByCategoryId(Long id);
    Product createProduct(CreateProductRequest request);
    Product updateProduct(Long id, CreateProductRequest request);
    void deleteProduct(Long id);
    void sellProduct(Product product, int quantity);
}

package com.uade.marketplace.controller;

import com.uade.marketplace.controller.dto.request.product.CreateProductRequest;
import com.uade.marketplace.controller.dto.request.product.UpdateProductRequest;
import com.uade.marketplace.models.Product;
import com.uade.marketplace.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("products")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    Product getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @GetMapping("category/{id}")
    List<Product> getProductsByCategoryId(@PathVariable Long id) {
        return productService.getProductsByCategoryId(id);
    }

    @PostMapping()
    Product createProduct(@RequestBody CreateProductRequest request) {
        Product product = Product.builder()
                .name(request.getName())
                .price(request.getPrice())
                .quantity(request.getQuantity())
                .category(request.getCategory())
                .userId(request.getUserId())
                .build();
        return productService.createProduct(product);
    }

    @PutMapping()
    Product editProduct(@RequestBody UpdateProductRequest request)  {
        Product product = Product.builder()
                .id(request.getId())
                .name(request.getName())
                .price(request.getPrice())
                .quantity(request.getQuantity())
                .category(request.getCategory())
                .userId(request.getUserId())
                .build();
        return productService.updateProduct(product);
    }

    @DeleteMapping("/{id}")
    void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }
}

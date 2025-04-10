package com.uade.marketplace.controller.controllers;

import com.uade.marketplace.controller.dto.request.product.CreateProductRequest;
import com.uade.marketplace.controller.dto.request.product.DeleteProductRequest;
import com.uade.marketplace.controller.dto.response.product.DeleteProductResponse;
import com.uade.marketplace.controller.dto.response.product.GetAllProductsResponse;
import com.uade.marketplace.controller.dto.response.product.ListProduct;
import com.uade.marketplace.controller.dto.response.product.ProductDetail;
import com.uade.marketplace.controller.web_services.product.ProductWebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("products")
public class ProductController {
    private final ProductWebService productWebService;
    @Autowired
    public ProductController(ProductWebService productWebService) {
        this.productWebService = productWebService;
    }

    @GetMapping
    ResponseEntity<GetAllProductsResponse> getAllProducts() {
        List<ListProduct> products = productWebService.getAllProducts();
        return ResponseEntity.ok(new GetAllProductsResponse(products));
    }

    @GetMapping("/{id}")
    ResponseEntity<ProductDetail> getProductById(@PathVariable Long id) {
        ProductDetail productDetail = productWebService.getProductById(id);
        return ResponseEntity.ok(productDetail);
    }

    @GetMapping("category/{id}")
    ResponseEntity<GetAllProductsResponse> getProductsByCategoryId(@PathVariable Long id) {
        GetAllProductsResponse getAllProductsResponse = new GetAllProductsResponse(productWebService.getProductsByCategoryId(id));
        return ResponseEntity.ok(getAllProductsResponse);
    }

    @PostMapping()
    ResponseEntity<ProductDetail> createProduct(@RequestBody CreateProductRequest request) {
        ProductDetail product = productWebService.createProduct(request);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    ResponseEntity<ProductDetail> editProduct(@PathVariable("id") Long id, @RequestBody CreateProductRequest request) {
        ProductDetail product = productWebService.updateProduct(id, request);
        return ResponseEntity.ok(product);
    }

    @DeleteMapping()
    ResponseEntity<DeleteProductResponse> deleteProduct(@RequestBody DeleteProductRequest request) {
        return ResponseEntity.ok(productWebService.deleteProduct(request));
    }
}

package com.uade.marketplace.controller.web_services.product;

import com.uade.marketplace.controller.dto.request.product.CreateProductRequest;
import com.uade.marketplace.controller.dto.request.product.DeleteProductRequest;
import com.uade.marketplace.controller.dto.response.product.DeleteProductResponse;
import com.uade.marketplace.controller.dto.response.product.ListProduct;
import com.uade.marketplace.controller.dto.response.product.ProductDetail;

import java.util.List;

public interface ProductWebService {
    List<ListProduct> getAllProducts();
    ProductDetail getProductById(Long id);
    List<ListProduct> getProductsByCategoryId(Long id);
    ProductDetail createProduct(CreateProductRequest request);
    ProductDetail updateProduct(Long id, CreateProductRequest request);
    DeleteProductResponse deleteProduct(DeleteProductRequest request);
}

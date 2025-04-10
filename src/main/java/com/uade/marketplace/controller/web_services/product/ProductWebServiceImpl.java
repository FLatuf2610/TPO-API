package com.uade.marketplace.controller.web_services.product;

import com.uade.marketplace.controller.dto.request.product.CreateProductRequest;
import com.uade.marketplace.controller.dto.request.product.DeleteProductRequest;
import com.uade.marketplace.controller.dto.response.product.DeleteProductResponse;
import com.uade.marketplace.controller.dto.response.product.ListProduct;
import com.uade.marketplace.controller.dto.response.product.ProductDetail;
import com.uade.marketplace.mappers.ProductMapper;
import com.uade.marketplace.models.Product;
import com.uade.marketplace.models.User;
import com.uade.marketplace.service.product.ProductService;
import com.uade.marketplace.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductWebServiceImpl implements ProductWebService {
    private final ProductService productService;
    private final UserService userService;

    @Autowired
    public ProductWebServiceImpl(ProductService productService, UserService userService) {
        this.productService = productService;
        this.userService = userService;
    }

    @Override
    public List<ListProduct> getAllProducts() {
        return productService.getAllProducts()
                .stream()
                .map(ProductMapper::toListProd)
                .toList();
    }

    @Override
    public ProductDetail getProductById(Long id) {
        Product product = productService.getProductById(id);
        User user = userService.getUserById(product.getUserId());
        return ProductMapper.toDetail(product, user);
    }

    @Override
    public List<ListProduct> getProductsByCategoryId(Long id) {
        return productService.getProductsByCategoryId(id)
                .stream()
                .map(ProductMapper::toListProd)
                .toList();
    }

    @Override
    public ProductDetail createProduct(CreateProductRequest request) {
        Product product = productService.createProduct(request);
        User user = userService.getUserById(product.getUserId());
        return ProductMapper.toDetail(product, user);
    }

    @Override
    public ProductDetail updateProduct(Long id, CreateProductRequest request) {
        Product product = productService.updateProduct(id, request);
        User user = userService.getUserById(product.getUserId());
        return ProductMapper.toDetail(product, user);
    }

    @Override
    public DeleteProductResponse deleteProduct(DeleteProductRequest request) {
        productService.deleteProduct(request);
        return new DeleteProductResponse("Producto eliminado correctamente");
    }
}

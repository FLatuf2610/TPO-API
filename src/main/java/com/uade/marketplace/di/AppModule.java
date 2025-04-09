package com.uade.marketplace.di;

import com.uade.marketplace.controller.web_services.product.ProductWebService;
import com.uade.marketplace.controller.web_services.product.ProductWebServiceImpl;
import com.uade.marketplace.data.repositories.CategoryRepository;
import com.uade.marketplace.data.repositories.OrderRepository;
import com.uade.marketplace.data.repositories.ProductRepository;
import com.uade.marketplace.data.repositories.UserRepository;
import com.uade.marketplace.service.category.CategoryService;
import com.uade.marketplace.service.category.CategoryServiceImpl;
import com.uade.marketplace.service.order.OrderService;
import com.uade.marketplace.service.order.OrderServiceImpl;
import com.uade.marketplace.service.product.ProductService;
import com.uade.marketplace.service.product.ProductServiceImpl;
import com.uade.marketplace.service.user.UserService;
import com.uade.marketplace.service.user.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppModule {
    //Services
    @Bean
    CategoryService categoryService(CategoryRepository categoryRepository) {
        return new CategoryServiceImpl(categoryRepository);
    }

    @Bean
    UserService userService(UserRepository userRepository) {
        return new UserServiceImpl(userRepository);
    }

    @Bean
    ProductService productService(ProductRepository productRepository) {
        return new ProductServiceImpl(productRepository);
    }

    @Bean
    OrderService orderService(OrderRepository orderRepository) {
        return new OrderServiceImpl(orderRepository);
    }

    //WebServices
    @Bean
    ProductWebService productWebService(ProductService productService, UserService userService) {
        return new ProductWebServiceImpl(productService, userService);
    }
}

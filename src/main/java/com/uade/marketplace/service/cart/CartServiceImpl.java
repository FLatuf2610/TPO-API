package com.uade.marketplace.service.cart;

import com.uade.marketplace.controller.dto.request.cart.CartItemRequest;
import com.uade.marketplace.controller.dto.response.cart.CartResponse;
import com.uade.marketplace.data.entities.CartEntity;
import com.uade.marketplace.data.entities.CartProductEntity;
import com.uade.marketplace.data.entities.ProductEntity;
import com.uade.marketplace.data.entities.UserEntity;
import com.uade.marketplace.data.repositories.CartProductRepository;
import com.uade.marketplace.data.repositories.CartRepository;
import com.uade.marketplace.data.repositories.ProductRepository;
import com.uade.marketplace.exceptions.cart.NoActiveCartException;
import com.uade.marketplace.exceptions.product.ProductNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

import static com.uade.marketplace.mappers.ProductMapper.toCartProd;

@AllArgsConstructor
@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private final CartRepository cartRepository;

    @Autowired
    private final ProductRepository productRepository;
    @Autowired
    private CartProductRepository cartProductRepository;

    public static double total(CartEntity cart) {
        return cart.getProducts().stream()
                .mapToDouble(producto -> producto.getProduct().getPrice() * producto.getQuantity())
                .sum();
    }

    @Override
    public CartResponse getCartByUser(UserEntity user) {
        CartEntity cart = cartRepository.findByUser(user)
                .orElseThrow(() -> new NoActiveCartException("No tienes un carrito activo"));

        return new CartResponse(cart.getId(), toCartProd(cart), total(cart));
    }

    @Override
    @Transactional
    public Double checkout(UserEntity user) {
        CartEntity cart = cartRepository.findByUser(user)
                .orElseThrow(() -> new NoActiveCartException("No tienes un carrito activo"));

        if (cart.getProducts().isEmpty()) {
            throw new RuntimeException("No se puede realizar el checkout de un carrito vacío");
        }

        for (CartProductEntity item : cart.getProducts()) {
            ProductEntity product = item.getProduct();
            if (product.getQuantity() < item.getQuantity()) {
                throw new RuntimeException("Stock insuficiente para el producto: " + product.getName());
            }
        }

        for (CartProductEntity cartItem : cart.getProducts()) {
            ProductEntity product = cartItem.getProduct();
            product.setQuantity(product.getQuantity() - cartItem.getQuantity());
            productRepository.save(product);
        }

        cartRepository.delete(cart);
        return total(cart);
    }

    @Override
    public CartResponse addItemToCart(UserEntity user, CartItemRequest request) {

        ProductEntity product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new ProductNotFoundException("Producto no encontrado"));

        if (product.getQuantity() < request.getQuantity()) {
            throw new RuntimeException("Stock insuficiente para el producto: " + product.getName());
        }


        CartEntity cart = cartRepository.findByUser(user)
                .orElseGet(() -> {
                    CartEntity newCart = CartEntity.builder()
                            .user(user)
                            .products(new ArrayList<>())
                            .build();
                    return cartRepository.save(newCart);
                });


        Optional<CartProductEntity> existingItem = cart.getProducts().stream()
                .filter(item -> item.getProduct().getId().equals(request.getProductId()))
                .findFirst();

        if (existingItem.isPresent()) {

            CartProductEntity item = existingItem.get();
            int newQuantity = item.getQuantity() + request.getQuantity();

            if (product.getQuantity() < newQuantity) {
                throw new RuntimeException("Stock insuficiente para el producto: " + product.getName());
            }

            item.setQuantity(newQuantity);
            cartProductRepository.save(item);
        } else {

            CartProductEntity newItem = CartProductEntity.builder()
                    .product(product)
                    .order(cart)
                    .quantity(request.getQuantity())
                    .build();
            cart.getProducts().add(newItem);
            cartProductRepository.save(newItem);
        }


        return new CartResponse(cart.getId(), toCartProd(cart), total(cart));
    }


    @Override
    public CartResponse updateCartItem(UserEntity user, Long itemId, CartItemRequest request) {
        CartEntity cart = cartRepository.findByUser(user)
                .orElseThrow(() -> new NoActiveCartException("No tienes un carrito activo"));

        CartProductEntity item = cart.getProducts().stream()
                .filter(i -> i.getId().equals(itemId))
                .findFirst()
                .orElseThrow(() -> new ProductNotFoundException("Item no encontrado en el carrito"));

        ProductEntity product = item.getProduct();

        if (product.getQuantity() < request.getQuantity()) {
            throw new RuntimeException("Stock insuficiente para el producto: " + product.getName());
        }

        item.setQuantity(request.getQuantity());
        cartProductRepository.save(item);

        return new CartResponse(cart.getId(), toCartProd(cart), total(cart));

    }

    public CartResponse removeItemFromCart(UserEntity user, Long itemId) {
        CartEntity cart = cartRepository.findByUser(user)
                .orElseThrow(() -> new NoActiveCartException("No tienes un carrito activo"));

        CartProductEntity item = cart.getProducts().stream()
                .filter(i -> i.getId().equals(itemId))
                .findFirst()
                .orElseThrow(() -> new ProductNotFoundException("Item no encontrado en el carrito"));

        cart.getProducts().remove(item);
        cartProductRepository.delete(item);

        return new CartResponse(cart.getId(), toCartProd(cart), total(cart));
    }
}

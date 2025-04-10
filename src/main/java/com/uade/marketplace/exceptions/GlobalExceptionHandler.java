package com.uade.marketplace.exceptions;

import com.uade.marketplace.controller.dto.response.ErrorResponse;
import com.uade.marketplace.exceptions.category.CategoryAlreadyExistsException;
import com.uade.marketplace.exceptions.category.CategoryNotFoundException;
import com.uade.marketplace.exceptions.order.OrderCanNotBePayedException;
import com.uade.marketplace.exceptions.order.OrderNotFoundException;
import com.uade.marketplace.exceptions.product.ProductIdShouldBeNull;
import com.uade.marketplace.exceptions.product.ProductNotFoundException;
import com.uade.marketplace.exceptions.product.UserCanNotBuyItsOwnProductsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleProductNotFoundException(ProductNotFoundException e) {
        return new ResponseEntity<>(new ErrorResponse(HttpStatus.NOT_FOUND.value(), e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ProductIdShouldBeNull.class)
    public ResponseEntity<ErrorResponse> handleProductIdShouldBeNullException(ProductIdShouldBeNull e) {
        return new ResponseEntity<>(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserCanNotBuyItsOwnProductsException.class)
    public ResponseEntity<ErrorResponse> handleUserCanNotBuyItsOwnProductsException(UserCanNotBuyItsOwnProductsException e) {
        return new ResponseEntity<>(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCategoryDoesNotExistException(CategoryNotFoundException e) {
        return new ResponseEntity<>(new ErrorResponse(HttpStatus.NOT_FOUND.value(), e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CategoryAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleCategoryAlreadyExistsException(CategoryAlreadyExistsException e) {
        return new ResponseEntity<>(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleOrderNotFoundException(OrderNotFoundException e) {
        return new ResponseEntity<>(new ErrorResponse(HttpStatus.NOT_FOUND.value(), e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(OrderCanNotBePayedException.class)
    public ResponseEntity<ErrorResponse> handleOrderCanNotBePayed(OrderCanNotBePayedException e) {
        return new ResponseEntity<>(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
    }
}

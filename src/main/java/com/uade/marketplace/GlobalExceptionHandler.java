package com.uade.marketplace;

import com.uade.marketplace.controller.dto.response.ErrorResponse;
import com.uade.marketplace.exceptions.DBAccessException;
import com.uade.marketplace.exceptions.cart.NoActiveCartException;
import com.uade.marketplace.exceptions.category.CategoryAlreadyExistsException;
import com.uade.marketplace.exceptions.category.CategoryNotFoundException;
import com.uade.marketplace.exceptions.product.InvalidQuantityException;
import com.uade.marketplace.exceptions.product.ProductNotFoundException;
import com.uade.marketplace.exceptions.product.UserNotAllowedToModifyOtherUserProductException;
import com.uade.marketplace.exceptions.user.UserNotAllowedException;
import com.uade.marketplace.exceptions.user.UserNotFoundException;
import com.uade.marketplace.exceptions.DBAccessException;
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

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCategoryDoesNotExistException(CategoryNotFoundException e) {
        return new ResponseEntity<>(new ErrorResponse(HttpStatus.NOT_FOUND.value(), e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CategoryAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleCategoryAlreadyExistsException(CategoryAlreadyExistsException e) {
        return new ResponseEntity<>(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException e) {
        return new ResponseEntity<>(new ErrorResponse(HttpStatus.NOT_FOUND.value(), e.getMessage()), HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(UserNotAllowedToModifyOtherUserProductException.class)
    public ResponseEntity<ErrorResponse> handleUserNotAllowedToModifyOtherUserProductException(UserNotAllowedToModifyOtherUserProductException e) {
        return new ResponseEntity<>(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(UserNotAllowedException.class)
    public ResponseEntity<ErrorResponse> handleUserNotAllowedException(UserNotAllowedException e) {
        return new ResponseEntity<>(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoActiveCartException.class)
    public ResponseEntity<ErrorResponse> handleNoActiveCartException(NoActiveCartException e) {
        return new ResponseEntity<>(new ErrorResponse(HttpStatus.NOT_FOUND.value(), e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidQuantityException.class)
    public ResponseEntity<ErrorResponse> handleInvalidQuantityException(InvalidQuantityException e) {
        return new ResponseEntity<>(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DBAccessException.class)
    public ResponseEntity<ErrorResponse> handleDBAccessException(DBAccessException e) {
        return new ResponseEntity<>(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.NOT_FOUND);
    }
}
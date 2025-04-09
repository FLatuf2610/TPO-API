package com.uade.marketplace.controller.controllers;

import com.uade.marketplace.controller.dto.request.user.UserRequest;
import com.uade.marketplace.models.User;
import com.uade.marketplace.service.user.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@Valid @RequestBody UserRequest userRequest) {
        User registeredUser = userService.registrarUsuario(userRequest);
        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
    }
}

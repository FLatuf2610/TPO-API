package com.uade.marketplace.controller.controllers;

import com.uade.marketplace.controller.dto.request.user.UserRequest;
import com.uade.marketplace.controller.dto.response.JwtResponse;
import com.uade.marketplace.models.User;
import com.uade.marketplace.security.JwtTokenUtil;
import com.uade.marketplace.service.user.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private AuthenticationManager authenticationManager;


    @PostMapping("/register")
    public ResponseEntity<JwtResponse> registerUser(@Valid @RequestBody UserRequest userRequest) {

        User registeredUser = userService.registrarUsuario(userRequest);
        String token = jwtTokenUtil.generateToken(registeredUser.getEmail());

        JwtResponse response = new JwtResponse(
                token,
                registeredUser.getEmail(),
                registeredUser.getName(),
                registeredUser.getUserType().toString()
        );

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


}

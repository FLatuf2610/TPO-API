package com.uade.marketplace.controller.controllers;

import com.uade.marketplace.controller.dto.request.AuthenticationRequest;
import com.uade.marketplace.controller.dto.request.user.UserRequest;
import com.uade.marketplace.controller.dto.response.AuthenticationResponse;
import com.uade.marketplace.service.security.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private final AuthenticationService service;

    // POST --> {url}/user/register
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody UserRequest request) {
        return ResponseEntity.ok(service.register(request));
    }

    // POST --> {url}/user/authenticate
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(service.authenticate(request));
    }
}

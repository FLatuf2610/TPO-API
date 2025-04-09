package com.uade.marketplace.service.security;

import com.uade.marketplace.controller.config.JwtService;
import com.uade.marketplace.controller.dto.request.AuthenticationRequest;
import com.uade.marketplace.controller.dto.request.user.UserRequest;
import com.uade.marketplace.controller.dto.response.AuthenticationResponse;
import com.uade.marketplace.data.repositories.UserRepository;
import com.uade.marketplace.models.Role;
import com.uade.marketplace.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
        private final UserRepository repository;
        private final PasswordEncoder passwordEncoder;
        private final JwtService jwtService;
        private final AuthenticationManager authenticationManager;

        public AuthenticationResponse register(UserRequest request) {

                if (repository.existsByEmail(request.getEmail())) {
                        throw new RuntimeException("El email ya está registrado");
                }

                Role role = request.getRole();
                if (role != Role.USER && role != Role.ADMIN) {
                        throw new IllegalArgumentException("El tipo de usuario debe ser user o admin");
                }

                var user = User.builder()
                                .name(request.getName())
                                .lastName(request.getLastName())
                                .email(request.getEmail())
                                .password(passwordEncoder.encode(request.getPassword()))
                                .role(request.getRole())
                                .build();

                repository.save(user);
                var jwtToken = jwtService.generateToken(user);
                return AuthenticationResponse.builder()
                                .accessToken(jwtToken)
                                .build();
        }

        public AuthenticationResponse authenticate(AuthenticationRequest request) {
                authenticationManager.authenticate(
                                new UsernamePasswordAuthenticationToken(
                                                request.getEmail(),
                                                request.getPassword()));
                var user = repository.findByEmail(request.getEmail())
                                .orElseThrow();
                var jwtToken = jwtService.generateToken(user);
                return AuthenticationResponse.builder()
                                .accessToken(jwtToken)
                                .build();
        }
}

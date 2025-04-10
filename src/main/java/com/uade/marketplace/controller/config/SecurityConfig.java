package com.uade.marketplace.controller.config;

import com.uade.marketplace.models.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req -> req.requestMatchers("/user/register", "/user/authenticate")
                        .permitAll()
                        .requestMatchers(HttpMethod.POST, "/products").hasAuthority(Role.VENDEDOR.name())
                        .requestMatchers(HttpMethod.PUT, "/products/{id}").hasAuthority(Role.VENDEDOR.name())
                        .requestMatchers(HttpMethod.DELETE, "/products/{id}").hasAuthority(Role.VENDEDOR.name())
                        .requestMatchers(HttpMethod.GET, "/products/**").authenticated()
                        .requestMatchers("/cart/**").hasAnyAuthority(Role.COMPRADOR.name())
                        .anyRequest()
                        .authenticated())
                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}

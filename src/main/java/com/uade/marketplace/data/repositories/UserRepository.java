package com.uade.marketplace.data.repositories;

import com.uade.marketplace.data.entities.UserEntity;
import com.uade.marketplace.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    boolean existsByName(String username);
    boolean existsByEmail(String email);
}

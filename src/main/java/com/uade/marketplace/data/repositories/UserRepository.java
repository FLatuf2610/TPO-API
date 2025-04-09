package com.uade.marketplace.data.repositories;

import com.uade.marketplace.data.entities.UserEntity;
import com.uade.marketplace.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByName(String username);
    boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);
}

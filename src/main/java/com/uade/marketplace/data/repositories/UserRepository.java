package com.uade.marketplace.data.repositories;

import com.uade.marketplace.data.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    /**
     * Retrieves a user by their username.
     *
     * @param username the name of the user
     * @return an {@link Optional} containing the {@link UserEntity} if found, or empty otherwise
     */
    Optional<UserEntity> findByName(String username);
    /**
     * Checks whether a user with the given email exists.
     *
     * @param email the email to check
     * @return {@code true} if a user with the given email exists, {@code false} otherwise
     */
    boolean existsByEmail(String email);
    /**
     * Retrieves a user by their email.
     *
     * @param email the email of the user
     * @return an {@link Optional} containing the {@link UserEntity} if found, or empty otherwise
     */
    Optional<UserEntity> findByEmail(String email);
}

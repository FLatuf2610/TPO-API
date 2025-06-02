package com.uade.marketplace.data.repositories;

import com.uade.marketplace.data.entities.CartEntity;
import com.uade.marketplace.data.entities.UserEntity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<CartEntity, Long> {
    /**
     * Retrieves the cart associated with the given user.
     *
     * @param user the user entity whose cart is to be fetched
     * @return an {@link Optional} containing the {@link CartEntity} if found, or empty if not
     */
    Optional<CartEntity> findByUser(UserEntity user);
}

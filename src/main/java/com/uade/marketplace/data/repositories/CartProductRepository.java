package com.uade.marketplace.data.repositories;

import com.uade.marketplace.data.entities.CartEntity;
import com.uade.marketplace.data.entities.CartProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartProductRepository extends JpaRepository<CartProductEntity, Long> {

}

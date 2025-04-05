package com.uade.marketplace.data.repositories;

import com.uade.marketplace.data.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    @Query("SELECT p FROM ProductEntity p WHERE p.category.id = :categoryId")
    List<ProductEntity> findByCategoryId(@Param("categoryId") Long categoryId);
}

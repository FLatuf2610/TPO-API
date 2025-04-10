package com.uade.marketplace.data.repositories;

import com.uade.marketplace.data.entities.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {
}

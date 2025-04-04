package com.uade.marketplace.data.repositories;

import com.uade.marketplace.data.entities.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    @Query("SELECT o from OrderEntity o WHERE o.user.id = :userId")
    List<OrderEntity> findOrdersByUserId(@Param("userId") Long userId);
}

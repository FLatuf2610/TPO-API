package com.uade.marketplace.data.repositories;

import com.uade.marketplace.data.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}

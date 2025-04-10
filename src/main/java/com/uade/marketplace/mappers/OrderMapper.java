package com.uade.marketplace.mappers;

import com.uade.marketplace.data.entities.OrderEntity;
import com.uade.marketplace.data.entities.UserEntity;
import com.uade.marketplace.models.*;

public class OrderMapper {
    public static Order toDomain(OrderEntity entity) {
        return new Order(
                entity.getId(),
                entity.getProducts(),
                entity.getStatus(),
                entity.getPaymentInfo(),
                entity.getUser().getId()
        );
    }
}

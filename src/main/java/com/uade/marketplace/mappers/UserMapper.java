package com.uade.marketplace.mappers;

import com.uade.marketplace.controller.dto.response.user.UserNoProducts;
import com.uade.marketplace.models.User;

public class UserMapper {
    public static UserNoProducts toNoProducts(User user) {
        UserNoProducts userNoProducts = new UserNoProducts();
        userNoProducts.setId(user.getId());
        userNoProducts.setName(user.getName());
        userNoProducts.setLastName(user.getLastName());
        return userNoProducts;
    }
}

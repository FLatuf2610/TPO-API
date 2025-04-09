package com.uade.marketplace.service.user;

import com.uade.marketplace.controller.dto.request.user.UserRequest;
import com.uade.marketplace.models.User;

public interface UserService {
    User getUserById(Long id);
    User registrarUsuario(UserRequest userRequest);
}

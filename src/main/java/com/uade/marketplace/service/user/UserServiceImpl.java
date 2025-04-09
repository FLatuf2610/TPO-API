package com.uade.marketplace.service.user;

import com.uade.marketplace.controller.dto.request.user.UserRequest;
import com.uade.marketplace.data.entities.UserEntity;
import com.uade.marketplace.data.repositories.UserRepository;
import com.uade.marketplace.exceptions.user.UserNotFoundException;
import com.uade.marketplace.mappers.UserMapper;
import com.uade.marketplace.models.User;
import com.uade.marketplace.models.UserType;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUserById(Long id) {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("El usuario no existe"));
        return UserMapper.toDomain(userEntity);
    }

    @Override
    public User registrarUsuario(UserRequest userRequest) {
        if (userRepository.existsByEmail(userRequest.getEmail())) {
            throw new RuntimeException("El email ya está registrado");
        }

        UserType userType = userRequest.getUserType();
        if (userType != UserType.VENDEDOR && userType != UserType.COMPRADOR) {
            throw new IllegalArgumentException("El tipo de usuario debe ser VENDEDOR o COMPRADOR");
        }

        UserEntity userEntity = UserMapper.requestToEntity(userRequest);
        UserEntity savedEntity = userRepository.save(userEntity);

        return UserMapper.toDomain(savedEntity);
    }
}

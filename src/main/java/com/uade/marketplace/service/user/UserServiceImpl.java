package com.uade.marketplace.service.user;

import com.uade.marketplace.data.entities.UserEntity;
import com.uade.marketplace.data.repositories.UserRepository;
import com.uade.marketplace.exceptions.user.UserNotFoundException;
import com.uade.marketplace.mappers.UserMapper;
import com.uade.marketplace.models.User;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUserById(Long id) {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("El usuario no existe"));
        return UserMapper.toDomain(userEntity);
    }
}

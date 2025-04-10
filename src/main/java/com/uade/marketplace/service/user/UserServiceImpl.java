package com.uade.marketplace.service.user;

import com.uade.marketplace.data.entities.UserEntity;
import com.uade.marketplace.data.repositories.UserRepository;
import com.uade.marketplace.exceptions.DBAccessException;
import com.uade.marketplace.exceptions.user.UserNotFoundException;
import com.uade.marketplace.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import static com.uade.marketplace.mappers.UserMapper.toDomain;

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;


    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUserById(Long id) {
        try {
            UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("El usuario no existe"));
            return toDomain(userEntity);
        } catch (DataAccessException e) {
            throw new DBAccessException("No se pudo acceder a la base de datos", e);
        }
    }
}

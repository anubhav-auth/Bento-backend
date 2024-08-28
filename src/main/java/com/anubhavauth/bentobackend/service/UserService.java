package com.anubhavauth.bentobackend.service;

import com.anubhavauth.bentobackend.entities.UserEntity;
import com.anubhavauth.bentobackend.entities.enums.Roles;
import com.anubhavauth.bentobackend.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void registerUser(UserEntity user) {
        if (user.getRoles().contains(Roles.RESTAURANT_OWNER)){
            user.setRestaurantIds(Collections.emptyList());
        }
        userRepository.save(user);
    }

    public Optional<UserEntity> getUserById(ObjectId id) {
        return userRepository.findById(id);
    }

    public void updateUser(UserEntity user) {
        user.setUpdatedAt(LocalDateTime.now());
        userRepository.save(user);
    }
    public void deleteUser(ObjectId id) {
        userRepository.deleteById(id);
    }
}

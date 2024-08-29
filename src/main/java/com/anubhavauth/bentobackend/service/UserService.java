package com.anubhavauth.bentobackend.service;

import com.anubhavauth.bentobackend.entities.persistentEntities.UserEntity;
import com.anubhavauth.bentobackend.entities.enums.Roles;
import com.anubhavauth.bentobackend.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Service
public class UserService {
    private final UserRepository userRepository;
    private final RestaurantService restaurantService;

    @Autowired
    public UserService(UserRepository userRepository,@Lazy RestaurantService restaurantService) {
        this.userRepository = userRepository;
        this.restaurantService = restaurantService;
    }

    public void registerUser(UserEntity user) {
        if (user.getRoles().contains(Roles.RESTAURANT_OWNER)){
            user.setRestaurantIds(Collections.emptyList());
        }
        if (user.getRoles().contains(Roles.DELIVERY_PERSONNEL)){
            user.setOrderDelHistory(Collections.emptyList());
            user.setOrderDel(null);
        }
        if (user.getRoles().contains(Roles.CUSTOMER)){
            user.setOrderIds(Collections.emptyList());
            user.setReviewIds(Collections.emptyList());
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
        UserEntity userById = getUserById(id).get();
        if (userById.getRoles().contains(Roles.RESTAURANT_OWNER)) {
            List<ObjectId> restaurantIds = userById.getRestaurantIds();
            for (ObjectId restaurantId : restaurantIds) {
                restaurantService.deleteRestaurant(restaurantId);
            }
        }
        userRepository.deleteById(id);
    }

    public void updateUserRoles(ObjectId userId, List<Roles> roles) {
        Optional<UserEntity> userById = getUserById(userId);
        if (userById.isPresent()) {
            userById.get().setRoles(roles);
            userRepository.save(userById.get());
        }else {
            throw new RuntimeException("User not found");
        }
    }
}

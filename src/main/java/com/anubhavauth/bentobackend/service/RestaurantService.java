package com.anubhavauth.bentobackend.service;


import com.anubhavauth.bentobackend.entities.persistentEntities.RestaurantEntity;
import com.anubhavauth.bentobackend.entities.persistentEntities.UserEntity;
import com.anubhavauth.bentobackend.repository.MenuRepository;
import com.anubhavauth.bentobackend.repository.RestaurantRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final UserService userService;
    private final MenuRepository menuRepository;

    @Autowired
    public RestaurantService(RestaurantRepository restaurantRepository, UserService userService, MenuRepository menuRepository) {
        this.restaurantRepository = restaurantRepository;
        this.userService = userService;
        this.menuRepository = menuRepository;
    }

    public List<RestaurantEntity> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    public RestaurantEntity getRestaurantById(ObjectId id) {
        return restaurantRepository.findById(id).orElse(null);
    }

    public void createRestaurant(RestaurantEntity restaurant) {
        RestaurantEntity restaurantEntity = restaurantRepository.save(restaurant);
        Optional<UserEntity> user = userService.getUserById(restaurant.getOwnerId());
        if (user.isPresent()) {
            List<ObjectId> restaurants = user.get().getRestaurantIds();
            restaurants.add(restaurantEntity.getId());
            userService.updateUser(user.get());
        }
    }

    public void updateRestaurant(RestaurantEntity restaurant) {
        restaurantRepository.save(restaurant);
    }

    public void deleteRestaurant(ObjectId restaurantId){
        Optional<RestaurantEntity> byId = restaurantRepository.findById(restaurantId);
        Optional<UserEntity> userById = userService.getUserById(byId.get().getOwnerId());
        if (userById.isPresent()) {
            List<ObjectId> menuItems = byId.get().getMenuItems();
            menuRepository.deleteAllById(menuItems);
            userById.get().getRestaurantIds().remove(restaurantId);
            userService.updateUser(userById.get());
            restaurantRepository.delete(byId.get());
        }
    }

    public List<RestaurantEntity> getRestaurantsByName(String name) {
        try {
            return restaurantRepository.findByNameContainingIgnoreCase(name);
        }catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException("menu items doesnt exist");
        }
    }
}

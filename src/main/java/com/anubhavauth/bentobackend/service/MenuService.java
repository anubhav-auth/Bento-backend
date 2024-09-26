package com.anubhavauth.bentobackend.service;

import com.anubhavauth.bentobackend.entities.dtos.MenuItemDTO;
import com.anubhavauth.bentobackend.entities.persistentEntities.MenuItemEntity;
import com.anubhavauth.bentobackend.entities.persistentEntities.RestaurantEntity;
import com.anubhavauth.bentobackend.repository.MenuRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class MenuService {
    private final MenuRepository menuRepository;
    private final RestaurantService restaurantService;

    @Autowired
    public MenuService(MenuRepository menuRepository, RestaurantService restaurantService) {
        this.menuRepository = menuRepository;
        this.restaurantService = restaurantService;
    }

    public void createMenuItem(MenuItemEntity menuItem) {
        try {
            menuItem.setCreatedAt(LocalDateTime.now());
            menuItem.setUpdatedAt(LocalDateTime.now());
            RestaurantEntity restaurantById = restaurantService.getRestaurantById(menuItem.getRestaurantId());
            if (restaurantById == null) {
                new ResponseEntity<>("restaurant doesnt exist", HttpStatus.NOT_FOUND);
                return;
            }
            MenuItemEntity item = menuRepository.save(menuItem);
            restaurantById.getMenuItems().add(item.getId());
            restaurantService.updateRestaurant(restaurantById);
            new ResponseEntity<>("menu item created", HttpStatus.CREATED);
        }catch (Exception e) {
            log.error(e.getMessage());
            new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<MenuItemEntity>> fetchAllMenuItems(List<ObjectId> menuItems) {
        List<MenuItemEntity> allById = menuRepository.findAllById(menuItems);
        return new ResponseEntity<>(allById, HttpStatus.OK);
    }

    public MenuItemEntity fetchMenuItemById(ObjectId menuItemId) {
        Optional<MenuItemEntity> item = menuRepository.findById(menuItemId);
        if (item.isPresent()) {
            return item.get();
        }else {
            throw new RuntimeException("menu item not found");
        }
    }

    public ResponseEntity<String> updateMenuItem(ObjectId menuItem, MenuItemDTO menuItemDTO) {
        Optional<MenuItemEntity> byId = menuRepository.findById(menuItem);
        if (byId.isPresent()) {
            MenuItemEntity menuItemEntity = byId.get();
            menuItemEntity.setName(menuItemDTO.getName());
            menuItemEntity.setDescription(menuItemDTO.getDescription());
            menuItemEntity.setPrice(menuItemDTO.getPrice());
            menuItemEntity.setImageUrl(menuItemDTO.getImageUrl());
            menuItemEntity.setCategory(menuItemDTO.getCategory());
            menuItemEntity.setAvailability(menuItemDTO.getAvailability());
            menuItemEntity.setUpdatedAt(LocalDateTime.now());
            menuRepository.save(menuItemEntity);
            return new ResponseEntity<>("menu item updated", HttpStatus.OK);
        }else {
            return new ResponseEntity<>("menu item doesnt exist", HttpStatus.NOT_FOUND);
        }
    }

    public void deleteMenuItem(ObjectId menuItemId) {
        Optional<MenuItemEntity> byId = menuRepository.findById(menuItemId);
        if (byId.isPresent()) {
            ObjectId restaurantId = byId.get().getRestaurantId();
            RestaurantEntity restaurantById = restaurantService.getRestaurantById(restaurantId);
            restaurantById.getMenuItems().remove(menuItemId);
            restaurantService.updateRestaurant(restaurantById);
            menuRepository.delete(byId.get());
        }
    }

    public List<MenuItemEntity> getMenuItemByName(String name) {
        try {
            return menuRepository.findByNameContainingIgnoreCase(name);
        }catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException("menu items doesnt exist");
        }
    }
}

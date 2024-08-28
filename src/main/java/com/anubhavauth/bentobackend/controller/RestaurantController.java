package com.anubhavauth.bentobackend.controller;

import com.anubhavauth.bentobackend.entities.dtos.MenuItemDTO;
import com.anubhavauth.bentobackend.entities.dtos.RestaurantDTO;
import com.anubhavauth.bentobackend.entities.enums.Roles;
import com.anubhavauth.bentobackend.entities.persistentEntities.MenuItemEntity;
import com.anubhavauth.bentobackend.entities.persistentEntities.RestaurantEntity;
import com.anubhavauth.bentobackend.entities.persistentEntities.UserEntity;
import com.anubhavauth.bentobackend.service.MenuService;
import com.anubhavauth.bentobackend.service.RestaurantService;
import com.anubhavauth.bentobackend.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/restaurants")
@Slf4j
public class RestaurantController {

    private final RestaurantService restaurantService;
    private final UserService userService;
    private final MenuService menuService;

    @Autowired
    public RestaurantController(RestaurantService restaurantService, UserService userService, MenuService menuService) {
        this.restaurantService = restaurantService;
        this.userService = userService;
        this.menuService = menuService;
    }

    //    @Transactional
    @GetMapping("/all-restaurants")
    public ResponseEntity<List<RestaurantEntity>> getAllRestaurants() {
        try {
            List<RestaurantEntity> allRestaurants = restaurantService.getAllRestaurants();
            if (allRestaurants.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(allRestaurants, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error fetching Restaurant Data {}", e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("")
    public ResponseEntity<RestaurantEntity> getRestaurantById(@RequestParam ObjectId restaurantId) {
        try {
            RestaurantEntity restaurantById = restaurantService.getRestaurantById(restaurantId);
            if (restaurantById == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(restaurantById, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error getting Restaurant Data by ID {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }


    //    TODO
    @PostMapping("/add-restaurant")
    public ResponseEntity<String> addRestaurant(@RequestBody RestaurantDTO restaurantdto) {
        try {
            Optional<UserEntity> userById = userService.getUserById(restaurantdto.getUserId());
            if (userById.isPresent() && userById.get().getRoles().contains(Roles.RESTAURANT_OWNER)) {
                restaurantService.createRestaurant(
                        RestaurantEntity.builder()
                                .ownerId(restaurantdto.getUserId())
                                .name(restaurantdto.getName())
                                .address(restaurantdto.getAddress())
                                .phone(restaurantdto.getPhone())
                                .email(restaurantdto.getEmail())
                                .cuisines(restaurantdto.getCuisines())
                                .reviews(null)
                                .rating(null)
                                .openingHours(restaurantdto.getOpeningHours())
                                .menuItems(Collections.emptyList())
                                .orders(null)
                                .createdAt(LocalDateTime.now())
                                .updatedAt(LocalDateTime.now())
                                .build()
                );
                return new ResponseEntity<>("restaurant added", HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>("User not Authorised to create restaurant", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            log.error("Error creating Restaurant {}", e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update-restaurant")
    public ResponseEntity<String> updateRestaurant(@RequestParam ObjectId restaurantId, @RequestBody RestaurantDTO restaurantdto) {
        try {
            RestaurantEntity restaurantById = restaurantService.getRestaurantById(restaurantId);
            if (restaurantById == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            restaurantService.updateRestaurant(
                    RestaurantEntity.builder()
                            .name(restaurantdto.getName())
                            .address(restaurantdto.getAddress())
                            .phone(restaurantdto.getPhone())
                            .email(restaurantdto.getEmail())
                            .cuisines(restaurantdto.getCuisines())
                            .openingHours(restaurantdto.getOpeningHours())
                            .updatedAt(LocalDateTime.now())
                            .build()
            );
            return new ResponseEntity<>("restaurant updated", HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error updating Restaurant {}", e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("")
    public ResponseEntity<String> deleteRestaurant(@RequestParam ObjectId restaurantId) {
        try {
            restaurantService.deleteRestaurant(restaurantId);
            return new ResponseEntity<>("restaurant deleted", HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error deleting Restaurant {}", e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<RestaurantEntity>> searchRestaurant(@RequestParam String name) {
        try {
            List<RestaurantEntity> restaurants = restaurantService.getRestaurantsByName(name);
            if (restaurants.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(restaurants, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error searching Restaurant {}", e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/menu")
    public ResponseEntity<String> addMenuItem(@RequestParam ObjectId restaurantId, @RequestBody MenuItemDTO menuitem) {
        try {

            menuitem.setRestaurantId(restaurantId);

            menuService.createMenuItem(
                    MenuItemEntity.builder()
                            .restaurantId(menuitem.getRestaurantId())
                            .name(menuitem.getName())
                            .description(menuitem.getDescription())
                            .price(menuitem.getPrice())
                            .imageUrl("sample.com")
                            .availability(menuitem.getAvailability())
                            .category(menuitem.getCategory())
                            .createdAt(LocalDateTime.now())
                            .updatedAt(LocalDateTime.now())
                            .build()
            );
            return new ResponseEntity<>("menu created", HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("Error creating menu {}", e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/menu")
    public ResponseEntity<List<MenuItemEntity>> getMenuRestaurant(@RequestParam ObjectId restaurantId) {
        try {
            RestaurantEntity restaurantById = restaurantService.getRestaurantById(restaurantId);
            if (restaurantById == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            List<ObjectId> menuItems = restaurantById.getMenuItems();
            List<MenuItemEntity> allMenuItems = menuService.fetchAllMenuItems(menuItems).getBody();
            return new ResponseEntity<>(allMenuItems, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error searching menu {}", e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/menu-items/update")
    public ResponseEntity<String> updateMenuItem(@RequestParam ObjectId menuItemId, @RequestBody MenuItemDTO menuItemDTO) {
        try {
            menuService.updateMenuItem(menuItemId, menuItemDTO);
            return new ResponseEntity<>("restaurant menu item updated ", HttpStatus.OK);
        }catch (Exception e) {
            log.error("Error updating menu item {}", e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/menu-items/delete")
    public ResponseEntity<String> deleteMenuItem(@RequestParam ObjectId menuItemId) {
        try {
            menuService.deleteMenuItem(menuItemId);
            return new ResponseEntity<>("restaurant menu item deleted ", HttpStatus.OK);
        }catch (Exception e) {
            log.error("Error deleting menu item {}", e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

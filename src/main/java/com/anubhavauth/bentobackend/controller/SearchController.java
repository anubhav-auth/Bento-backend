package com.anubhavauth.bentobackend.controller;


import com.anubhavauth.bentobackend.entities.persistentEntities.MenuItemEntity;
import com.anubhavauth.bentobackend.entities.persistentEntities.RestaurantEntity;
import com.anubhavauth.bentobackend.entities.persistentEntities.SearchResult;
import com.anubhavauth.bentobackend.service.MenuService;
import com.anubhavauth.bentobackend.service.RestaurantService;
import com.anubhavauth.bentobackend.service.SearchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/search")
@Slf4j
public class SearchController {
    private final SearchService searchService;
    private final RestaurantService restaurantService;
    private final MenuService menuService;

    @Autowired
    public SearchController(SearchService searchService, RestaurantService restaurantService, MenuService menuService) {
        this.searchService = searchService;
        this.restaurantService = restaurantService;
        this.menuService = menuService;
    }

    @GetMapping("/all")
    public ResponseEntity<SearchResult> getSearchResults(@RequestParam String searchText) {
        try {
            SearchResult searchResult = searchService.searchResult(searchText);
            return new ResponseEntity<>(searchResult, HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/restaraunt")
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
    @GetMapping("/menu-item")
    public ResponseEntity<List<MenuItemEntity>> searchMenuItem(@RequestParam String name) {
        try {
            List<MenuItemEntity> menuItems = menuService.getMenuItemByName(name);
            if (menuItems.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(menuItems, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error searching menuItem {}", e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

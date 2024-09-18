package com.anubhavauth.bentobackend.service;

import com.anubhavauth.bentobackend.entities.persistentEntities.MenuItemEntity;
import com.anubhavauth.bentobackend.entities.persistentEntities.RestaurantEntity;
import com.anubhavauth.bentobackend.entities.persistentEntities.SearchResult;
import com.anubhavauth.bentobackend.repository.MenuRepository;
import com.anubhavauth.bentobackend.repository.RestaurantRepository;
import com.anubhavauth.bentobackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchService {

    private final RestaurantRepository restaurantRepository;
    private final MenuRepository menuRepository;

    @Autowired
    public SearchService(RestaurantRepository restaurantRepository, MenuRepository menuRepository) {
        this.restaurantRepository = restaurantRepository;
        this.menuRepository = menuRepository;
    }

    public SearchResult searchResult(String query) {
        List<RestaurantEntity> restaurantByNameContainingIgnoreCase = restaurantRepository.findByNameContainingIgnoreCase(query);
        List<MenuItemEntity> MenuItemByNameContainingIgnoreCase = menuRepository.findByNameContainingIgnoreCase(query);
        return new SearchResult(restaurantByNameContainingIgnoreCase, MenuItemByNameContainingIgnoreCase);
    }


}

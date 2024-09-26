package com.anubhavauth.bentobackend.controller;

import com.anubhavauth.bentobackend.entities.persistentEntities.MenuItemEntity;
import com.anubhavauth.bentobackend.service.MenuService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    private final MenuService menuService;

    @Autowired
    public CartController(MenuService menuService) {
        this.menuService = menuService;
    }

    @PostMapping("/cart-data")
    public ResponseEntity<List<MenuItemEntity>> getMenuEntity(@RequestBody List<ObjectId> items) {
        ResponseEntity<List<MenuItemEntity>> listResponseEntity = menuService.fetchAllMenuItems(items);
        if (listResponseEntity.getBody().isEmpty()) {
            throw new RuntimeException("");
        }
        return ResponseEntity.ok(listResponseEntity.getBody());
    }
}

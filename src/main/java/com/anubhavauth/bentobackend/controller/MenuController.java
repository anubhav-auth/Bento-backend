package com.anubhavauth.bentobackend.controller;


import com.anubhavauth.bentobackend.entities.persistentEntities.MenuItemEntity;
import com.anubhavauth.bentobackend.service.MenuService;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/menu-item")
@Slf4j
public class MenuController {

    private final MenuService menuService;
    @Autowired
    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping("")
    public ResponseEntity<MenuItemEntity> getMenuItem(@RequestParam ObjectId menuItemId) {
        try{
            MenuItemEntity menuItemEntity = menuService.fetchMenuItemById(menuItemId);
            return new ResponseEntity<>(menuItemEntity, HttpStatus.OK);
        }catch (Exception e){
            log.error("Error fetching Menu Item {}",e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

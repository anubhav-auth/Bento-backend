package com.anubhavauth.bentobackend.controller;


import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/menu-item")
public class MenuController {

    @GetMapping("/{menuItemId}")
    public ResponseEntity<String> getMenuItem(@PathVariable ObjectId menuItemId) {
        return new ResponseEntity<>("menu", HttpStatus.OK);
    }
}

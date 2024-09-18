package com.anubhavauth.bentobackend.controller;


import com.anubhavauth.bentobackend.entities.dtos.UserDTO;
import com.anubhavauth.bentobackend.entities.enums.Roles;
import com.anubhavauth.bentobackend.entities.persistentEntities.UserEntity;
import com.anubhavauth.bentobackend.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/public")
public class PublicController {

    private final UserService userService;

    public PublicController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/health-check")
    public ResponseEntity<String> healthCheck() {
        return new ResponseEntity<>("Running Ok", HttpStatus.OK);
    }

    @PostMapping("/register-customer")
    public ResponseEntity<String> customerRegistration(@RequestBody UserDTO userdto) {
        userService.registerUser(UserEntity.builder()
                .name(userdto.getName())
                .email(userdto.getEmail())
                .password(userdto.getPassword())
                .roles(List.of(Roles.CUSTOMER))
                .paymentIds(Collections.emptyList())
                .phone(userdto.getPhone())
                .address(List.of(userdto.getAddress()))
                .profilePicture(userdto.getProfilePicture())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build()
        );
        return new ResponseEntity<>("Customer Registered", HttpStatus.OK);
    }

    @PostMapping("/register-restaurant-owner")
    public ResponseEntity<String> restaurantOwnerRegistration(@RequestBody UserDTO userdto) {
        userService.registerUser(UserEntity.builder()
                .name(userdto.getName())
                .email(userdto.getEmail())
                .password(userdto.getPassword())
                .roles(List.of(Roles.RESTAURANT_OWNER))
                .paymentIds(Collections.emptyList())
                .phone(userdto.getPhone())
                .address(List.of(userdto.getAddress()))
                .profilePicture(userdto.getProfilePicture())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build()
        );
        return new ResponseEntity<>("Restaurant Owner Registered", HttpStatus.OK);
    }

    @PostMapping("/register-delivery-personnel")
    public ResponseEntity<String> deliveryPersonnelRegistration(@RequestBody UserDTO userdto) {
        userService.registerUser(UserEntity.builder()
                .name(userdto.getName())
                .email(userdto.getEmail())
                .password(userdto.getPassword())
                .roles(List.of(Roles.DELIVERY_PERSONNEL))
                .paymentIds(Collections.emptyList())
                .phone(userdto.getPhone())
                .address(List.of(userdto.getAddress()))
                .profilePicture(userdto.getProfilePicture())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build()
        );
        return new ResponseEntity<>("Delivery Personnel Registered", HttpStatus.OK);
    }
}

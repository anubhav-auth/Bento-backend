package com.anubhavauth.bentobackend.controller;


import com.anubhavauth.bentobackend.entities.UserDTO;
import com.anubhavauth.bentobackend.entities.UserEntity;
import com.anubhavauth.bentobackend.entities.enums.Roles;
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

    @PostMapping("/register")
    public ResponseEntity<String> userRegistration(@RequestBody UserDTO userdto) {
        userService.registerUser(UserEntity.builder()
                .name(userdto.getName())
                .email(userdto.getEmail())
                .password(userdto.getPassword())
                .roles(List.of(Roles.ADMINISTRATOR,Roles.CUSTOMER, Roles.RESTAURANT_OWNER, Roles.DELIVERY_PERSONNEL))
                .phone(userdto.getPhone())
                .address(userdto.getAddress())
                .profilePicture(userdto.getProfilePicture())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build()
        );
        return new ResponseEntity<>("User Registered", HttpStatus.OK);
    }
}

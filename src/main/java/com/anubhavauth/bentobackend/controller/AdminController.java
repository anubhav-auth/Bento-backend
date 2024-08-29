package com.anubhavauth.bentobackend.controller;


import com.anubhavauth.bentobackend.entities.dtos.UserDTO;
import com.anubhavauth.bentobackend.entities.enums.Roles;
import com.anubhavauth.bentobackend.entities.persistentEntities.UserEntity;
import com.anubhavauth.bentobackend.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register-admin")
    public ResponseEntity<String> adminRegistration(@RequestBody UserDTO userdto) {
        userService.registerUser(UserEntity.builder()
                .name(userdto.getName())
                .email(userdto.getEmail())
                .password(userdto.getPassword())
                .roles(List.of(Roles.ADMINISTRATOR))
                .paymentIds(Collections.emptyList())
                .phone(userdto.getPhone())
                .address(userdto.getAddress())
                .profilePicture(userdto.getProfilePicture())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build()
        );
        return new ResponseEntity<>("Admin Registered", HttpStatus.OK);
    }

    @PutMapping("/user/role-update")
    public ResponseEntity<String> manageUserRoles(@RequestParam ObjectId userId, @RequestBody List<Roles> roles){
        try{
            userService.updateUserRoles(userId, roles);
            return new ResponseEntity<>("User Roles Updated", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}

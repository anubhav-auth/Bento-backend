package com.anubhavauth.bentobackend.controller;

import com.anubhavauth.bentobackend.entities.dtos.UserDTO;
import com.anubhavauth.bentobackend.entities.persistentEntities.UserEntity;
import com.anubhavauth.bentobackend.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


//    TODO
//    @Transactional
    @PostMapping("/login")
    public ResponseEntity<String> userLogin() {
        return new ResponseEntity<>("login success", HttpStatus.OK);
    }

//    @Transactional
    @GetMapping("/profile")
    public ResponseEntity<UserEntity> userProfile(@RequestParam ObjectId userId) {
        try {
            Optional<UserEntity> userById = userService.getUserById(userId);
            return userById.map(userEntity -> new ResponseEntity<>(userEntity, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        }catch (Exception e){
            log.error("Exception occurred when finding user{}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    @Transactional
    @PutMapping("/profile")
    public ResponseEntity<String> userUpdateProfile(@RequestParam ObjectId userId, @RequestBody UserDTO userDTO) {

        try{
            Optional<UserEntity> userById = userService.getUserById(userId);
            if (userById.isPresent()){
                userById.get().setName(userDTO.getName());
                userById.get().setEmail(userDTO.getEmail());
                userById.get().setAddress(userDTO.getAddress());
                userById.get().setProfilePicture(userDTO.getProfilePicture());

                userService.updateUser(userById.get());
                return new ResponseEntity<>("User Updated", HttpStatus.OK);
            }else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            log.error("Exception occurred when update user{}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    @Transactional
    @DeleteMapping("/profile")
    public ResponseEntity<String> userDeleteProfile(@RequestParam ObjectId userId) {
        try {
            userService.deleteUser(userId);
            return new ResponseEntity<>("userDeleteProfile", HttpStatus.OK);
        }catch (Exception e){
            log.error("Exception occured when delete user{}" , e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


//    @Transactional
    @PutMapping("/profile/change-password")
    public ResponseEntity<String> userChangePassword(@RequestParam ObjectId userId, @RequestBody UserDTO userDTO) {
        try {
            Optional<UserEntity> userById = userService.getUserById(userId);
            if (userById.isPresent()) {
                userById.get().setPassword(userDTO.getPassword());
                userService.updateUser(userById.get());
                return new ResponseEntity<>("Password Changed", HttpStatus.OK);
            }else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            log.error("Exception occured when change password{}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    TODO
//    @Transactional
    @PostMapping("/profile/forgot-password")
    public ResponseEntity<String> userForgotPassword() {
        return new ResponseEntity<>("userForgotPassword", HttpStatus.OK);
    }

//    TODO
//    @Transactional
    @GetMapping("/{userId}/orders")
    public ResponseEntity<String> userOrders(@PathVariable ObjectId userId) {
        return new ResponseEntity<>("userOrders", HttpStatus.OK);
    }

//    TODO
//    @Transactional
    @GetMapping("/{userId}/payments")
    public ResponseEntity<String> userPayments(@PathVariable ObjectId userId) {
        return new ResponseEntity<>("userPayments", HttpStatus.OK);
    }
}

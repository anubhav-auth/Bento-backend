package com.anubhavauth.bentobackend.controller;


import com.anubhavauth.bentobackend.entities.enums.Roles;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @PutMapping("/user/{userId}/role")
    public ResponseEntity<String> manageUserRoles(@PathVariable ObjectId userId, @RequestBody List<Roles> roles){
        return new ResponseEntity<>("role updated", HttpStatus.OK);
    }

}

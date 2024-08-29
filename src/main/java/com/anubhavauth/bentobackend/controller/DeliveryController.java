package com.anubhavauth.bentobackend.controller;


import com.anubhavauth.bentobackend.entities.persistentEntities.UserEntity;
import com.anubhavauth.bentobackend.service.DeliveryService;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/delivery")
@Slf4j
public class DeliveryController {

    private final DeliveryService deliveryService;
    @Autowired
    public DeliveryController(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    @GetMapping("/delivery-personnel")
    public ResponseEntity<List<UserEntity>> getAvailableDeliveryPersonnel() {
        try{
            List<UserEntity> allDeliveryPersonnelAvailable = deliveryService.getAllDeliveryPersonnelAvailable();
            return new ResponseEntity<>(allDeliveryPersonnelAvailable, HttpStatus.OK);
        }catch (Exception e){
           log.error("Error getting available personnel",e.getMessage());
           return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/order-assign")
    public ResponseEntity<String> assignDeliveryPersonnel(@RequestParam ObjectId orderId, @RequestParam ObjectId deliveryPersonnelId) {
        return new ResponseEntity<>("delivery personnel", HttpStatus.OK);
    }
    @PutMapping("/orders/{orderId}/delivery-status")
    public ResponseEntity<String> updateDeliveryStatus(@PathVariable ObjectId orderId) {
        return new ResponseEntity<>("delivery status", HttpStatus.OK);
    }
}

package com.anubhavauth.bentobackend.controller;


import com.anubhavauth.bentobackend.entities.dtos.DeliveryDTO;
import com.anubhavauth.bentobackend.entities.enums.DeliveryStatus;
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
           log.error("Error getting available personnel {}",e.getMessage());
           return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/order-assign")
    public ResponseEntity<String> assignDeliveryPersonnel(@RequestParam ObjectId orderId, @RequestParam ObjectId deliveryPersonnelId) {
        try {
            deliveryService.assignDeliveryPersonnel(orderId, deliveryPersonnelId);
            return new ResponseEntity<>("Order assigned successfully", HttpStatus.OK);
        }catch (Exception e){
            log.error("Error assigning delivery personnel {}",e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/status/update")
    public ResponseEntity<String> updateDeliveryStatus(@RequestParam ObjectId orderId, @RequestBody DeliveryStatus deliveryStatus) {
        try {
            deliveryService.updateDeliveryStatus(orderId, deliveryStatus);
            return new ResponseEntity<>("Order updated successfully", HttpStatus.OK);
        }catch (Exception e){
            log.error("Error updating delivery status {}",e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

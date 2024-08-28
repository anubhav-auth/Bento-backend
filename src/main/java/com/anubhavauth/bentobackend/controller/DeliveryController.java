package com.anubhavauth.bentobackend.controller;


import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/delivery")
public class DeliveryController {

    @GetMapping("/delivery-personnel")
    public ResponseEntity<String> getDeliveryPersonnel() {
        return new ResponseEntity<>("delivery personnel", HttpStatus.OK);
    }

    @PutMapping("/order/{orderId}/delivery-personnel/{deliveryPersonnelId}")
    public ResponseEntity<String> assignDeliveryPersonnel(@PathVariable ObjectId orderId, @PathVariable ObjectId deliveryPersonnelId) {
        return new ResponseEntity<>("delivery personnel", HttpStatus.OK);
    }
    @PutMapping("/orders/{orderId}/delivery-status")
    public ResponseEntity<String> updateDeliveryStatus(@PathVariable ObjectId orderId) {
        return new ResponseEntity<>("delivery status", HttpStatus.OK);
    }
}

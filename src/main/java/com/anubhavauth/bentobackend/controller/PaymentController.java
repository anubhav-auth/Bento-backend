package com.anubhavauth.bentobackend.controller;


import com.anubhavauth.bentobackend.entities.dtos.PaymentDTO;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @PostMapping("/new")
    public ResponseEntity<String> newPayment(@RequestBody PaymentDTO paymentdto) {
        return new ResponseEntity<>("payment made", HttpStatus.OK);
    }

    @GetMapping("/{paymentId}")
    public ResponseEntity<String> getPayment(@PathVariable ObjectId paymentId) {
        return new ResponseEntity<>("Payment details",HttpStatus.OK);
    }
}

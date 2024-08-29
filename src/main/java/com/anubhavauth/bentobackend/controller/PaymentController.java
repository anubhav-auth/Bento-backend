package com.anubhavauth.bentobackend.controller;


import com.anubhavauth.bentobackend.entities.dtos.PaymentDTO;
import com.anubhavauth.bentobackend.entities.dtos.RestaurantDTO;
import com.anubhavauth.bentobackend.entities.enums.PaymentStatus;
import com.anubhavauth.bentobackend.entities.persistentEntities.PaymentEntity;
import com.anubhavauth.bentobackend.entities.persistentEntities.RestaurantEntity;
import com.anubhavauth.bentobackend.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/payments")
@Slf4j
public class PaymentController {
    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/new")
    public ResponseEntity<Pair<ObjectId, String>> newPayment(@RequestBody PaymentDTO paymentdto) {
        try{
            ObjectId payment = paymentService.createPayment(
                    PaymentEntity.builder()
                            .orderId(paymentdto.getUserId())
                            .userId(paymentdto.getUserId())
                            .restaurantId(paymentdto.getRestaurantId())
                            .amount(paymentdto.getAmount())
                            .paymentMethod(paymentdto.getPaymentMethod())
                            .transactionId(paymentdto.getTransactionId())
                            .paymentStatus(paymentdto.getPaymentStatus())
                            .paidAt(LocalDateTime.now())
                            .build()
            );

            return new ResponseEntity<>(Pair.of(payment,"Payment Created"), HttpStatus.OK);
        }catch (Exception e){
            log.error("Error initialising Payment {}",e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("")
    public ResponseEntity<String> getPayment(@RequestParam ObjectId paymentId) {
        try{
            paymentService.getPayment(paymentId);
            return new ResponseEntity<>("Payment found", HttpStatus.OK);
        }catch (Exception e){
            log.error("Error getting payment {}",e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/restaurant")
    public ResponseEntity<List<PaymentEntity>> getAllPaymentsById(@RequestParam ObjectId restaurantId) {
        try{
            List<PaymentEntity> paymentRestaurant = paymentService.getPaymentRestaurant(restaurantId);
            return new ResponseEntity<>(paymentRestaurant, HttpStatus.OK);
        }catch (Exception e){
            log.error("Error getting payment details {}",e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/status-update")
    public ResponseEntity<String> updatePaymentStatus(@RequestParam ObjectId paymentId, @RequestBody PaymentDTO paymentdto) {
        try{
            paymentService.updatePaymentStatus(paymentId, paymentdto.getPaymentStatus());
            return new ResponseEntity<>("Payment status updated", HttpStatus.OK);
        }catch (Exception e){
            log.error("Error updating payment {}",e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

package com.anubhavauth.bentobackend.controller;


import com.anubhavauth.bentobackend.entities.OrderDTO;
import com.anubhavauth.bentobackend.entities.OrderEntity;
import com.anubhavauth.bentobackend.entities.enums.OrderStatus;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @PostMapping("/place-order")
    public ResponseEntity<String> placeOrder(OrderDTO orderdto) {
        return new ResponseEntity<>("order placed", HttpStatus.CREATED);
    }

    @GetMapping("/{orderID}")
    public ResponseEntity<String> getOrderById(@PathVariable ObjectId orderID) {
        return new ResponseEntity<>("order found", HttpStatus.OK);
    }

    @PutMapping("/{orderId}/status")
    public ResponseEntity<String> updateOrderStatus(@PathVariable ObjectId orderId, OrderStatus status) {
        return new ResponseEntity<>("order updated", HttpStatus.OK);
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<String> cancelOrder(@PathVariable ObjectId orderId) {
        return new ResponseEntity<>("order cancelled", HttpStatus.OK);
    }
}

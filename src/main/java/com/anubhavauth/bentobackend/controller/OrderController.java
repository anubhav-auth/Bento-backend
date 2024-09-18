package com.anubhavauth.bentobackend.controller;


import com.anubhavauth.bentobackend.entities.dtos.OrderDTO;
import com.anubhavauth.bentobackend.entities.enums.OrderStatus;
import com.anubhavauth.bentobackend.entities.enums.PaymentStatus;
import com.anubhavauth.bentobackend.entities.persistentEntities.OrderEntity;
import com.anubhavauth.bentobackend.service.OrderService;
import com.anubhavauth.bentobackend.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/orders")
@Slf4j
public class OrderController {
    private final OrderService orderService;
    private final PaymentService paymentService;
    @Autowired
    public OrderController(OrderService orderService, PaymentService paymentService) {
        this.orderService = orderService;
        this.paymentService = paymentService;
    }

    @PostMapping("/place/online")
    public ResponseEntity<String> placeOrderWithPayment(@RequestParam ObjectId userId, @RequestParam ObjectId restaurantId, @RequestBody OrderDTO orderdto) {
        try{
            ObjectId order = orderService.createOrder(
                    OrderEntity.builder()
                            .userId(userId)
                            .restaurantId(restaurantId)
                            .paymentId(orderdto.getPaymentId())
                            .items(orderdto.getItems())
                            .totalPrice(orderdto.getTotalPrice())
                            .status(OrderStatus.WAITING_FOR_APPROVAL)
                            .deliveryAddress(orderdto.getDeliveryAddress())
                            .paymentStatus(PaymentStatus.PAID)
                            .placedOn(LocalDateTime.now())
                            .updatedOn(LocalDateTime.now())
                            .build()
            );

            paymentService.updatePaymentWithObjectId(order, orderdto.getPaymentId());
            return new ResponseEntity<>("Order Created",HttpStatus.OK);
        }catch (Exception e){
            log.error("Error placing order",e.getMessage());
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping("/place/cod")
    public ResponseEntity<String> placeOrderCOD(@RequestBody OrderDTO orderdto) {
        try {
            orderService.createOrder(
                    OrderEntity.builder()
                            .userId(orderdto.getUserId())
                            .restaurantId(orderdto.getRestaurantId())
                            .paymentId(orderdto.getPaymentId())
                            .items(orderdto.getItems())
                            .totalPrice(orderdto.getTotalPrice())
                            .status(OrderStatus.WAITING_FOR_APPROVAL)
                            .deliveryAddress(orderdto.getDeliveryAddress())
                            .paymentStatus(PaymentStatus.PENDING)
                            .placedOn(LocalDateTime.now())
                            .updatedOn(LocalDateTime.now())
                            .build()
            );
            return new ResponseEntity<>("Order Created",HttpStatus.OK);
        }catch (Exception e){
            log.error("Error placing Order {}",e.getMessage());
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("")
    public ResponseEntity<OrderEntity> getOrderById(@RequestParam ObjectId orderID) {
        try{
            return new ResponseEntity<>(orderService.getOrderById(orderID),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/status")
    public ResponseEntity<String> updateOrderStatus(@RequestParam ObjectId orderId, @RequestBody OrderStatus status) {
        try {
            OrderEntity orderById = orderService.getOrderById(orderId);
            orderById.setStatus(status);
            orderService.updateOrder(orderById);
            return new ResponseEntity<>("Order Updated",HttpStatus.OK);
        }catch (Exception e){
            log.error("Error updating Order status {}",e.getMessage());
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<String> cancelOrder(@PathVariable ObjectId orderId) {
        return new ResponseEntity<>("order cancelled", HttpStatus.OK);
    }
}

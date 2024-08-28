package com.anubhavauth.bentobackend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/promotions")
public class PromotionController {

    @PostMapping("/{orderId}/apply-discount/{promotionId}")
    public ResponseEntity<String> applyDiscount(@PathVariable String orderId, @PathVariable String promotionId) {
        return new ResponseEntity<>("discount applied", HttpStatus.OK);
    }

    @GetMapping("/all-discounts")
    public ResponseEntity<String> getAvailable() {
        return new ResponseEntity<>("all-discounts", HttpStatus.OK);
    }


}

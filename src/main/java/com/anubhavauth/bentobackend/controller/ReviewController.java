package com.anubhavauth.bentobackend.controller;

import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/review")
public class ReviewController {

    @PostMapping("/restaurant/{restaurantId}")
    public ResponseEntity<String> submitReview(@PathVariable ObjectId restaurantId) {
        return new ResponseEntity<>("review submitted", HttpStatus.OK);
    }

    @GetMapping("/restaurants/{restaurantId}/reviews")
    public ResponseEntity<String> getReviews(@PathVariable ObjectId restaurantId) {
        return new ResponseEntity<>("got reviews", HttpStatus.OK);
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<String> getReview(@PathVariable ObjectId reviewId) {
        return new ResponseEntity<>("review", HttpStatus.OK);
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<String> updateReview(@PathVariable ObjectId reviewId){
        return new ResponseEntity<>("review updated", HttpStatus.OK);
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable ObjectId reviewId){
        return new ResponseEntity<>("review deleted", HttpStatus.OK);
    }

}

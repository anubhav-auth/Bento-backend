package com.anubhavauth.bentobackend.controller;

import com.anubhavauth.bentobackend.entities.dtos.ReviewDTO;
import com.anubhavauth.bentobackend.entities.persistentEntities.RestaurantEntity;
import com.anubhavauth.bentobackend.entities.persistentEntities.ReviewEntity;
import com.anubhavauth.bentobackend.service.RestaurantService;
import com.anubhavauth.bentobackend.service.ReviewService;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/review")
@Slf4j
public class ReviewController {

    private final ReviewService reviewService;
    private final RestaurantService restaurantService;

    @Autowired
    public ReviewController(ReviewService reviewService, RestaurantService restaurantService) {
        this.reviewService = reviewService;
        this.restaurantService = restaurantService;
    }


    @PostMapping("/restaurant")
    public ResponseEntity<String> submitReview(@RequestParam ObjectId restaurantId,@RequestParam ObjectId userId, ReviewDTO reviewdto) {
        try{
            reviewService.createReview(
                    ReviewEntity.builder()
                            .userId(userId)
                            .restaurantId(restaurantId)
                            .rating(reviewdto.getRating())
                            .comment(reviewdto.getComment())
                            .reviewedAt(LocalDateTime.now())
                            .updatedAt(LocalDateTime.now())
                            .build()

            );
            return new ResponseEntity<>("Review created", HttpStatus.CREATED);
        }catch (Exception e){
            log.error("Error submitting Review {}",e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/restaurants/reviews")
    public ResponseEntity<List<ReviewEntity>> getReviews(@RequestParam ObjectId restaurantId) {
        try {
            RestaurantEntity restaurantById = restaurantService.getRestaurantById(restaurantId);
            if (restaurantById != null) {

                List<ObjectId> reviews = restaurantById.getReviews();
                List<ReviewEntity> reviewEntities = reviewService.fetchAllReviews(reviews);
                return new ResponseEntity<>(reviewEntities, HttpStatus.OK);
            }else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            log.error("Error fetching reviews {}",e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("")
    public ResponseEntity<ReviewEntity> getReview(@RequestParam ObjectId reviewId) {
        try{
            ReviewEntity reviewEntity = reviewService.fetchReviewById(reviewId);
            if (reviewEntity != null) {
                return new ResponseEntity<>(reviewEntity, HttpStatus.OK);
            }else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            log.error("Error fetching review {}",e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateReview(@RequestParam ObjectId reviewId, @RequestBody ReviewDTO reviewdto) {
        try{
            ReviewEntity reviewEntity = reviewService.fetchReviewById(reviewId);
            if (reviewEntity != null) {
                reviewEntity.setRating(reviewdto.getRating());
                reviewEntity.setComment(reviewdto.getComment());
                reviewEntity.setUpdatedAt(LocalDateTime.now());
                reviewService.updateReview(reviewEntity);
                return new ResponseEntity<>("Review updated", HttpStatus.OK);
            }else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            log.error("Error fetching review {}",e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteReview(@RequestParam ObjectId reviewId){
        try{
            reviewService.deleteReview(reviewId);
            return new ResponseEntity<>("Review deleted", HttpStatus.OK);
        }catch (Exception e){
            log.error("Error deleting review {}",e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

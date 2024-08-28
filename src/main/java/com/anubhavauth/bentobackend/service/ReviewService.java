package com.anubhavauth.bentobackend.service;

import com.anubhavauth.bentobackend.entities.persistentEntities.RestaurantEntity;
import com.anubhavauth.bentobackend.entities.persistentEntities.ReviewEntity;
import com.anubhavauth.bentobackend.entities.persistentEntities.UserEntity;
import com.anubhavauth.bentobackend.repository.RestaurantRepository;
import com.anubhavauth.bentobackend.repository.ReviewRepository;
import com.anubhavauth.bentobackend.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final RestaurantRepository restaurantRepository;
    private final UserRepository userRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository, RestaurantRepository restaurantRepository, UserRepository userRepository) {
        this.reviewRepository = reviewRepository;
        this.restaurantRepository = restaurantRepository;
        this.userRepository = userRepository;
    }

    public void createReview(ReviewEntity review) {
        Optional<RestaurantEntity> byId = restaurantRepository.findById(review.getRestaurantId());
        Optional<UserEntity> byId1 = userRepository.findById(review.getUserId());
        if (byId.isPresent() && byId1.isPresent()) {
            ReviewEntity saved = reviewRepository.save(review);
            byId1.get().getReviewIds().add(saved.getId());
            userRepository.save(byId1.get());
            byId.get().getReviews().add(saved.getId());
            byId.get().setRating(getRating(review.getRestaurantId()));
            restaurantRepository.save(byId.get());
        } else {
            throw new RuntimeException("Restaurant/User not found");
        }
    }

    public List<ReviewEntity> fetchAllReviews(List<ObjectId> reviewIds) {
        return reviewRepository.findAllById(reviewIds);
    }

    public ReviewEntity fetchReviewById(ObjectId id) {
        return reviewRepository.findById(id).orElse(null);
    }

    public void updateReview(ReviewEntity review) {
        Optional<RestaurantEntity> byId = restaurantRepository.findById(review.getRestaurantId());
        if (byId.isPresent()) {
            reviewRepository.save(review);
            byId.get().setRating(getRating(review.getRestaurantId()));
        }else {
            throw new RuntimeException("Exception in updating review");
        }

    }

    public void deleteReview(ObjectId id) {
        ReviewEntity reviewById = reviewRepository.findById(id).get();
        Optional<RestaurantEntity> restaurantById = restaurantRepository.findById(reviewById.getRestaurantId());
        Optional<UserEntity> userById = userRepository.findById(reviewById.getUserId());
        if (restaurantById.isPresent() && userById.isPresent()) {
            restaurantById.get().getReviews().remove(id);
            restaurantById.get().setRating(getRating(reviewById.getRestaurantId()));
            userById.get().getReviewIds().remove(id);
            restaurantRepository.save(restaurantById.get());
            userRepository.save(userById.get());
            reviewRepository.delete(reviewById);
        } else {
            throw new RuntimeException("Restaurant/User not found");
        }
    }

    public Double getRating(ObjectId id) {
        Optional<RestaurantEntity> byId = restaurantRepository.findById(id);
        if (byId.isPresent()) {
            List<ObjectId> reviews = byId.get().getReviews();
            List<ReviewEntity> reviewEntities = fetchAllReviews(reviews);
            return reviewEntities.stream().mapToDouble(ReviewEntity::getRating).average().orElse(0.0);
        } else {
            throw new RuntimeException("Restaurant/User not found");
        }
    }

}

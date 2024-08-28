package com.anubhavauth.bentobackend.repository;

import com.anubhavauth.bentobackend.entities.RestaurantEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RestaurantRepository extends MongoRepository<RestaurantEntity, ObjectId> {
    List<RestaurantEntity> findByNameContainingIgnoreCase(String name);
}

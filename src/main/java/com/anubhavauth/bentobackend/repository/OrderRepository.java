package com.anubhavauth.bentobackend.repository;

import com.anubhavauth.bentobackend.entities.persistentEntities.OrderEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<OrderEntity, ObjectId> {
}

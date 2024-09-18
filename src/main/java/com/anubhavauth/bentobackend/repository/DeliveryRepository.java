package com.anubhavauth.bentobackend.repository;

import com.anubhavauth.bentobackend.entities.persistentEntities.DeliveryEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DeliveryRepository extends MongoRepository<DeliveryEntity, ObjectId> {
}

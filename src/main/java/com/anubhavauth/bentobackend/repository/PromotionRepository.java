package com.anubhavauth.bentobackend.repository;

import com.anubhavauth.bentobackend.entities.persistentEntities.PromotionEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PromotionRepository extends MongoRepository<PromotionEntity, ObjectId> {
}

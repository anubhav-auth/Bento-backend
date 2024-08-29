package com.anubhavauth.bentobackend.repository;

import com.anubhavauth.bentobackend.entities.persistentEntities.PaymentEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PaymentRepository extends MongoRepository<PaymentEntity, ObjectId> {
}

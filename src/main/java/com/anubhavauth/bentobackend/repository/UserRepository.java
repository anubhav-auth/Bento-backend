package com.anubhavauth.bentobackend.repository;

import com.anubhavauth.bentobackend.entities.UserEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<UserEntity, ObjectId> {

}

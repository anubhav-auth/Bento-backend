package com.anubhavauth.bentobackend.repository;

import com.anubhavauth.bentobackend.entities.enums.Roles;
import com.anubhavauth.bentobackend.entities.persistentEntities.UserEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface UserRepository extends MongoRepository<UserEntity, ObjectId> {

    @Query("{ 'roles': { '$elemMatch': { '$eq': ?0 } }, 'orderDel': { '$exists': false } }")
    List<UserEntity> findUsersByRoleAndOrderDelIsEmpty(Roles role);

}

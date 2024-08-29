package com.anubhavauth.bentobackend.repository;

import com.anubhavauth.bentobackend.entities.enums.Roles;
import com.anubhavauth.bentobackend.entities.persistentEntities.UserEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<UserEntity, ObjectId> {

    List<UserEntity> findByRolesAndOrderDelIsEmpty(List<Roles> roles);

}

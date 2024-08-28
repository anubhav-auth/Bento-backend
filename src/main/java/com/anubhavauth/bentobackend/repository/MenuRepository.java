package com.anubhavauth.bentobackend.repository;

import com.anubhavauth.bentobackend.entities.MenuItemEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MenuRepository extends MongoRepository<MenuItemEntity, ObjectId> {
}

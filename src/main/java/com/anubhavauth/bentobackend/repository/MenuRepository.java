package com.anubhavauth.bentobackend.repository;

import com.anubhavauth.bentobackend.entities.persistentEntities.MenuItemEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MenuRepository extends MongoRepository<MenuItemEntity, ObjectId> {
    List<MenuItemEntity> findByNameContainingIgnoreCase(String name);
}

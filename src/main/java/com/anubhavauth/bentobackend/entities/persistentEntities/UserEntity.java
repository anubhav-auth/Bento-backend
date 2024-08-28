package com.anubhavauth.bentobackend.entities.persistentEntities;

import com.anubhavauth.bentobackend.entities.enums.Roles;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    @Id
    private ObjectId id;
    private String name;
    private String email;
    private String password;
    private List<Roles> roles;
    private List<ObjectId> restaurantIds;
    private List<ObjectId> reviewIds;
    private Long phone;
    private String address;
    private String profilePicture;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

/*
relationships
o	Orders: A user can have multiple orders.
o	Reviews: A user can write multiple reviews for different restaurants.
 */

package com.anubhavauth.bentobackend.entities.persistentEntities;

import com.anubhavauth.bentobackend.ObjectIdSerializer;
import com.anubhavauth.bentobackend.entities.enums.Roles;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
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
    @JsonSerialize(using = ObjectIdSerializer.class)
    private ObjectId id;

    private String name;
    private String email;
    private String password;
    private List<Roles> roles;
    private ObjectId orderDel;

    @JsonSerialize(contentUsing = ObjectIdSerializer.class)
    private List<ObjectId> orderDelHistory;

    @JsonSerialize(contentUsing = ObjectIdSerializer.class)
    private List<ObjectId> restaurantIds;

    @JsonSerialize(contentUsing = ObjectIdSerializer.class)
    private List<ObjectId> reviewIds;

    @JsonSerialize(contentUsing = ObjectIdSerializer.class)
    private List<ObjectId> paymentIds;

    @JsonSerialize(contentUsing = ObjectIdSerializer.class)
    private List<ObjectId> orderIds;

    private String phone;
    private List<Address> address;
    private String profilePicture;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}



/*
relationships
o	Orders: A user can have multiple orders.
o	Reviews: A user can write multiple reviews for different restaurants.
 */

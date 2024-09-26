package com.anubhavauth.bentobackend.entities.persistentEntities;

import com.anubhavauth.bentobackend.ObjectIdSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "reviews")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewEntity {

    @Id
    @JsonSerialize(using = ObjectIdSerializer.class)
    private ObjectId id;

    @JsonSerialize(using = ObjectIdSerializer.class)
    private ObjectId userId;

    @JsonSerialize(using = ObjectIdSerializer.class)
    private ObjectId restaurantId;

    private double rating;
    private String comment;
    private LocalDateTime reviewedAt;
    private LocalDateTime updatedAt;
}

/*
•	Relationships:
o	User: Belongs to one user.
o	Restaurant: Belongs to one restaurant.

 */

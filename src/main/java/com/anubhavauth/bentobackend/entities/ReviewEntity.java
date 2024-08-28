package com.anubhavauth.bentobackend.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "reviews")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewEntity {

    @Id
    private ObjectId id;
    private ObjectId userId;
    private double rating;
    private String comment;
    private LocalDate reviewDate;
}

/*
•	Relationships:
o	User: Belongs to one user.
o	Restaurant: Belongs to one restaurant.

 */

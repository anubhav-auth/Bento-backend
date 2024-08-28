package com.anubhavauth.bentobackend.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDTO {
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

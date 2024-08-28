package com.anubhavauth.bentobackend.entities;

import com.anubhavauth.bentobackend.entities.enums.DishCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenuItemDTO {
    private ObjectId restaurantId;
    private String name;
    private String description;
    private int price;
    private String imageUrl;
    private Boolean availability;
    private DishCategory category;
}
/*
•	Relationships:
o	Restaurant: Belongs to one restaurant.
o	Orders: Can be part of multiple orders.

 */
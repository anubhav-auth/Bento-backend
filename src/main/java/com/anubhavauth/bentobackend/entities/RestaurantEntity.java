package com.anubhavauth.bentobackend.entities;

import com.anubhavauth.bentobackend.entities.enums.Cuisines;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Document(collection = "restaurants")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantEntity {
    @Id
    private ObjectId id;
    private ObjectId ownerId;
    private String name;
    private String address;
    private String phone;
    private String email;
    private List<Cuisines> cuisines;
    private Double rating;
    private List<Map<DayOfWeek, String>> openingHours;
    private List<ObjectId> menuItems;
    private List<ObjectId> orders;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}


/*
•	Relationships:
o	MenuItems: A restaurant has multiple menu items.
o	Orders: A restaurant can have multiple orders placed by customers.

 */

package com.anubhavauth.bentobackend.entities.persistentEntities;

import com.anubhavauth.bentobackend.ObjectIdSerializer;
import com.anubhavauth.bentobackend.entities.enums.Cuisines;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
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
    @JsonSerialize(using = ObjectIdSerializer.class)
    private ObjectId id;

    @JsonSerialize(using = ObjectIdSerializer.class)
    private ObjectId ownerId;

    private String name;
    private Address address;
    private String phone;
    private String email;
    private List<Cuisines> cuisines;
    private Double rating;
    private String imageUrl;
    @JsonSerialize(contentUsing = ObjectIdSerializer.class)
    private List<ObjectId> reviews;

    private List<Map<DayOfWeek, String>> openingHours;

    @JsonSerialize(contentUsing = ObjectIdSerializer.class)
    private List<ObjectId> paymentIds;

    @JsonSerialize(contentUsing = ObjectIdSerializer.class)
    private List<ObjectId> menuItems;

    @JsonSerialize(contentUsing = ObjectIdSerializer.class)
    private List<ObjectId> currentOrders;

    @JsonSerialize(contentUsing = ObjectIdSerializer.class)
    private List<ObjectId> pastOrders;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}


/*
•	Relationships:
o	MenuItems: A restaurant has multiple menu items.
o	Orders: A restaurant can have multiple orders placed by customers.

 */

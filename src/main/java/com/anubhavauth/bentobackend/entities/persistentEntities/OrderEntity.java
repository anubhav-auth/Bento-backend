package com.anubhavauth.bentobackend.entities.persistentEntities;

import com.anubhavauth.bentobackend.ObjectIdSerializer;
import com.anubhavauth.bentobackend.entities.enums.OrderStatus;
import com.anubhavauth.bentobackend.entities.enums.PaymentStatus;
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


@Document(collection = "orders")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderEntity {

    @Id
    @JsonSerialize(using = ObjectIdSerializer.class)
    private ObjectId id;

    @JsonSerialize(using = ObjectIdSerializer.class)
    private ObjectId userId;

    @JsonSerialize(using = ObjectIdSerializer.class)
    private ObjectId restaurantId;

    @JsonSerialize(using = ObjectIdSerializer.class)
    private ObjectId paymentId;

    @JsonSerialize(using = ObjectIdSerializer.class)
    private ObjectId deliveryId;

    private List<ObjectId> items;
    private Double totalPrice;
    private OrderStatus status;
    private String deliveryAddress;
    private PaymentStatus paymentStatus;
    private LocalDateTime placedOn;
    private LocalDateTime updatedOn;
}

/*
•	Relationships:
o	User: Belongs to one user.
o	Restaurant: Belongs to one restaurant.
o	MenuItems: Contains multiple menu items.

 */

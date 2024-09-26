package com.anubhavauth.bentobackend.entities.persistentEntities;

import com.anubhavauth.bentobackend.ObjectIdSerializer;
import com.anubhavauth.bentobackend.entities.enums.DeliveryStatus;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "delivery")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryEntity {
    @Id
    @JsonSerialize(using = ObjectIdSerializer.class)
    private ObjectId id;

    @JsonSerialize(using = ObjectIdSerializer.class)
    private ObjectId orderId;

    @JsonSerialize(using = ObjectIdSerializer.class)
    private ObjectId deliveryPersonnelId;

    @JsonSerialize(using = ObjectIdSerializer.class)
    private ObjectId addressId;

    private DeliveryStatus deliveryStatus;
    private LocalDateTime assignTime;
    private LocalDateTime pickupTime;
    private LocalDateTime deliveryTime;

}
/*
•	Relationships:
o	Order: Belongs to one order.
o	User: Represents one delivery person.

 */

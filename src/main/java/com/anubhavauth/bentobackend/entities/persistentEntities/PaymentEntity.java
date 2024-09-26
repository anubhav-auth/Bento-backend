package com.anubhavauth.bentobackend.entities.persistentEntities;

import com.anubhavauth.bentobackend.ObjectIdSerializer;
import com.anubhavauth.bentobackend.entities.enums.PaymentMethod;
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


@Document(collection = "payments")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentEntity {
    @Id
    @JsonSerialize(using = ObjectIdSerializer.class)
    private ObjectId id;

    @JsonSerialize(using = ObjectIdSerializer.class)
    private ObjectId orderId;

    @JsonSerialize(using = ObjectIdSerializer.class)
    private ObjectId userId;

    @JsonSerialize(using = ObjectIdSerializer.class)
    private ObjectId restaurantId;

    private Double amount;
    private PaymentMethod paymentMethod;
    private String transactionId;
    private PaymentStatus paymentStatus;
    private LocalDateTime paidAt;
}

/*
•	Relationships:
o	Order: Belongs to one order.

 */

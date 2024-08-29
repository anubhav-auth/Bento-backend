package com.anubhavauth.bentobackend.entities.persistentEntities;

import com.anubhavauth.bentobackend.entities.enums.PaymentMethod;
import com.anubhavauth.bentobackend.entities.enums.PaymentStatus;
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
    private ObjectId id;
    private ObjectId orderId;
    private ObjectId userId;
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

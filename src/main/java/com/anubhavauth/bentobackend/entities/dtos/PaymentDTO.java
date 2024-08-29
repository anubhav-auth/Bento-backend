package com.anubhavauth.bentobackend.entities.dtos;

import com.anubhavauth.bentobackend.entities.enums.PaymentMethod;
import com.anubhavauth.bentobackend.entities.enums.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDTO {
    private ObjectId orderId;
    private ObjectId userId;
    private ObjectId restaurantId;
    private Double amount;
    private PaymentMethod paymentMethod;
    private String transactionId;
    private PaymentStatus paymentStatus;
}

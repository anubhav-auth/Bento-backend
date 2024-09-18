package com.anubhavauth.bentobackend.entities.dtos;

import com.anubhavauth.bentobackend.entities.enums.OrderStatus;
import com.anubhavauth.bentobackend.entities.persistentEntities.MenuItemEntity;
import com.anubhavauth.bentobackend.entities.persistentEntities.PaymentEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    private ObjectId userId;
    private ObjectId restaurantId;
    private List<ObjectId> items;
    private Double totalPrice;
    private String deliveryAddress;
    private ObjectId paymentId;
}

package com.anubhavauth.bentobackend.entities.dtos;

import com.anubhavauth.bentobackend.entities.persistentEntities.MenuItemEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    private List<MenuItemEntity> items;
    private String deliveryAddress;
}

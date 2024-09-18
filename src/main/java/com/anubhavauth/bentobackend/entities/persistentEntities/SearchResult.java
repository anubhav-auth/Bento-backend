package com.anubhavauth.bentobackend.entities.persistentEntities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchResult {
    private List<RestaurantEntity> restaurants;
    private List<MenuItemEntity> menuItem;
}

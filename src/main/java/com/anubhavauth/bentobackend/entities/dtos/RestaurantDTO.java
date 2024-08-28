package com.anubhavauth.bentobackend.entities.dtos;


import com.anubhavauth.bentobackend.entities.enums.Cuisines;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Map;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantDTO {
    private ObjectId userId;
    private String name;
    private String address;
    private String phone;
    private String email;
    private List<Cuisines> cuisines;
    private List<Map<DayOfWeek, String>> openingHours;
}

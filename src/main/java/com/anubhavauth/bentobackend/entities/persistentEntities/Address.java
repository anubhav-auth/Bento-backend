package com.anubhavauth.bentobackend.entities.persistentEntities;

import com.anubhavauth.bentobackend.entities.enums.AddressTypes;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Address {
    private String name;
    private String houseFlatNo;
    private String floorNo;
    private String apartmentBuildingNo;
    private String howToReach;
    private String contactNumber;
    private String LatLang;
    private String mapAddress;
    private AddressTypes addressType;
}


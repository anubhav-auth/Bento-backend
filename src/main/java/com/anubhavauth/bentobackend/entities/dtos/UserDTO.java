package com.anubhavauth.bentobackend.entities.dtos;


import com.anubhavauth.bentobackend.entities.persistentEntities.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String name;
    private String email;
    private String password;
    private Long phone;
    private Address address;
    private String profilePicture;
}

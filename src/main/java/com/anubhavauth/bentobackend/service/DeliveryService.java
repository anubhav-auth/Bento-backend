package com.anubhavauth.bentobackend.service;

import com.anubhavauth.bentobackend.entities.enums.Roles;
import com.anubhavauth.bentobackend.entities.persistentEntities.DeliveryEntity;
import com.anubhavauth.bentobackend.entities.persistentEntities.UserEntity;
import com.anubhavauth.bentobackend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryService {

    private final UserRepository userRepository;
    public DeliveryService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserEntity> getAllDeliveryPersonnelAvailable(){
        return userRepository.findByRolesAndOrderDelIsEmpty(List.of(Roles.DELIVERY_PERSONNEL));
    }

}

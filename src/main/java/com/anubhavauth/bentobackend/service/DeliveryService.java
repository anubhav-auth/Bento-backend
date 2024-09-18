package com.anubhavauth.bentobackend.service;

import com.anubhavauth.bentobackend.entities.enums.DeliveryStatus;
import com.anubhavauth.bentobackend.entities.enums.Roles;
import com.anubhavauth.bentobackend.entities.persistentEntities.DeliveryEntity;
import com.anubhavauth.bentobackend.entities.persistentEntities.OrderEntity;
import com.anubhavauth.bentobackend.entities.persistentEntities.RestaurantEntity;
import com.anubhavauth.bentobackend.entities.persistentEntities.UserEntity;
import com.anubhavauth.bentobackend.repository.DeliveryRepository;
import com.anubhavauth.bentobackend.repository.OrderRepository;
import com.anubhavauth.bentobackend.repository.RestaurantRepository;
import com.anubhavauth.bentobackend.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final RestaurantRepository restaurantRepository;
    public DeliveryService(DeliveryRepository deliveryRepository, UserRepository userRepository, OrderRepository orderRepository, RestaurantRepository restaurantRepository) {
        this.deliveryRepository = deliveryRepository;
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
        this.restaurantRepository = restaurantRepository;
    }

    public List<UserEntity> getAllDeliveryPersonnelAvailable(){
        return userRepository.findUsersByRoleAndOrderDelIsEmpty(Roles.DELIVERY_PERSONNEL);
    }

    public void assignDeliveryPersonnel(ObjectId orderId, ObjectId deliveryPersonnelId){
        Optional<UserEntity> userRepositoryById = userRepository.findById(deliveryPersonnelId);
        Optional<OrderEntity> byId = orderRepository.findById(orderId);
        if (userRepositoryById.isPresent() && byId.isPresent()) {
            UserEntity userEntity = userRepositoryById.get();
            userEntity.setOrderDel(orderId);
            userRepository.save(userEntity);
            DeliveryEntity save = deliveryRepository.save(
                    DeliveryEntity.builder()
                            .orderId(orderId)
                            .deliveryPersonnelId(deliveryPersonnelId)
                            .deliveryStatus(DeliveryStatus.ASSIGNED)
                            .assignTime(LocalDateTime.now())
                            .build()
            );
            byId.get().setDeliveryId(save.getId());
            orderRepository.save(byId.get());
        }else {
            throw new RuntimeException("Delivery personnel not found");
        }
    }

    public void updateDeliveryStatus(ObjectId orderId, DeliveryStatus deliveryStatus){

        Optional<OrderEntity> byId = orderRepository.findById(orderId);


        if (byId.isPresent()) {
            Optional<DeliveryEntity> byId1 = deliveryRepository.findById(byId.get().getDeliveryId());
            if (byId1.isPresent()) {
                DeliveryEntity deliveryEntity = byId1.get();
                if (deliveryStatus.equals(DeliveryStatus.AT_RESTAURANT)) {

                    deliveryEntity.setDeliveryStatus(DeliveryStatus.AT_RESTAURANT);

                } else if (deliveryStatus.equals(DeliveryStatus.DELIVERED)) {

                    deliveryEntity.setDeliveryTime(LocalDateTime.now());
                    deliveryEntity.setDeliveryStatus(DeliveryStatus.DELIVERED);
                    Optional<RestaurantEntity> byId2 = restaurantRepository.findById(byId.get().getRestaurantId());
                    byId2.get().getCurrentOrders().remove(byId.get().getId());
                    byId2.get().getPastOrders().add(byId.get().getId());
                    restaurantRepository.save(byId2.get());

                } else if (deliveryStatus.equals(DeliveryStatus.PICKED_UP)) {

                    deliveryEntity.setPickupTime(LocalDateTime.now());
                    deliveryEntity.setDeliveryStatus(DeliveryStatus.PICKED_UP);
                }
                deliveryRepository.save(deliveryEntity);
            }
        }
    }

}

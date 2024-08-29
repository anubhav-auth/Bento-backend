package com.anubhavauth.bentobackend.service;

import com.anubhavauth.bentobackend.entities.persistentEntities.OrderEntity;
import com.anubhavauth.bentobackend.entities.persistentEntities.RestaurantEntity;
import com.anubhavauth.bentobackend.entities.persistentEntities.UserEntity;
import com.anubhavauth.bentobackend.repository.OrderRepository;
import com.anubhavauth.bentobackend.repository.RestaurantRepository;
import com.anubhavauth.bentobackend.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, UserRepository userRepository, RestaurantRepository restaurantRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.restaurantRepository = restaurantRepository;
    }

    public ObjectId createOrder(OrderEntity order) {
        Optional<RestaurantEntity> restaurantByID = restaurantRepository.findById(order.getRestaurantId());
        Optional<UserEntity> userById = userRepository.findById(order.getUserId());

        if (restaurantByID.isPresent() && userById.isPresent()) {
            RestaurantEntity restaurant = restaurantByID.get();
            UserEntity user = userById.get();
            OrderEntity savedOrder = orderRepository.save(order);
            user.getOrderIds().add(savedOrder.getId());
            userRepository.save(user);
            restaurant.getCurrentOrders().add(savedOrder.getId());
            RestaurantEntity save = restaurantRepository.save(restaurant);
            return save.getId();
        }else {
            return null;
        }

    }

    public OrderEntity getOrderById(ObjectId id) {
        Optional<OrderEntity> byId = orderRepository.findById(id);
        return byId.orElse(null);
    }

    public void updateOrder(OrderEntity order) {
        orderRepository.save(order);
    }

}

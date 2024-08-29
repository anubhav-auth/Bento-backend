package com.anubhavauth.bentobackend.service;

import com.anubhavauth.bentobackend.entities.enums.PaymentStatus;
import com.anubhavauth.bentobackend.entities.persistentEntities.PaymentEntity;
import com.anubhavauth.bentobackend.entities.persistentEntities.RestaurantEntity;
import com.anubhavauth.bentobackend.entities.persistentEntities.UserEntity;
import com.anubhavauth.bentobackend.repository.PaymentRepository;
import com.anubhavauth.bentobackend.repository.RestaurantRepository;
import com.anubhavauth.bentobackend.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository, UserRepository userRepository, RestaurantRepository restaurantRepository) {
        this.paymentRepository = paymentRepository;
        this.userRepository = userRepository;
        this.restaurantRepository = restaurantRepository;
    }

    public ObjectId createPayment(PaymentEntity payment) {
        Optional<UserEntity> userRepositoryById = userRepository.findById(payment.getUserId());
        Optional<RestaurantEntity> restaurantRepositoryById = restaurantRepository.findById(payment.getRestaurantId());
        if (userRepositoryById.isPresent() && restaurantRepositoryById.isPresent()) {
            PaymentEntity save = paymentRepository.save(payment);
            userRepositoryById.get().getPaymentIds().add(save.getId());
            restaurantRepositoryById.get().getPaymentIds().add(save.getId());
            restaurantRepository.save(restaurantRepositoryById.get());
            userRepository.save(userRepositoryById.get());
            return save.getId();
        }else {
            return null;
        }
    }

    public void updatePaymentWithObjectId(ObjectId orderId, ObjectId payment) {
        Optional<PaymentEntity> byId = paymentRepository.findById(payment);
        if (byId.isPresent()) {
            PaymentEntity paymentEntity = byId.get();
            paymentEntity.setOrderId(orderId);
            paymentRepository.save(paymentEntity);
        }else {
            throw new RuntimeException("Payment not found");
        }

    }
    public PaymentEntity getPayment(ObjectId paymentId) {
        Optional<PaymentEntity> byId = paymentRepository.findById(paymentId);
        if (byId.isPresent()) {
            return byId.get();
        }else {
            throw new RuntimeException("Payment not found");
        }
    }

    public List<PaymentEntity> getPaymentRestaurant(ObjectId id) {
        Optional<RestaurantEntity> byId = restaurantRepository.findById(id);
        if (byId.isPresent()) {
            RestaurantEntity restaurantEntity = byId.get();
            return paymentRepository.findAllById(restaurantEntity.getPaymentIds());
        }else {
            throw new RuntimeException("Restaurant not found");
        }
    }

    public void updatePaymentStatus(ObjectId paymentId, PaymentStatus status) {
        Optional<PaymentEntity> byId = paymentRepository.findById(paymentId);
        if (byId.isPresent()) {
            PaymentEntity paymentEntity = byId.get();
            paymentEntity.setPaymentStatus(status);
            paymentRepository.save(paymentEntity);
        }else {
            throw new RuntimeException("Payment not found");
        }
    }



}

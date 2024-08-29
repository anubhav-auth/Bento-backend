package com.anubhavauth.bentobackend.service;

import com.anubhavauth.bentobackend.entities.persistentEntities.PromotionEntity;
import com.anubhavauth.bentobackend.repository.PromotionRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PromotionService {
    private final PromotionRepository promotionRepository;
    @Autowired
    public PromotionService(PromotionRepository promotionRepository) {
        this.promotionRepository = promotionRepository;
    }

    public void createPromotion(PromotionEntity promotion) {
        promotionRepository.save(promotion);
    }

    public List<PromotionEntity> getAllPromotions() {
        return promotionRepository.findAll();
    }
    public PromotionEntity getPromotionById(ObjectId id) {
        return promotionRepository.findById(id).orElse(null);
    }
}

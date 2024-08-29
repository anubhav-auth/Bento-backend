package com.anubhavauth.bentobackend.controller;

import com.anubhavauth.bentobackend.entities.persistentEntities.PromotionEntity;
import com.anubhavauth.bentobackend.service.PromotionService;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/promotions")
@Slf4j
public class PromotionController {
    private final PromotionService promotionService;

    @Autowired
    public PromotionController(PromotionService promotionService) {
        this.promotionService = promotionService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createPromotion(PromotionEntity promotion) {
        try{
            promotionService.createPromotion(promotion);
            return new ResponseEntity<>("Promotion created successfully", HttpStatus.CREATED);
        }catch (Exception e){
            log.error("Exception creating promotion {}",e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/all-discounts")
    public ResponseEntity<List<PromotionEntity>> getAvailable() {
        try {
            return new ResponseEntity<>(promotionService.getAllPromotions(), HttpStatus.OK);
        }catch (Exception e){
            log.error("Exception getting available promotions{}",e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("")
    public ResponseEntity<PromotionEntity> getPromotionById(@RequestParam ObjectId promotionId) {
        return new ResponseEntity<>(promotionService.getPromotionById(promotionId), HttpStatus.OK);
    }


}

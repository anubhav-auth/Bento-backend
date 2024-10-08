package com.anubhavauth.bentobackend.entities.persistentEntities;

import com.anubhavauth.bentobackend.ObjectIdSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "promotions")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PromotionEntity {
    @Id
    @JsonSerialize(using = ObjectIdSerializer.class)
    private ObjectId id;

    private String discountCode;
    private String description;
    private Double discountPercentage;
    private LocalDateTime validFrom;
    private LocalDateTime validUntil;
}
/*
•	Relationships:
o	Orders: Can be applied to multiple orders.
 */

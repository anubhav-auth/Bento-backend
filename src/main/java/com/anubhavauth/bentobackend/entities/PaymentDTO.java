package com.anubhavauth.bentobackend.entities;

import com.anubhavauth.bentobackend.entities.enums.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDTO {
    private PaymentMethod paymentMethod;
}

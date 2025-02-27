package com.imavazq.ecommerce.domain.dto;

import com.imavazq.ecommerce.domain.enums.PaymentMethod;

import java.math.BigDecimal;

public record OrderResponseDTO(
        Integer id,
        String reference,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        String customerId
) {
}

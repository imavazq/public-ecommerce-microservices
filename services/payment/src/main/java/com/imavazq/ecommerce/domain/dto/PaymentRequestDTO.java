package com.imavazq.ecommerce.domain.dto;

import com.imavazq.ecommerce.domain.enums.PaymentMethod;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record PaymentRequestDTO(
        Integer id,

        @Positive(message = "Payment amount should be positive.")
        BigDecimal amount,

        @NotNull(message = "Payment method is required.")
        PaymentMethod paymentMethod,

        @NotEmpty(message = "Order is required.")
        Integer orderId,

        String orderReference,

        @NotEmpty(message = "Customer is required.")
        Customer customer
) {
}

package com.imavazq.ecommerce.domain.dto;

import com.imavazq.ecommerce.domain.enums.PaymentMethod;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.List;

public record OrderRequestDTO(
        Integer id,
        String reference,

        @Positive(message = "Order amount should be positive.")
        BigDecimal amount,

        @NotNull(message = "Payment method is required.")
        PaymentMethod paymentMethod,

        //@NotNull(message = "Customer is required.")
        @NotEmpty(message = "Customer is required.")
        //@NotBlank(message = "Customer is required.")
        String customerId,

        @NotEmpty(message = "At least one product should be purchased")
        List<PurchasedProductRequestDTO> products
) {
}

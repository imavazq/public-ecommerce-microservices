package com.imavazq.ecommerce.domain.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record PurchasedProductRequestDTO(
        @NotNull(message = "Product is mandatory")
        Integer productId,
        @NotNull(message = "Quantity is mandatory")
        @Positive(message = "Quantity must be positive")
        double quantity
) {
}

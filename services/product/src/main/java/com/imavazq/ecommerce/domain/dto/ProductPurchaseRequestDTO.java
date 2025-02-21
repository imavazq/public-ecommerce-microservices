package com.imavazq.ecommerce.domain.dto;

import jakarta.validation.constraints.NotNull;

public record ProductPurchaseRequestDTO(
        @NotNull(message = "Product is mandatory")
        Integer productId,
        @NotNull(message = "Quantity is mandatory")
        double quantity
) {
}

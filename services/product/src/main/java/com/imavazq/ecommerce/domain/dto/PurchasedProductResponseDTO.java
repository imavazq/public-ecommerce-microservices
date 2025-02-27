package com.imavazq.ecommerce.domain.dto;

import java.math.BigDecimal;

public record PurchasedProductResponseDTO(
        Integer productId,
        String name,
        String description,
        BigDecimal price,
        double quantity
) {
}

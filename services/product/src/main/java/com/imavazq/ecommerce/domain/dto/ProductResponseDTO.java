package com.imavazq.ecommerce.domain.dto;

import java.math.BigDecimal;

public record ProductResponseDTO(
        Integer id,
        String name,
        String description,
        double availableQuantity,
        BigDecimal price,
        Integer categoryId,

        //campos category extra que devolvemos
        String categoryName,
        String categoryDescription
) {
}

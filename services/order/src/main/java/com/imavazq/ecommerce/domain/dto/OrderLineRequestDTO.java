package com.imavazq.ecommerce.domain.dto;

public record OrderLineRequestDTO(
        Integer id,
        Integer orderId,
        Integer productId,
        double quantity
) {
}

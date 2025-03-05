package com.imavazq.ecommerce.kafka.order;

import java.math.BigDecimal;

public record PurchasedProduct(
        Integer productId,
        String name,
        String description,
        BigDecimal price,
        double quantity
) {
}

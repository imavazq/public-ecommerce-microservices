package com.imavazq.ecommerce.kafka.order;

import com.imavazq.ecommerce.kafka.payment.PaymentMethod;

import java.math.BigDecimal;
import java.util.List;

//tipo de dato consumido de kafka broker topic
public record OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        Customer customer,
        List<PurchasedProduct> products
) {
}

package com.imavazq.ecommerce.kafka.payment;

import java.math.BigDecimal;

//PaymentConfirmation tiene mismos campos que los que se enviaron desde PaymentProducer en Payment-ms
//tipo de dato consumido de kafka broker topic
public record PaymentConfirmation(
        String orderReference,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        String customerFirstName,
        String customerLastName,
        String customerEmail
) {
}

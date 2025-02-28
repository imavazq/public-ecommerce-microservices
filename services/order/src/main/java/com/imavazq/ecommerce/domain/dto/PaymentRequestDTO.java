package com.imavazq.ecommerce.domain.dto;

import com.imavazq.ecommerce.domain.enums.PaymentMethod;
import java.math.BigDecimal;

public record PaymentRequestDTO( //mismos campos que PaymentRequestDTO de Payment-ms
     BigDecimal amount,

     PaymentMethod paymentMethod,

     Integer orderId,

     String orderReference,

     CustomerResponseDTO customer //aprovechamos CustomerResponseDTO creado antes
) {
}

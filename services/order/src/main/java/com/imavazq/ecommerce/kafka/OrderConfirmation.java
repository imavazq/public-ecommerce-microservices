package com.imavazq.ecommerce.kafka;

import com.imavazq.ecommerce.domain.dto.CustomerResponseDTO;
import com.imavazq.ecommerce.domain.enums.PaymentMethod;
import com.imavazq.ecommerce.domain.dto.PurchasedProductResponseDTO;

import java.math.BigDecimal;
import java.util.List;

//info a enviar a kafka broker (para que sea consumido por Notification service)
public record OrderConfirmation(
        //informacion que necesita Notification service para enviar el mail de confirmacion
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponseDTO customer, //info del customer
        List<PurchasedProductResponseDTO> products
) {
}

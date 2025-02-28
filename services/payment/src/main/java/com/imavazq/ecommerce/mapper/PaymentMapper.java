package com.imavazq.ecommerce.mapper;

import com.imavazq.ecommerce.domain.dto.PaymentRequestDTO;
import com.imavazq.ecommerce.domain.entity.Payment;
import org.springframework.stereotype.Component;

@Component
public class PaymentMapper {
    public Payment toPayment(PaymentRequestDTO paymentRequestDTO) {
        return Payment.builder()
                .id(paymentRequestDTO.id())
                .amount(paymentRequestDTO.amount())
                .paymentMethod(paymentRequestDTO.paymentMethod())
                .orderId(paymentRequestDTO.orderId())
                .build();
    }
}

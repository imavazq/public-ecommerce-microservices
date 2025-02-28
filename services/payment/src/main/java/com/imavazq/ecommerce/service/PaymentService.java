package com.imavazq.ecommerce.service;

import com.imavazq.ecommerce.domain.dto.PaymentRequestDTO;
import com.imavazq.ecommerce.domain.entity.Payment;
import com.imavazq.ecommerce.kafka.PaymentConfirmation;
import com.imavazq.ecommerce.kafka.PaymentProducer;
import com.imavazq.ecommerce.mapper.PaymentMapper;
import com.imavazq.ecommerce.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;
    private final PaymentProducer paymentProducer;

    public Integer createPayment(PaymentRequestDTO paymentRequestDTO) {
        Payment payment = paymentRepository.save(paymentMapper.toPayment(paymentRequestDTO));

        //enviamos notificacion a Notification microservice usando Kafka
        paymentProducer.sendPaymentConfirmation(
                new PaymentConfirmation( //creamos mensaje a notificar con sus datos
                        paymentRequestDTO.orderReference(),
                        paymentRequestDTO.amount(),
                        paymentRequestDTO.paymentMethod(),
                        paymentRequestDTO.customer().firstName(),
                        paymentRequestDTO.customer().lastName(),
                        paymentRequestDTO.customer().email()
                )
        );

        return payment.getId();
    }
}

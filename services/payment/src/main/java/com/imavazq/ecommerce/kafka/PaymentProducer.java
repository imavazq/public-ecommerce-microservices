package com.imavazq.ecommerce.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j //logging
public class PaymentProducer {
    private final KafkaTemplate<String, PaymentConfirmation> kafkaTemplate;

    @Bean
    public void sendPaymentConfirmation(PaymentConfirmation paymentConfirmation){
        log.info("Sending payment confirmation with body <{}>" + paymentConfirmation);

        //preparamos mensaje a enviar a broker
        Message<PaymentConfirmation> message = MessageBuilder
                .withPayload(paymentConfirmation)
                .setHeader(KafkaHeaders.TOPIC, "payment-topic") //especificamos Topic al que enviamos mensaje
                .build();

        //enviamos mensaje a kafka broker
        kafkaTemplate.send(message);
    }
}

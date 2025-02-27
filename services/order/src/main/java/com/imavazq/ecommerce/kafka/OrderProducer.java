package com.imavazq.ecommerce.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j //logging
public class OrderProducer {
    //K tipo String para que se aplique Hash y que mensajes con misma clave vayan a la misma particion
    private final KafkaTemplate<String, OrderConfirmation> kafkaTemplate;

    //enviar mensaje a topic
    public void sendOrderConfirmation(OrderConfirmation orderConfirmation){
        log.info("Sending order confirmation...");
        //preparamos mensaje a enviar a broker
        Message<OrderConfirmation> message = MessageBuilder
                .withPayload(orderConfirmation)
                .setHeader(KafkaHeaders.TOPIC, "order-topic") //especificamos Topic al que enviamos mensaje
                .build();

        //enviamos mensaje a kafka broker
        kafkaTemplate.send(message);
    }
}

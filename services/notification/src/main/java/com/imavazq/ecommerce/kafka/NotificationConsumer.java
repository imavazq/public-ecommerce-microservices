package com.imavazq.ecommerce.kafka;

import com.imavazq.ecommerce.email.EmailService;
import com.imavazq.ecommerce.kafka.order.OrderConfirmation;
import com.imavazq.ecommerce.kafka.payment.PaymentConfirmation;
import com.imavazq.ecommerce.notification.Notification;
import com.imavazq.ecommerce.notification.NotificationRepository;
import com.imavazq.ecommerce.notification.NotificationType;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service //en este caso uso etiqueta Service y no Component xq implementa lógica de negocio de persistencia y envío de mail
@RequiredArgsConstructor
@Slf4j //logging
public class NotificationConsumer {
    private final NotificationRepository notificationRepository;
    private final EmailService emailService;

    @KafkaListener(topics = "order-topic") //consumimos order-topic definido en order-ms
    public void consumeOrderConfirmationNotification(OrderConfirmation orderConfirmation) throws MessagingException {
        log.info("Consuming the message from order-topic Topic: {}", orderConfirmation);

        //persistimos notificacion consumida
        notificationRepository.save(
                Notification.builder()
                        .notificationType(NotificationType.ORDER_CONFIRMATION)
                        .notificationDate(LocalDateTime.now())
                        .orderConfirmation(orderConfirmation)
                        .build()
        );

        //enviamos email
        String customerName = orderConfirmation.customer().firstName() + " " + orderConfirmation.customer().lastName();
        emailService.sendOrderConfirmationEmail( //throws MessagingException
                orderConfirmation.customer().email(),
                customerName,
                orderConfirmation.totalAmount(),
                orderConfirmation.orderReference(),
                orderConfirmation.purchasedProducts()
        );
    }

    @KafkaListener(topics = "payment-topic") //consumimos payment-topic definido en Payment-ms
    public void consumePaymentConfirmationNotification(PaymentConfirmation paymentConfirmation) throws MessagingException {
        log.info("Consuming the message from payment-topic Topic: {}", paymentConfirmation);

        //persistimos notificacion consumida
        notificationRepository.save(
                Notification.builder()
                        .notificationType(NotificationType.PAYMENT_CONFIRMATION)
                        .notificationDate(LocalDateTime.now())
                        .paymentConfirmation(paymentConfirmation)
                        .build()
        );

        //enviamos email
        String customerName = paymentConfirmation.customerFirstName() + " " + paymentConfirmation.customerLastName();

        emailService.sendPaymentConfirmationEmail( //throws MessagingException
                paymentConfirmation.customerEmail(),
                customerName,
                paymentConfirmation.amount(),
                paymentConfirmation.orderReference()
        );
    }
}

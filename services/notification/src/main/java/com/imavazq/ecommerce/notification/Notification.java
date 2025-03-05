package com.imavazq.ecommerce.notification;

import com.imavazq.ecommerce.kafka.order.OrderConfirmation;
import com.imavazq.ecommerce.kafka.payment.PaymentConfirmation;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Document //objeto documento a almacenar en bd
public class Notification {
    @Id
    private String id;

    private NotificationType notificationType; //order o payment

    private LocalDateTime notificationDate;

    private OrderConfirmation orderConfirmation;

    private PaymentConfirmation paymentConfirmation;
}

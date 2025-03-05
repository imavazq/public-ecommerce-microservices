package com.imavazq.ecommerce.email;

import lombok.Getter;


public enum EmailTemplates {
    PAYMENT_CONFIRMATION("payment-confirmation.html", "Payment successfully processed"),//nombre template, asunto mail
    ORDER_CONFIRMATION("order-confirmation.html", "Order confirmation");

    //declaramos parametros utilizados para los ENUM values
    @Getter
    private final String template;
    @Getter
    private final String subject;

    EmailTemplates(String template, String subject) {
        this.template = template;
        this.subject = subject;
    }
}

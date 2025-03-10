package com.imavazq.ecommerce.email;

import com.imavazq.ecommerce.kafka.order.PurchasedProduct;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {
    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;

    @Async
    public void sendOrderConfirmationEmail(
            String destinationEmail,
            String customerName,
            BigDecimal amount,
            String orderReference,
            List<PurchasedProduct> purchasedProducts
    ) throws MessagingException { //MimeMessageHelper puede lanzar excepcion
        MimeMessage mimeMessage = mailSender.createMimeMessage(); //clase Java Mail para representar mensaje email
        MimeMessageHelper messageHelper = //configuracion de mensaje
                new MimeMessageHelper(
                        mimeMessage,
                        MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, //para indicar que msg tiene multiples partes (ej: texto y archivos)
                        StandardCharsets.UTF_8.name() //codificacion msg
                );
        messageHelper.setFrom("vazquezimanol2000@gmail.com"); //remitente

        final String templateName = EmailTemplates.ORDER_CONFIRMATION.getTemplate(); //nos quedamos con nombre de plantilla 'order-confirmation.html'

        Map<String, Object> variables = new HashMap<>(); //variables que se usan para rellenar plantilla HTML
        variables.put("customerName", customerName);
        variables.put("totalAmount", amount);
        variables.put("orderReference", orderReference);
        variables.put("products", purchasedProducts);

        Context context = new Context(); //clase Thymeleaf que contiene variables que se usan en la plantilla
        context.setVariables(variables);
        messageHelper.setSubject(EmailTemplates.ORDER_CONFIRMATION.getSubject()); //setteamos subject de correo

        try{
            //Thymeleaf procesa template 'payment-confirmation.html' y reemplaza variables con valores asignados
            String htmlTemplate = templateEngine.process(templateName, context);
            messageHelper.setText(htmlTemplate, true); //definimos contenido del email; true indica que es contenido html

            messageHelper.setTo(destinationEmail); //destinatario
            mailSender.send(mimeMessage); //enviamos correo

            log.info("INFO: Email successfully sent to {} with template {}", destinationEmail, templateName);
        } catch(MessagingException exc){
            log.warn("WARNING: Cannot send email to {}", destinationEmail);
        }
    }

    @Async
    public void sendPaymentConfirmationEmail(
            String destinationEmail,
            String customerName,
            BigDecimal amount,
            String orderReference
    ) throws MessagingException { //MimeMessageHelper puede lanzar excepcion
        MimeMessage mimeMessage = mailSender.createMimeMessage(); //clase Java Mail para representar mensaje email
        MimeMessageHelper messageHelper = //configuracion de mensaje
                new MimeMessageHelper(
                        mimeMessage,
                        MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, //para indicar que msg tiene multiples partes (ej: texto y archivos)
                        StandardCharsets.UTF_8.name() //codificacion msg
                );
        messageHelper.setFrom("vazquezimanol2000@gmail.com"); //remitente

        final String templateName = EmailTemplates.PAYMENT_CONFIRMATION.getTemplate(); //nos quedamos con nombre de plantilla 'payment-confirmation.html'

        Map<String, Object> variables = new HashMap<>(); //variables que se usan para rellenar plantilla HTML
        variables.put("customerName", customerName);
        variables.put("amount", amount);
        variables.put("orderReference", orderReference);

        Context context = new Context(); //clase Thymeleaf que contiene variables que se usan en la plantilla
        context.setVariables(variables);
        messageHelper.setSubject(EmailTemplates.PAYMENT_CONFIRMATION.getSubject()); //setteamos subject de correo

        try{
            //Thymeleaf procesa template 'payment-confirmation.html' y reemplaza variables con valores asignados
            String htmlTemplate = templateEngine.process(templateName, context);
            messageHelper.setText(htmlTemplate, true); //definimos contenido del email; true indica que es contenido html

            messageHelper.setTo(destinationEmail); //destinatario
            mailSender.send(mimeMessage); //enviamos correo

            log.info("INFO: Email successfully sent to {} with template {}", destinationEmail, templateName);
        } catch(MessagingException exc){
            log.warn("WARNING: Cannot send email to {}", destinationEmail);
        }
    }
}

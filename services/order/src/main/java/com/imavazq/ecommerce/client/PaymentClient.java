package com.imavazq.ecommerce.client;

import com.imavazq.ecommerce.domain.dto.PaymentRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

//Interfaz OpenFeign para consumir API REST Payment
@FeignClient(
        name = "PAYMENT-SERVICE",
        url = "${application.config.payment-url}" //usamos url establecido en config-server order-service.yml
)
public interface PaymentClient {
    @PostMapping //hacemos referencia a endpoint createPayment
    Integer requestOrderPayment(@RequestBody PaymentRequestDTO paymentRequestDTO);
}

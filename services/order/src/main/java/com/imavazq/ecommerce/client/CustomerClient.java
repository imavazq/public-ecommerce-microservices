package com.imavazq.ecommerce.client;

import com.imavazq.ecommerce.domain.dto.CustomerResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

//Interfaz OpenFeign para consumir API REST Customer
//La definimos para poder consumir sus endpoints
//Feign se encarga de la lógica para la llamada al endpoint
@FeignClient(
        name = "CUSTOMER-SERVICE",
        url = "${application.config.customer-url}" //usamos url establecido en config-server
)
public interface CustomerClient {
    @GetMapping("/{id}") //consumimos endpoint de customer service (findById)
    Optional<CustomerResponseDTO> findCustomerById(@PathVariable("id") String customerId);
}

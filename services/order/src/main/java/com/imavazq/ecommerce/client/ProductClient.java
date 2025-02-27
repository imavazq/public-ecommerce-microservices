package com.imavazq.ecommerce.client;

import com.imavazq.ecommerce.exception.BusinessException;
import com.imavazq.ecommerce.domain.dto.PurchasedProductRequestDTO;
import com.imavazq.ecommerce.domain.dto.PurchasedProductResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static jakarta.ws.rs.core.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Component
@RequiredArgsConstructor
public class ProductClient {
    @Value("${application.config.product-url}") //inyectamos url definida en config importada de config-server
    private String productUrl;

    private final RestTemplate restTemplate; //inyectamos bean definida en archivo RestTemplateConfig

    public List<PurchasedProductResponseDTO> purchaseProducts(List<PurchasedProductRequestDTO> purchasedProductsRequest){
        //configuramos headers para solicitud HTTP a hacer al product-ms
        //(en un futuro, si se agrega seguridad en product-ms, se podría agregar Token JWT)
        HttpHeaders headers = new HttpHeaders();
        headers.set(CONTENT_TYPE, APPLICATION_JSON_VALUE);//body request tipo JSON

        //creamos entidad request HTTP con lista de productos comprados y headers definidos
        HttpEntity<List<PurchasedProductRequestDTO>> requestEntity = new HttpEntity<>(purchasedProductsRequest, headers);

        //definimos tipo de response esperada tipo List
        ParameterizedTypeReference<List<PurchasedProductResponseDTO>> responseType =
                new ParameterizedTypeReference<List<PurchasedProductResponseDTO>>() {};

        //hacemos solicitud HTTP POST /purchase a product-ms y la almacenamos
        ResponseEntity<List<PurchasedProductResponseDTO>> responseEntity = restTemplate.exchange(
                productUrl + "/purchase", //URL endpoint
                HttpMethod.POST, //metodo HTTP
                requestEntity,
                responseType //tipo respuesta esperada
        );

        if(responseEntity.getStatusCode().isError()) { //verificamos si http status code es de tipo 4XX o 5XX
            throw new BusinessException("An error occurred while processing the products purchase: " + responseEntity.getStatusCode());
        }

        //devolvemos response (lista de productos comprados)
        return responseEntity.getBody();
    }
}

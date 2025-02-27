package com.imavazq.ecommerce.controller;

import com.imavazq.ecommerce.domain.dto.OrderLineResponseDTO;
import com.imavazq.ecommerce.service.IOrderLineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/order-lines")
public class OrderLineController {
    private final IOrderLineService orderLineService;

    //Endpoint para obtener todas las orderLines de una Order específica
    @GetMapping("/order/{order-id}")
    public ResponseEntity<List<OrderLineResponseDTO>> findAllByOrderId(
            @PathVariable("order-id") Integer orderId
    ){
        return new ResponseEntity<>(orderLineService.findAllByOrderId(orderId), HttpStatus.OK);
    }
}

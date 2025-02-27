package com.imavazq.ecommerce.controller;

import com.imavazq.ecommerce.domain.dto.OrderRequestDTO;
import com.imavazq.ecommerce.domain.dto.OrderResponseDTO;
import com.imavazq.ecommerce.service.IOrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
@RestController
public class OrderController {
    private final IOrderService orderService;

    @PostMapping //retornamos unicamente el id
    public ResponseEntity<Integer> createOrder(
            @RequestBody @Valid OrderRequestDTO orderRequestDTO
    ){
        return new ResponseEntity<>(orderService.createOrder(orderRequestDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<OrderResponseDTO>> findAll(){
        return new ResponseEntity<>(orderService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDTO> findById(
            @PathVariable("id") Integer id
    ){
        return new ResponseEntity<>(orderService.findById(id), HttpStatus.OK);
    }
}

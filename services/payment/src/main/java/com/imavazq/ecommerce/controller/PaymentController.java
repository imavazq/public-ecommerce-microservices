package com.imavazq.ecommerce.controller;

import com.imavazq.ecommerce.domain.dto.PaymentRequestDTO;
import com.imavazq.ecommerce.service.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/payments")
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping
    public ResponseEntity<Integer> createPayment(
            @RequestBody @Valid PaymentRequestDTO paymentRequestDTO
    ){
        return new ResponseEntity<>(paymentService.createPayment(paymentRequestDTO), HttpStatus.CREATED);
    }
}

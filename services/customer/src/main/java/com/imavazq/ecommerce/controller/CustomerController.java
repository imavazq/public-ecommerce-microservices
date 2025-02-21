package com.imavazq.ecommerce.controller;

import com.imavazq.ecommerce.domain.dto.CustomerRequestDTO;
import com.imavazq.ecommerce.domain.dto.CustomerResponseDTO;
import com.imavazq.ecommerce.service.ICustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor //Genera constructor para atr definidos como final
@RequestMapping("/api/v1/customer")
public class CustomerController {
    private final ICustomerService customerService;

    @PostMapping //retornamos unicamente el id
    public ResponseEntity<String> createCustomer(
            @RequestBody @Valid CustomerRequestDTO customerRequestDTO
    ) {
        return new ResponseEntity<>(customerService.createCustomer(customerRequestDTO), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Void> updateCustomer(
            @RequestBody @Valid CustomerRequestDTO customerRequestDTO
    ){
        customerService.updateCustomer(customerRequestDTO);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponseDTO>> findAll(){
        return new ResponseEntity<>(customerService.findAllCustomers(), HttpStatus.OK);
    }

    @GetMapping("/exists/{id}")
    public ResponseEntity<Boolean> existsById(
            @PathVariable("id") String id
    ){
        return new ResponseEntity<>(customerService.existsById(id), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponseDTO> findById(
            @PathVariable("id") String id
    ){
        return new ResponseEntity<>(customerService.findById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(
            @PathVariable("id") String id
    ){
        customerService.deleteCustomer(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

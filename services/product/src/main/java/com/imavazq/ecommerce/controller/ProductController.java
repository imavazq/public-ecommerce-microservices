package com.imavazq.ecommerce.controller;

import com.imavazq.ecommerce.domain.dto.PurchasedProductRequestDTO;
import com.imavazq.ecommerce.domain.dto.PurchasedProductResponseDTO;
import com.imavazq.ecommerce.domain.dto.ProductRequestDTO;
import com.imavazq.ecommerce.domain.dto.ProductResponseDTO;
import com.imavazq.ecommerce.service.IProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor //Genera constructor para atr definidos como final
@RequestMapping("/api/v1/products")
public class ProductController {
    private final IProductService productService;

    @PostMapping //retornamos unicamente el id
    public ResponseEntity<Integer> createProduct(
            @RequestBody @Valid ProductRequestDTO productRequestDTO
    ){
        return new ResponseEntity<>(productService.createProduct(productRequestDTO), HttpStatus.CREATED);
    }

    @PostMapping("/purchase") //compra lista de productos
    public ResponseEntity<List<PurchasedProductResponseDTO>> purchaseProducts(
            @RequestBody List<PurchasedProductRequestDTO> requestedList
    ){
        return new ResponseEntity<>(productService.purchaseProducts(requestedList), HttpStatus.CREATED); //ver si paso http status 201
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> findById(
            @PathVariable("id") Integer id
    ){
        return new ResponseEntity<>(productService.findById(id), HttpStatus.OK);
    }

    @GetMapping //TODO: Agregar paginación
    public ResponseEntity<List<ProductResponseDTO>> findAll(){
        return new ResponseEntity<>(productService.findAllProducts(), HttpStatus.OK);
    }
}


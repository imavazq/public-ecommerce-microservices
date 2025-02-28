package com.imavazq.ecommerce.domain.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

@Validated //para que valide campos de Customer en cadena tambien
public record Customer(
        String id, //id es string para customer (mongoSQL)

        @NotNull(message = "First name is required")
        String firstName,

        @NotNull(message = "Last name is required")
        String lastName,

        @NotNull(message = "Email is required")
        @Email(message = "The customer's mail format is not valid")
        String email
) {
}

package com.imavazq.ecommerce.domain.dto;

import com.imavazq.ecommerce.domain.entity.Address;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record CustomerRequestDTO(
        String id,

        @NotNull(message = "Customer's first name is required")
        String firstName,

        @NotNull(message = "Customer's last name is required")
        String lastName,

        @NotNull(message = "Customer's email is required")
        @Email(message = "Customer's email is not a valid email address")
        String email,

        Address address
) {

}

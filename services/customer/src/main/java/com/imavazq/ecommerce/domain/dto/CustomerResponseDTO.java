package com.imavazq.ecommerce.domain.dto;

import com.imavazq.ecommerce.domain.entity.Address;

public record CustomerResponseDTO(
        String id,

        String firstName,

        String lastName,

        String email,

        Address address
) {
}

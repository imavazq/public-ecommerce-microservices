package com.imavazq.ecommerce.domain.dto;

public record CustomerResponseDTO(
        String id,
        String firstName,
        String lastName,
        String email //email del customer a enviar para notificar la orden
) {
}

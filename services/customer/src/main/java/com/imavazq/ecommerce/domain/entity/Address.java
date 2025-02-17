package com.imavazq.ecommerce.domain.entity;

import lombok.*;
import org.springframework.validation.annotation.Validated;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Validated //Para validar atributos
public class Address {
    private String street;
    private String houseNumber;
    private String zipCode;
}

package com.imavazq.ecommerce.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)//Llamamos solo a la superclase
@Data
public class ProductNotFoundException extends RuntimeException {
    private final String msg;
}

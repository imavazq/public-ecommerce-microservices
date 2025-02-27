package com.imavazq.ecommerce.exception.handler;

import java.util.Map;

//Clase para especificar los argumentos que no cumplen con validación
public record ErrorResponse(
        //Campo, Mensaje a mostrar por error en ese campo
        Map<String, String> errors
) {
}

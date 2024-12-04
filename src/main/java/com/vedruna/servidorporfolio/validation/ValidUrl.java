package com.vedruna.servidorporfolio.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UrlValidator.class) 
public @interface ValidUrl {
    /**
     * Mensaje de error que se muestra cuando la URL no cumple con el formato esperado.
     * 
     * @return mensaje de error.
     */
    String message() default "Formato de URL inválido"; 

    /**
     * Permite agrupar varias restricciones de validación.
     * 
     * @return los grupos de validación.
     */
    Class<?>[] groups() default {}; 

    /**
     * Permite asociar metadatos adicionales a la anotación.
     * 
     * @return los metadatos adicionales.
     */
    Class<? extends Payload>[] payload() default {}; 
}

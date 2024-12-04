package com.vedruna.servidorporfolio.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class UrlValidator implements ConstraintValidator<ValidUrl, String> {

    private static final String URL_REGEX = "^(http|https)://[a-zA-Z0-9-_.]+(?:\\.[a-zA-Z]{2,})+(?:/[\\w-._~:/?#[\\]@!$&'()*+,;=.]+)*$";

    /**
     * Inicializa el validador en preparación para que se llame al método {@link #isValid(Object, ConstraintValidatorContext)}.
     * <p>
     * La anotación de restricción para la restricción que se está validando, por ejemplo {@link NotNull}.
     *
     * @param constraintAnnotation instancia de la anotación que contiene los atributos de la anotación.
     */
    @Override
    public void initialize(ValidUrl constraintAnnotation) {
    }


    /**
     * Evalúa el formato de la URL.
     *
     * @param value   la URL que se va a validar
     * @param context el contexto en el que se evalúa la restricción
     * @return false si la URL no es válida, true en caso contrario
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty()) {
            return true;
        }
        return Pattern.matches(URL_REGEX, value);
    }
}

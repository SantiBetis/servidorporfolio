package com.vedruna.servidorporfolio.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.vedruna.servidorporfolio.dto.ResponseDTO;

// Anotación para capturar y manejar excepciones a nivel de controlador.
@RestControllerAdvice
public class ExceptionController {

    // Manejo de excepciones específicas: Recurso no encontrado (IllegalArgumentException)
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ResponseDTO<String>> handleIllegalArgumentException(IllegalArgumentException ex) {
        // Se crea un ResponseDTO con el mensaje de error y detalles de la excepción.
        ResponseDTO<String> response = new ResponseDTO<>("Solicitud no válida", ex.getMessage());
        
        // Se retorna una respuesta HTTP con estado BAD_REQUEST (400) y el cuerpo con el mensaje de error.
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response); 
    }

    // Manejo de excepciones generales: Error interno del servidor (Exception)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDTO<String>> handleGeneralException(Exception ex) {
        // Se crea un ResponseDTO con el mensaje de error genérico y una descripción más detallada.
        ResponseDTO<String> response = new ResponseDTO<>("Error interno del servidor", 
                "Error. Verifique que la ruta sea correcta, que el recurso no exista ya en la base de datos, y vuelva a intentarlo.");
        
        // Se retorna una respuesta HTTP con estado INTERNAL_SERVER_ERROR (500) y el cuerpo con el mensaje de error.
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}

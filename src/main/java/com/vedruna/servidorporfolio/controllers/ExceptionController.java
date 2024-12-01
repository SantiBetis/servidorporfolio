package com.vedruna.servidorporfolio.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.vedruna.servidorporfolio.dto.ResponseDTO;

@RestControllerAdvice
public class ExceptionController {

    // Manejo de excepciones específicas: Recurso no encontrado
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ResponseDTO<String>> handleIllegalArgumentException(IllegalArgumentException ex) {
        ResponseDTO<String> response = new ResponseDTO<>("Solicitud no válida", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response); 
    }

    // Manejo de excepciones específicas: Solicitud incorrecta
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDTO<String>> handleGeneralException(Exception ex) {
        ResponseDTO<String> response = new ResponseDTO<>("Error interno del servidor", "Error. Verifique que la ruta sea correcta, que el recurso no exista ya en la base de datos, y vuelva a intentarlo.");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}

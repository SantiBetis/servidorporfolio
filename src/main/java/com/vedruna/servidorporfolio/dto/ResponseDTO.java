package com.vedruna.servidorporfolio.dto;

// Definición de la clase ResponseDTO, que es una clase genérica que se utiliza para enviar respuestas estandarizadas en la API
public class ResponseDTO<T> {

    // Atributo que contiene el mensaje de la respuesta (por ejemplo, éxito o error)
    private String message;

    // Atributo que contiene los datos que se devolverán en la respuesta (genérico para adaptarse a cualquier tipo de dato)
    private T data;

    // Constructor que permite inicializar la clase con un mensaje y los datos
    public ResponseDTO(String message, T data) {
        this.message = message; // Establece el mensaje
        this.data = data;       // Establece los datos
    }

    // Getter para el mensaje
    public String getMessage() {
        return message;
    }

    // Setter para el mensaje
    public void setMessage(String message) {
        this.message = message;
    }

    // Getter para los datos
    public T getData() {
        return data;
    }

    // Setter para los datos
    public void setData(T data) {
        this.data = data;
    }
}

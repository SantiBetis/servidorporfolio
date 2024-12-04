package com.vedruna.servidorporfolio.dto;

import com.vedruna.servidorporfolio.persistance.model.Developer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeveloperDTO {

    // Atributos que representan la información básica del desarrollador.
    private int id;              // ID único del desarrollador.
    private String name;         // Nombre del desarrollador.
    private String surname;      // Apellido del desarrollador.
    private String email;        // Correo electrónico del desarrollador.
    private String linkedin_url; // URL del perfil de LinkedIn del desarrollador.
    private String github_url;   // URL del perfil de GitHub del desarrollador.

    /**
     * Constructor que convierte un objeto Developer en un DTO (Data Transfer Object).
     * 
     * @param d el objeto Developer que se va a convertir en un DeveloperDTO.
     */
    public DeveloperDTO(Developer d) {
        this.id = d.getId();                // Asigna el ID del Developer al DTO.
        this.name = d.getName();            // Asigna el nombre del Developer al DTO.
        this.surname = d.getSurname();      // Asigna el apellido del Developer al DTO.
        this.email = d.getEmail();          // Asigna el email del Developer al DTO.
        this.linkedin_url = d.getLinkedin_url(); // Asigna la URL de LinkedIn al DTO.
        this.github_url = d.getGithub_url();     // Asigna la URL de GitHub al DTO.
    }
}

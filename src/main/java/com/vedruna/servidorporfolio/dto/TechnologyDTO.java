package com.vedruna.servidorporfolio.dto;

import com.vedruna.servidorporfolio.persistance.model.Technology;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TechnologyDTO {

    // Atributos que representan los datos esenciales de una tecnología.
    private int id;             // ID único de la tecnología.
    private String name;        // Nombre de la tecnología (por ejemplo, "Java", "React").

    /**
     * Constructor que convierte un objeto Technology a un DTO (Data Transfer Object).
     * 
     * @param t el objeto Technology que se va a convertir a TechnologyDTO.
     */
    public TechnologyDTO(Technology t) {
        this.id = t.getId();    // Asigna el ID de la tecnología al DTO.
        this.name = t.getName(); // Asigna el nombre de la tecnología al DTO.
    }
}

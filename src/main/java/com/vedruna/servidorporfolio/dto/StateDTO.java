package com.vedruna.servidorporfolio.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.vedruna.servidorporfolio.persistance.model.State;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StateDTO {

    // Atributos que representan el estado y los proyectos asociados a ese estado.
    private int id;                      // ID único del estado.
    private String name;                 // Nombre del estado (por ejemplo, "En desarrollo").
    private List<ProjectDTO> statesWithProject; // Lista de proyectos asociados a este estado (DTO).

    /**
     * Constructor que convierte un objeto State a un DTO (Data Transfer Object).
     * 
     * @param s el objeto State que se va a convertir en un StateDTO.
     */
    public StateDTO(State s) {
        this.id = s.getId(); // Asigna el ID del estado al DTO.
        this.name = s.getName(); // Asigna el nombre del estado al DTO.
        
        // Si el estado tiene proyectos asociados, convierte cada proyecto a un ProjectDTO.
        this.statesWithProject = s.getStatesWithProject() != null ? s.getStatesWithProject().stream()
            .map(ProjectDTO::new) // Convierte cada objeto Project a un DTO.
            .collect(Collectors.toList()) // Recoge los resultados en una lista.
            : null; // Si no hay proyectos asociados, asigna null.
    }
    
}

package com.vedruna.servidorporfolio.dto;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.vedruna.servidorporfolio.persistance.model.Developer;
import com.vedruna.servidorporfolio.persistance.model.Project;
import com.vedruna.servidorporfolio.persistance.model.Technology;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDTO {

    // Atributos que representan la información básica del proyecto.
    private int id;                     // ID único del proyecto.
    private String name;                // Nombre del proyecto.
    private String description;         // Descripción del proyecto.
    private Date start_date;            // Fecha de inicio del proyecto.
    private Date end_date;              // Fecha de finalización del proyecto.
    private String repository_url;      // URL del repositorio del proyecto.
    private String demo_url;            // URL de la demostración del proyecto.
    private String picture;             // Imagen asociada al proyecto.
    private String stateProjectName;    // Nombre del estado del proyecto (por ejemplo, "En desarrollo").
    private List<TechnologyDTO> technologies; // Lista de tecnologías utilizadas en el proyecto (DTO).
    private List<DeveloperDTO> developers;    // Lista de desarrolladores involucrados en el proyecto (DTO).

    /**
     * Constructor que convierte un objeto Project en un DTO (Data Transfer Object).
     * 
     * @param p el objeto Project que se va a convertir en un ProjectDTO.
     */
    public ProjectDTO(Project p) {
        this.id = p.getId();                                 // Asigna el ID del proyecto al DTO.
        this.name = p.getName();                             // Asigna el nombre del proyecto al DTO.
        this.description = p.getDescription();               // Asigna la descripción al DTO.
        this.start_date = p.getStart_date();                  // Asigna la fecha de inicio al DTO.
        this.end_date = p.getEnd_date();                      // Asigna la fecha de fin al DTO.
        this.repository_url = p.getRepository_url();          // Asigna la URL del repositorio al DTO.
        this.demo_url = p.getDemo_url();                      // Asigna la URL de la demo al DTO.
        this.picture = p.getPicture();                        // Asigna la imagen del proyecto al DTO.
        this.stateProjectName = p.getStateProject() != null ? p.getStateProject().getName() : null; // Asigna el estado del proyecto (si existe).
        this.technologies = technologiesDTO(p.getTechnologies()); // Convierte la lista de tecnologías a DTO.
        this.developers = developersDTO(p.getDevelopers());   // Convierte la lista de desarrolladores a DTO.
    }

    /**
     * Convierte una lista de objetos Technology a una lista de objetos TechnologyDTO.
     * 
     * @param technologies lista de objetos Technology a convertir.
     * @return lista de objetos TechnologyDTO.
     */
    public List<TechnologyDTO> technologiesDTO(List<Technology> technologies) {
        List<TechnologyDTO> technologiesRegistered = new ArrayList<>();
        for (Technology t : technologies) {
            technologiesRegistered.add(new TechnologyDTO(t)); // Convierte cada tecnología a DTO.
        }
        return technologiesRegistered;
    }

    /**
     * Convierte una lista de objetos Developer a una lista de objetos DeveloperDTO,
     * para ser serializada a JSON.
     * 
     * @param developers lista de objetos Developer a convertir.
     * @return lista de objetos DeveloperDTO, lista para ser serializada.
     */
    public List<DeveloperDTO> developersDTO(List<Developer> developers) {
        List<DeveloperDTO> developersRegistered = new ArrayList<>();
        for (Developer d : developers) {
            developersRegistered.add(new DeveloperDTO(d)); // Convierte cada desarrollador a DTO.
        }
        return developersRegistered;
    }
}

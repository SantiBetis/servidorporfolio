package com.vedruna.servidorporfolio.persistance.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor // Genera un constructor sin parámetros
@Data // Genera getters, setters, toString(), hashCode() y equals() automáticamente
@Entity // Indica que esta clase es una entidad de JPA (Java Persistence API)
@Table(name="technologies") // Especifica el nombre de la tabla en la base de datos
public class Technology implements Serializable { // Representa una tecnología utilizada en proyectos

    @Id // Define la clave primaria
    @Column(name="tech_id") // Mapea la columna 'tech_id' en la base de datos
    @NotNull(message = "ID no puede ser null") // Validación: el ID no puede ser null
    private int id; // ID de la tecnología

    @Column(name="tech_name") // Mapea la columna 'tech_name' en la base de datos
    private String name; // Nombre de la tecnología

    @ManyToMany(cascade = {CascadeType.PERSIST}) // Relación Many-to-Many con la entidad Project
    @JoinTable(name="technologies_used_in_projects", // Nombre de la tabla intermedia
        joinColumns={@JoinColumn(name="technologies_tech_id")}, // Clave foránea de la tecnología
        inverseJoinColumns={@JoinColumn(name="projects_project_id")}) // Clave foránea del proyecto
    private List<Project> projectsTechnologies = new ArrayList<>(); // Lista de proyectos en los que se usa esta tecnología

}

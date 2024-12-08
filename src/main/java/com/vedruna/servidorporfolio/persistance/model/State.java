package com.vedruna.servidorporfolio.persistance.model;

import java.util.List;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor // Genera un constructor sin parámetros
@Data // Genera getters, setters, toString(), hashCode() y equals() automáticamente
@Entity // Indica que esta clase es una entidad de JPA (Java Persistence API)
@Table(name="status") // Especifica el nombre de la tabla en la base de datos
public class State { // Representa el estado de un proyecto

    @Id // Define la clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // La clave primaria se genera automáticamente
    @Column(name="status_id") // Mapea la columna de la base de datos a este campo
    private int id; // ID del estado

    @Column(name="status_name") // Mapea la columna 'status_name' en la base de datos
    @NotNull(message = "Name no puede ser null") // Validación: el nombre no puede ser null
    private String name; // Nombre del estado

    @OneToMany(fetch= FetchType.LAZY, mappedBy="stateProject") // Relación One-to-Many con la entidad Project
    private List<Project> statesWithProject; // Lista de proyectos asociados a este estado

}


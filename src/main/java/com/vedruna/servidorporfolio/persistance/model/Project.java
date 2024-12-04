package com.vedruna.servidorporfolio.persistance.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.vedruna.servidorporfolio.validation.ValidUrl;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor // Genera un constructor sin parámetros
@Data // Genera getters, setters, toString(), hashCode() y equals() automáticamente
@Entity // Indica que esta clase es una entidad de JPA (Java Persistence API)
@Table(name="projects") // Especifica el nombre de la tabla en la base de datos
public class Project implements Serializable { // Marca la clase como serializable para su persistencia

    @Id // Define la clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // La clave primaria se genera automáticamente
    @Column(name="project_id") // Mapea la columna de la base de datos a este campo
    private int id; // ID del proyecto

    @Column(name="project_name") // Mapea la columna 'project_name' en la base de datos
    @NotNull(message = "Name no puede ser null") // Validación: el nombre no puede ser null
    @NotBlank(message = "Name no puede estar vacio") // Validación: el nombre no puede estar vacío
    private String name; // Nombre del proyecto

    @Column(name="description") // Mapea la columna 'description' en la base de datos
    @Size(min = 2, max = 50, message = "Description debe contener entre 2 y 50 caracteres") // Validación: tamaño de la descripción
    private String description; // Descripción del proyecto
    
    @Column(name="start_date") // Mapea la columna 'start_date' en la base de datos
    @PastOrPresent(message = "start_date no puede ser futura") // Validación: la fecha de inicio no puede ser futura
    private Date start_date; // Fecha de inicio del proyecto

    @Column(name="end_date") // Mapea la columna 'end_date' en la base de datos
    private Date end_date; // Fecha de finalización del proyecto

    @Column(name="repository_url") // Mapea la columna 'repository_url' en la base de datos
    @ValidUrl(message = "Formato de URL invalido") // Validación personalizada para URLs
    private String repository_url; // URL del repositorio del proyecto

    @Column(name="demo_url") // Mapea la columna 'demo_url' en la base de datos
    @ValidUrl(message = "Formato de URL invalido") // Validación personalizada para URLs
    private String demo_url; // URL del demo del proyecto

    @Column(name="picture") // Mapea la columna 'picture' en la base de datos
    private String picture; // Imagen asociada al proyecto

    @ManyToOne(fetch= FetchType.LAZY) // Relación Many-to-One con la entidad State
    @JoinColumn(name="status_status_id", referencedColumnName = "status_id") // Mapea la columna de clave foránea
    private State stateProject; // Estado del proyecto (entidad State)

    @ManyToMany(cascade = {CascadeType.ALL}, mappedBy="projectsTechnologies") // Relación Many-to-Many con la entidad Technology
    private List<Technology> technologies = new ArrayList<>(); // Tecnologías asociadas al proyecto

    @ManyToMany(cascade = {CascadeType.ALL}, mappedBy="projectsDevelopers") // Relación Many-to-Many con la entidad Developer
    private List<Developer> developers = new ArrayList<>(); // Desarrolladores asociados al proyecto

}

package com.vedruna.servidorporfolio.persistance.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.vedruna.servidorporfolio.validation.ValidUrl;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor  // Genera un constructor vacío
@Data  // Genera getters, setters, equals, hashCode y toString
@Entity  // Indica que esta clase es una entidad JPA
@Table(name="developers")  // Especifica el nombre de la tabla en la base de datos
public class Developer implements Serializable {

    @Id  // Define que este campo es la clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Genera un valor único de forma automática
    @Column(name="dev_id")  // Especifica el nombre de la columna en la base de datos
    private int id;  // ID único del desarrollador

    @Column(name="dev_name")  // Especifica el nombre de la columna en la base de datos
    @Size(min = 2, max = 50, message = "Name deber contener entre 2 y 50 caracteres")  // Validación de tamaño
    private String name;  // Nombre del desarrollador

    @Column(name="dev_surname")  // Especifica el nombre de la columna en la base de datos
    @Size(min = 2, max = 50, message = "Surname deber contener entre 2 y 50 caracteres")  // Validación de tamaño
    private String surname;  // Apellido del desarrollador

    @Column(name="email")  // Especifica el nombre de la columna en la base de datos
    @Email(message = "Email deber ser válido")  // Validación de formato de email
    private String email;  // Correo electrónico del desarrollador

    @Column(name="linkedin_url")  // Especifica el nombre de la columna en la base de datos
    @ValidUrl(message = "Formato de URL incorrecto")  // Validación de formato de URL
    private String linkedin_url;  // URL del perfil de LinkedIn

    @Column(name="github_url")  // Especifica el nombre de la columna en la base de datos
    @ValidUrl(message = "Formato de URL incorrecto")  // Validación de formato de URL
    private String github_url;  // URL del perfil de GitHub

    // Relación Many-to-Many con los proyectos en los que el desarrollador ha trabajado
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})  // Propaga las operaciones de persistencia (guardar, actualizar) a los proyectos relacionados
    @JoinTable(name="developers_worked_on_projects",  // Nombre de la tabla intermedia
        joinColumns={@JoinColumn(name="developers_dev_id")},  // Columna de la tabla intermedia que hace referencia a esta entidad (developer)
        inverseJoinColumns={@JoinColumn(name="projects_project_id")})  // Columna de la tabla intermedia que hace referencia a la entidad relacionada (project)
    private List<Project> projectsDevelopers = new ArrayList<>();  // Lista de proyectos en los que el desarrollador ha trabajado

}

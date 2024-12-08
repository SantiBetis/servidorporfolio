package com.vedruna.servidorporfolio.persistance.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository; // Importa JpaRepository para facilitar el acceso a la base de datos
import org.springframework.data.jpa.repository.Query; // Importa Query para realizar consultas personalizadas

import com.vedruna.servidorporfolio.dto.ProjectDTO; // Importa el DTO de Project
import com.vedruna.servidorporfolio.persistance.model.Project; // Importa la entidad Project

// Interfaz que extiende JpaRepository para proporcionar acceso a la base de datos de la entidad Project
public interface ProjectRepositoryI extends JpaRepository<Project, Integer> {

    // Método para encontrar un proyecto por su nombre (devuelve un Optional)
    Page<Project> findByNameContainingIgnoreCase(String name, Pageable pageable);
    
    // Método para obtener todos los proyectos paginados
    Page<Project> findAll(Pageable pageable);

    

    // Consulta personalizada para obtener proyectos por nombre de tecnología (utiliza JPQL)
    @Query("SELECT p FROM Project p JOIN p.technologies t WHERE t.name = :techName")
    List<ProjectDTO> findProjectsByTechnology(String techName);
}

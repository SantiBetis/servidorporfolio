package com.vedruna.servidorporfolio.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vedruna.servidorporfolio.dto.ProjectDTO;
import com.vedruna.servidorporfolio.persistance.model.Project;

// Interfaz para los servicios relacionados con el manejo de proyectos
public interface ProjectServiceI {

    /**
     * Muestra todos los proyectos en forma de página.
     * @param page número de página
     * @param size tamaño de la página
     * @return una página de ProjectDTO
     */
    Page<ProjectDTO> showAllProjects(int page, int size);

    // Método que devuelve una lista de ProjectDTOs, basándose en la búsqueda por nombre de proyecto.
    // Parámetro:
    //   name: Una cadena (String) que representa el nombre o parte del nombre de los proyectos que se desean encontrar.
    // Retorno:
    //   Una lista de objetos ProjectDTO que corresponden a los proyectos cuyo nombre contiene la cadena proporcionada (name).
    // Excepción:
    //   Si no se encuentran proyectos que coincidan con el nombre, se lanza una IllegalArgumentException con un mensaje adecuado.
<<<<<<< HEAD
    Page<ProjectDTO> findByNameContainingIgnoreCase(String name, Pageable pageable);
=======
    List<ProjectDTO> showProjectByName(String name);
>>>>>>> 4ae39d33a4835c6461e5ee022941dcfa03f0a181

    /**
     * Guarda un nuevo proyecto en la base de datos.
     * @param project el proyecto a guardar
     */
    void saveProject(Project project);

    /**
     * Elimina un proyecto por su ID.
     * @param id el ID del proyecto
     * @return true si el proyecto fue eliminado exitosamente
     */
    boolean deleteProject(Integer id);

    /**
     * Mueve un proyecto al estado de "Testing".
     * @param id el ID del proyecto
     * @return true si el movimiento fue exitoso
     */
    boolean moveProjectToTesting(Integer id);

    /**
     * Mueve un proyecto al estado de "Producción".
     * @param id el ID del proyecto
     * @return true si el movimiento fue exitoso
     */
    boolean moveProjectToProduction(Integer id);

    /**
     * Actualiza los detalles de un proyecto.
     * @param id el ID del proyecto
     * @param project el proyecto con los nuevos detalles
     * @return true si la actualización fue exitosa
     */
    boolean updateProject(Integer id, Project project);

    /**
     * Busca un proyecto por su ID.
     * @param projectId el ID del proyecto
     * @return el proyecto encontrado o null si no se encuentra
     */
    Project findById(Integer projectId);

    /**
     * Obtiene una lista de proyectos que usan una tecnología específica.
     * @param techName el nombre de la tecnología
     * @return una lista de ProjectDTO
     */
    List<ProjectDTO> getProjectsByTechnology(String techName);

}

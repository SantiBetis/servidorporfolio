package com.vedruna.servidorporfolio.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.vedruna.servidorporfolio.dto.ProjectDTO;
import com.vedruna.servidorporfolio.persistance.model.Project;
import com.vedruna.servidorporfolio.persistance.model.State;
import com.vedruna.servidorporfolio.persistance.repository.ProjectRepositoryI;
import com.vedruna.servidorporfolio.persistance.repository.StateRepositoryI;

@Service
public class ProjectServiceImpl implements ProjectServiceI {

    @Autowired
    ProjectRepositoryI projectRepository;

    @Autowired
    StateRepositoryI stateRepository;
    
    /**
     * Obtiene todos los proyectos con paginación.
     * @param page número de página
     * @param size tamaño de la página
     * @return una página con proyectos convertidos a ProjectDTO
     */
    @Override
    public Page<ProjectDTO> showAllProjects(int page, int size) {
        Pageable pageable = PageRequest.of(page, size); // Definir la paginación
        Page<Project> projectPage = projectRepository.findAll(pageable); // Obtener proyectos paginados
        return projectPage.map(ProjectDTO::new); // Convertir a ProjectDTO y devolver
    }

    /**
     * Obtiene un proyecto por su nombre.
     * @param name el nombre del proyecto
     * @return el ProjectDTO correspondiente al proyecto encontrado
     * @throws IllegalArgumentException si no se encuentra el proyecto
     */
    @Override
<<<<<<< HEAD
    public Page<ProjectDTO> findByNameContainingIgnoreCase(String name, Pageable pageable) {
        Page<Project> projectPage = projectRepository.findByNameContainingIgnoreCase(name, pageable);
=======
    public List<ProjectDTO> showProjectByName(String name) {
        // Se obtienen todos los proyectos desde el repositorio
        List<Project> projects = projectRepository.findAll();
    
        // Se crea una lista para almacenar los proyectos que coincidan con el nombre dado
        List<Project> matchingProjects = new ArrayList<>();
    
        // Se recorre la lista de proyectos obtenida desde la base de datos
        for (Project project : projects) {
            // Si el nombre del proyecto contiene la cadena 'name' proporcionada, se agrega a la lista de proyectos coincidentes
            if (project.getName().contains(name)) {
                matchingProjects.add(project);
            }
        }
    
        // Si no se encontraron proyectos que coincidan con el nombre, se lanza una excepción
        if (matchingProjects.isEmpty()) {
            throw new IllegalArgumentException("No se encontraron proyectos con el nombre: " + name);
        }
    
        // Se crea una lista para almacenar los objetos DTO que representarán los proyectos
        List<ProjectDTO> projectDTOs = new ArrayList<>();
    
        // Se recorre la lista de proyectos coincidentes y se convierte cada uno en su correspondiente DTO
        for (Project p : matchingProjects) {
            projectDTOs.add(new ProjectDTO(p));
        }
    
        // Se devuelve la lista de DTOs de proyectos que coinciden con el nombre
        return projectDTOs;
    }
    
>>>>>>> 4ae39d33a4835c6461e5ee022941dcfa03f0a181

        List<ProjectDTO> projects = new ArrayList<>();
        for (Project project : projectPage) {
            projects.add(new ProjectDTO(project));
        }
        return new PageImpl<>(projects, pageable, projectPage.getTotalElements());
    
    }
    /**
     * Guarda un proyecto en la base de datos.
     * @param project el proyecto a guardar
     */
    @Override
    public void saveProject(Project project) {
        projectRepository.save(project); // Guardar el proyecto
    }

    /**
     * Elimina un proyecto por su ID.
     * @param id el ID del proyecto
     * @return true si se eliminó exitosamente
     * @throws IllegalArgumentException si no se encuentra el proyecto
     */
    public boolean deleteProject(Integer id) {
        Optional<Project> project = projectRepository.findById(id); // Buscar proyecto por ID
        if (project.isPresent()) {
            projectRepository.deleteById(id); // Eliminar proyecto si existe
            return true;
        } else {
            throw new IllegalArgumentException("No existe ningún proyecto con el ID: " + id);
        }
    }

    /**
     * Actualiza un proyecto existente.
     * @param id el ID del proyecto a actualizar
     * @param project los nuevos datos del proyecto
     * @return true si la actualización fue exitosa, false si no se encontró el proyecto
     */
    public boolean updateProject(Integer id, Project project) {
        Optional<Project> projectToUpdate = projectRepository.findById(id); // Buscar el proyecto por ID
        if (projectToUpdate.isPresent()) {
            // Actualizar las propiedades del proyecto
            projectToUpdate.get().setName(project.getName());
            projectToUpdate.get().setDescription(project.getDescription());
            projectToUpdate.get().setStart_date(project.getStart_date());
            projectToUpdate.get().setEnd_date(project.getEnd_date());
            projectToUpdate.get().setRepository_url(project.getRepository_url());
            projectToUpdate.get().setDemo_url(project.getDemo_url());
            projectToUpdate.get().setPicture(project.getPicture());
            projectToUpdate.get().setTechnologies(project.getTechnologies());
            projectToUpdate.get().setDevelopers(project.getDevelopers());
            projectRepository.save(projectToUpdate.get()); // Guardar el proyecto actualizado
            return true;
        } else {
            return false; // Si no se encuentra el proyecto, retornar falso
        }
    }

    /**
     * Mueve un proyecto al estado de "Testing".
     * @param id el ID del proyecto
     * @return true si el movimiento fue exitoso
     */
    @Override
    public boolean moveProjectToTesting(Integer id) {
        Optional<Project> projectOptional = projectRepository.findById(id); // Buscar el proyecto
        boolean isUpdated = false;
        if (projectOptional.isPresent()) {
            Project project = projectOptional.get();
            // Buscar el estado "Testing" por su ID
            Optional<State> stateOptional = stateRepository.findById(2);
            if (stateOptional.isPresent()) {
                project.setStateProject(stateOptional.get()); // Asignar el estado de "Testing"
                projectRepository.save(project); // Guardar el proyecto con el nuevo estado
                isUpdated = true;
            } else {
                System.out.println("Estado con ID 2 no existe.");
            }
        } else {
            System.out.println("Proyecto con ID " + id + " no existe.");
        }
        return isUpdated; // Retornar si se actualizó el proyecto
    }

    /**
     * Mueve un proyecto al estado de "Producción".
     * @param id el ID del proyecto
     * @return true si el movimiento fue exitoso
     */
    @Override
    public boolean moveProjectToProduction(Integer id) {
        Optional<Project> projectOptional = projectRepository.findById(id); // Buscar el proyecto
        boolean isUpdated = false;
        if (projectOptional.isPresent()) {
            Project project = projectOptional.get();
            // Buscar el estado "Producción" por su ID
            Optional<State> stateOptional = stateRepository.findById(3);
            if (stateOptional.isPresent()) {
                project.setStateProject(stateOptional.get()); // Asignar el estado de "Producción"
                projectRepository.save(project); // Guardar el proyecto con el nuevo estado
                isUpdated = true;
            } else {
                System.out.println("Estado con ID 3 no existe.");
            }
        } else {
            System.out.println("Proyecto con ID " + id + " no existe.");
        }
        return isUpdated; // Retornar si se actualizó el proyecto
    }

    /**
     * Busca un proyecto por su ID.
     * @param projectId el ID del proyecto
     * @return el proyecto si se encuentra, o null si no existe
     */
    @Override
    public Project findById(Integer projectId) {
        return projectRepository.findById(projectId).orElse(null); // Buscar proyecto por ID
    }

    /**
     * Obtiene proyectos que usan una tecnología específica.
     * @param techName el nombre de la tecnología
     * @return una lista de ProjectDTO de proyectos que usan la tecnología
     */
    @Override
    public List<ProjectDTO> getProjectsByTechnology(String techName) {
        return projectRepository.findProjectsByTechnology(techName); // Buscar proyectos por tecnología
    }
}

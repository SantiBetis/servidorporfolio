package com.vedruna.servidorporfolio.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vedruna.servidorporfolio.persistance.model.Project;
import com.vedruna.servidorporfolio.persistance.model.Technology;
import com.vedruna.servidorporfolio.persistance.repository.ProjectRepositoryI;
import com.vedruna.servidorporfolio.persistance.repository.TechnologyRepositoryI;

@Service
public class TechnologyServiceImpl implements TechnologyServiceI {

    @Autowired
    TechnologyRepositoryI technologyRepository;

    @Autowired
    ProjectRepositoryI projectRepository;

    /**
     * Guarda una tecnología con los proyectos asociados
     * @param technology la tecnología a guardar
     */
    @Override
    public void saveTechnology(Technology technology) {
        // Verifica si ya existe una tecnología con el mismo ID
        if (technologyRepository.existsById(technology.getId())) {
            throw new IllegalArgumentException("El ID de la tecnología ya está en uso");
        }
        
        List<Project> managedProjects = new ArrayList<>();
        
        for (Project project : technology.getProjectsTechnologies()) {
            projectRepository.findById(project.getId()).ifPresentOrElse(
                managedProjects::add, 
                () -> { 
                    throw new IllegalArgumentException("No existe ningún proyecto con el ID: " + project.getId());
                }
            );
        }
        
        // Asociar los proyectos gestionados por JPA a la tecnología
        technology.setProjectsTechnologies(managedProjects);
        
        // Guardar la tecnología con los proyectos asociados
        technologyRepository.save(technology);
    }
    

    /**
    * Elimina una tecnología por su ID.
    * 
    * @param id el ID de la tecnología a eliminar
    * @return true si la tecnología fue eliminada exitosamente, en caso contrario lanza una excepción
    * @throws IllegalArgumentException si no existe una tecnología con el ID proporcionado
    */
    @Override
    public boolean deleteTechnology(Integer id) {
        Optional<Technology> technology = technologyRepository.findById(id);
    
        if (technology.isPresent()) {
            technologyRepository.deleteById(id); 
            return true;
        } else {
            throw new IllegalArgumentException("No existe ninguna tecnología con el ID: " + id);
        }
    }


    /**
     * Encuentra una tecnología por su ID.
     * 
     * @param techId el ID de la tecnología a encontrar
     * @return la tecnología si se encuentra, en caso contrario retorna null
     */
    public Technology findById(Integer techId) {
        return technologyRepository.findById(techId).orElse(null); 
    }


    /**
    * Asocia una tecnología con un proyecto.
    *
    * @param technologyId el ID de la tecnología a asociar
    * @param projectId el ID del proyecto con el que asociar la tecnología
    * @throws IllegalArgumentException si no se encuentra la tecnología o el proyecto con los IDs proporcionados
    */
    @Override
    public void associateTechnologyWithProject(int projectId, int technologyId) {
        Technology technology = technologyRepository.findById(technologyId).orElseThrow(() -> 
            new IllegalArgumentException("Tecnología con ID " + technologyId + " no encontrada"));
        Project project = projectRepository.findById(projectId).orElseThrow(() -> 
            new IllegalArgumentException("Proyecto con ID " + projectId + " no encontrado"));
        project.getTechnologies().add(technology);
        technology.getProjectsTechnologies().add(project);
        projectRepository.save(project);
        technologyRepository.save(technology);
    }
}

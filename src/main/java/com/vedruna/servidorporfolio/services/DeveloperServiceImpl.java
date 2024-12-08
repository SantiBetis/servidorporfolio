package com.vedruna.servidorporfolio.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vedruna.servidorporfolio.persistance.model.Developer;
import com.vedruna.servidorporfolio.persistance.model.Project;
import com.vedruna.servidorporfolio.persistance.repository.DeveloperRepositoryI;
import com.vedruna.servidorporfolio.persistance.repository.ProjectRepositoryI;

// Implementación del servicio de Developer
@Service
public class DeveloperServiceImpl implements DeveloperServiceI {

    @Autowired
    DeveloperRepositoryI developerRepository;  // Repositorio de Developer para interactuar con la base de datos

    @Autowired
    ProjectRepositoryI projectRepository;  // Repositorio de Project para acceder a los proyectos asociados al developer
    
    /**
     * Guarda un developer con los proyectos asociados
     * @param developer el developer a ser guardado
     */
    @Override
    public void saveDeveloper(Developer developer) {
        List<Project> managedProjects = new ArrayList<>();
        
        // Itera sobre los proyectos asociados al developer
        for (Project project : developer.getProjectsDevelopers()) {
            projectRepository.findById(project.getId()).ifPresentOrElse(
                managedProjects::add,  // Si el proyecto existe, lo añade a la lista
                () -> { 
                    throw new IllegalArgumentException("No existe ningún proyecto con ID: " + project.getId());
                }
            );
        }
        
        // Asocia los proyectos gestionados al developer
        developer.setProjectsDevelopers(managedProjects);
        
        // Guarda el developer con los proyectos asociados
        developerRepository.save(developer);
    }

    /**
     * Elimina un developer por su ID.
     * 
     * @param id el ID del developer a ser eliminado
     * @return true si el developer fue eliminado exitosamente, de lo contrario lanza una excepción
     * @throws IllegalArgumentException si no existe un developer con el ID dado
     */
    @Override
    public boolean deleteDeveloper(Integer id) {
        Optional<Developer> developer = developerRepository.findById(id);
        
        if (developer.isPresent()) {
            developerRepository.deleteById(id);  // Elimina el developer si existe
            return true;
        } else {
            throw new IllegalArgumentException("No existe ningún developer con ID: " + id);
        }
    }

    /**
     * Encuentra un developer por su ID.
     * @param developerId el ID del developer a buscar
     * @return el developer si existe, o null si no existe
     */
    @Override
    public Developer findById(Integer developerId) {
        return developerRepository.findById(developerId).orElse(null);  // Retorna el developer o null si no se encuentra
    }
}

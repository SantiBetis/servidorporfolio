package com.vedruna.servidorporfolio.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.vedruna.servidorporfolio.dto.ResponseDTO;
import com.vedruna.servidorporfolio.persistance.model.Developer;
import com.vedruna.servidorporfolio.persistance.model.Project;
import com.vedruna.servidorporfolio.services.DeveloperServiceI;
import com.vedruna.servidorporfolio.services.ProjectServiceI;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
public class DeveloperController {

    @Autowired
    private DeveloperServiceI developerService;

    @Autowired
    private ProjectServiceI projectService;

    /**
     * Creates a new developer.
     *
     * @param developer the developer to be created, given in the request body
     * @return a ResponseEntity with the HTTP status 201 (Created) and a ResponseDTO containing a success message
     */
    @PostMapping("/developers")
    public ResponseEntity<ResponseDTO<String>> postDeveloper(@RequestBody Developer developer) {
        developerService.saveDeveloper(developer);
        ResponseDTO<String> response = new ResponseDTO<>("Developer creado correctamente", null);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }
    /**
     * Deletes a developer by their ID.
     *
     * @param id the ID of the developer to be deleted
     * @return a ResponseEntity with the HTTP status 204 (No Content) and a ResponseDTO containing a success message
     * @throws IllegalArgumentException if no developer exists with the given ID
     */
    @DeleteMapping("/developers/{id}")
    public ResponseEntity<ResponseDTO<String>> deleteDeveloper(@PathVariable Integer id) {
        boolean developerDeleted = developerService.deleteDeveloper(id);
        if (!developerDeleted) {
            throw new IllegalArgumentException("No hay ningún developer con ID: " + id);
        }
        ResponseDTO<String> response = new ResponseDTO<>("Developer borrado correctamente", null);
        return ResponseEntity.status(HttpStatus.OK).body(response); // En vez de NO_CONTENT puse OK
    }
    

    /**
    * Adds a developer to a project.
    *
    * @param developerId the ID of the developer to be added
    * @param projectId the ID of the project to which the developer will be added
    * @return a ResponseEntity that contains a success message if the operation is successful,
    *         or an error message if the developer or project is not found
    */
    @PostMapping("/developers/worked/{developerId}/{projectId}")
    public ResponseEntity<?> addDeveloperToProject(@PathVariable int developerId, @PathVariable int projectId) {
        Developer developer = developerService.findById(developerId);
        Project project = projectService.findById(projectId);
        
        if (developer == null) {
            return ResponseEntity.badRequest().body("Developer no encontrado");
        }
        
        if (project == null) {
            return ResponseEntity.badRequest().body("Project no encontrado");
        }
        if (!project.getDevelopers().contains(developer)) {
            project.getDevelopers().add(developer);
            developer.getProjectsDevelopers().add(project);
            projectService.saveProject(project);
            developerService.saveDeveloper(developer);  
        }
    
        return ResponseEntity.ok("Developer añadido al projecto correctamente");
    }
    
    
}

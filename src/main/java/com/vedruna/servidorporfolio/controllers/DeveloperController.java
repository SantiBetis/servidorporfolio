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

@RestController // Anotación que indica que esta clase es un controlador REST
@RequestMapping("/api/v1") // Establece la ruta base para las solicitudes
@CrossOrigin // Permite solicitudes desde diferentes dominios (útil para el desarrollo frontend y backend separados)
public class DeveloperController {

    @Autowired // Inyecta la instancia del servicio para gestionar desarrolladores
    private DeveloperServiceI developerService;

    @Autowired // Inyecta la instancia del servicio para gestionar proyectos
    private ProjectServiceI projectService;

    /**
     * Crea un nuevo desarrollador.
     * 
     * @param developer El desarrollador a crear, dado en el cuerpo de la solicitud
     * @return ResponseEntity con el código de estado HTTP 201 (Creado) y un mensaje de éxito
     */
    @PostMapping("/developers") // Ruta para crear un nuevo desarrollador
    public ResponseEntity<ResponseDTO<String>> postDeveloper(@RequestBody Developer developer) {
        developerService.saveDeveloper(developer); // Llama al servicio para guardar el desarrollador
        ResponseDTO<String> response = new ResponseDTO<>("Developer creado correctamente", null); // Crea una respuesta con un mensaje
        return ResponseEntity
                .status(HttpStatus.CREATED) // Establece el código de estado HTTP 201 (Creado)
                .body(response); // Devuelve la respuesta con el mensaje
    }

    /**
     * Elimina un desarrollador por su ID.
     * 
     * @param id El ID del desarrollador a eliminar
     * @return ResponseEntity con el código de estado HTTP 204 (Sin Contenido) y un mensaje de éxito
     * @throws IllegalArgumentException Si no se encuentra un desarrollador con el ID dado
     */
    @DeleteMapping("/developers/{id}") // Ruta para eliminar un desarrollador por su ID
    public ResponseEntity<ResponseDTO<String>> deleteDeveloper(@PathVariable Integer id) {
        boolean developerDeleted = developerService.deleteDeveloper(id); // Llama al servicio para eliminar el desarrollador
        if (!developerDeleted) {
            throw new IllegalArgumentException("No hay ningún developer con ID: " + id); // Si no se encuentra el desarrollador, lanza una excepción
        }
        ResponseDTO<String> response = new ResponseDTO<>("Developer borrado correctamente", null); // Crea una respuesta de éxito
        return ResponseEntity.status(HttpStatus.OK).body(response); // Devuelve el código HTTP 200 (OK) con el mensaje
    }

    /**
     * Añade un desarrollador a un proyecto.
     * 
     * @param developerId El ID del desarrollador a añadir
     * @param projectId El ID del proyecto al que se añadirá el desarrollador
     * @return ResponseEntity con un mensaje de éxito o error si no se encuentra el desarrollador o proyecto
     */
    @PostMapping("/developers/worked/{developerId}/{projectId}") // Ruta para asignar un desarrollador a un proyecto
    public ResponseEntity<?> addDeveloperToProject(@PathVariable int developerId, @PathVariable int projectId) {
        Developer developer = developerService.findById(developerId); // Busca al desarrollador por su ID
        Project project = projectService.findById(projectId); // Busca el proyecto por su ID
        
        if (developer == null) {
            return ResponseEntity.badRequest().body("Developer no encontrado"); // Si el desarrollador no existe, devuelve un error
        }
        
        if (project == null) {
            return ResponseEntity.badRequest().body("Project no encontrado"); // Si el proyecto no existe, devuelve un error
        }
        
        // Si el desarrollador no está ya en la lista de desarrolladores del proyecto, lo añade
        if (!project.getDevelopers().contains(developer)) {
            project.getDevelopers().add(developer); // Añade al desarrollador al proyecto
            developer.getProjectsDevelopers().add(project); // Añade el proyecto al desarrollador
            projectService.saveProject(project); // Guarda los cambios en el proyecto
            developerService.saveDeveloper(developer); // Guarda los cambios en el desarrollador
        }
    
        return ResponseEntity.ok("Developer añadido al projecto correctamente"); // Devuelve una respuesta con éxito
    }
}

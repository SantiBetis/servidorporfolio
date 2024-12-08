package com.vedruna.servidorporfolio.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.vedruna.servidorporfolio.dto.ProjectDTO;
import com.vedruna.servidorporfolio.dto.ResponseDTO;
import com.vedruna.servidorporfolio.persistance.model.Project;
import com.vedruna.servidorporfolio.services.ProjectServiceI;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1") // Mapea las peticiones a esta URL base
@CrossOrigin // Permite las solicitudes desde diferentes dominios
public class ProjectController {

    @Autowired
    private ProjectServiceI projectService; // Inyecta el servicio que maneja la lógica de los proyectos

    /**
     * Obtiene todos los proyectos con paginación.
     *
     * @param page El número de página.
     * @param size El tamaño de la página.
     * @return Una lista de proyectos en formato de página.
     */
    @GetMapping("/projects")
    public Page<ProjectDTO> getAllProjects(@RequestParam("page") int page, @RequestParam("size") int size) {
        return projectService.showAllProjects(page, size); // Llama al servicio para obtener los proyectos
    }
    

    // Endpoint para obtener proyectos por nombre, utilizando un parámetro de ruta.
    // Este método maneja las solicitudes HTTP GET a "/projects/{name}" donde {name} es el nombre del proyecto que se busca.
    // El método utiliza el servicio `projectService` para recuperar los proyectos que coinciden con el nombre proporcionado.
    // Parámetro:
    //   @PathVariable String name: El nombre del proyecto que se pasa como parte de la URL (en la ruta {name}).
    // Retorno:
    //   ResponseEntity<ResponseDTO<List<ProjectDTO>>>: Una respuesta HTTP que incluye un DTO con el mensaje y la lista de proyectos encontrados.
    // Excepción:
    //   Si no se encuentran proyectos que coincidan con el nombre, el servicio lanza una excepción, la cual se maneja y genera una respuesta adecuada.
    //   La respuesta será un objeto `ResponseDTO` que incluye un mensaje y la lista de proyectos encontrados, o un mensaje de error si no se encuentran resultados.

    @GetMapping("/projects/{name}")
    public ResponseEntity<Page<ProjectDTO>> showProjectByName(@PathVariable String name, Pageable pageable) {
    Page<ProjectDTO> projects = projectService.findByNameContainingIgnoreCase(name, pageable);
    return ResponseEntity.ok(projects); // Corrige aquí
    }


    /**
     * Guarda un nuevo proyecto.
     *
     * @param project El proyecto a guardar.
     * @param bindingResult Los errores de validación.
     * @return Un ResponseEntity con un mensaje de éxito o error si falla la validación.
     */
    @PostMapping("/projects")
    public ResponseEntity<ResponseDTO<Object>> postProject(@Valid @RequestBody Project project, BindingResult bindingResult) {
        // Verifica si existen errores de validación en los campos
        if (bindingResult.hasErrors()) {
            StringBuilder errorMessages = new StringBuilder();
            bindingResult.getFieldErrors().forEach(error -> 
                errorMessages.append(error.getField())
                             .append(": ")
                             .append(error.getDefaultMessage())
                             .append("\n")
            );
            ResponseDTO<Object> response = new ResponseDTO<>("Error de validación", errorMessages.toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response); // Devuelve error si la validación falla
        }

        // Verifica si la fecha de inicio es válida (no posterior a hoy)
        LocalDate today = LocalDate.now();
        if (project.getStart_date().toLocalDate().isAfter(today)) {
            ResponseDTO<Object> response = new ResponseDTO<>("Error de validación", "La fecha de inicio no puede ser posterior a la fecha actual.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        // Guarda el proyecto si no hay errores
        projectService.saveProject(project);
        ResponseDTO<Object> response = new ResponseDTO<>("Proyecto creado", null);
        return ResponseEntity.status(HttpStatus.CREATED).body(response); // Devuelve éxito al crear el proyecto
    }

    /**
     * Elimina un proyecto por su ID.
     *
     * @param id El ID del proyecto a eliminar.
     * @return Una respuesta con el mensaje de éxito o un error si no se encuentra el proyecto.
     */
    @DeleteMapping("/projects/{id}")
    public ResponseEntity<ResponseDTO<String>> deleteProject(@PathVariable Integer id) {
        boolean projectDeleted = projectService.deleteProject(id);
        if (!projectDeleted) {
            throw new IllegalArgumentException("No hay ningún proyecto con el ID:" + id); // Lanza error si no se encuentra el proyecto
        }
        ResponseDTO<String> response = new ResponseDTO<>("Proyecto eliminado", "Proyecto con ID " + id + " eliminado.");
        return ResponseEntity.status(HttpStatus.OK).body(response); // Devuelve éxito al eliminar el proyecto
    }

    /**
     * Actualiza un proyecto existente.
     *
     * @param id El ID del proyecto a actualizar.
     * @param project Los datos del proyecto a actualizar.
     * @param bindingResult Los errores de validación.
     * @return Un ResponseEntity con el mensaje de éxito o error si la validación falla.
     */
    @PutMapping("/projects/{id}")
    public ResponseEntity<ResponseDTO<Object>> updateProject(@PathVariable Integer id, @Valid @RequestBody Project project, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMessages = new StringBuilder();
            bindingResult.getFieldErrors().forEach(error -> 
                errorMessages.append(error.getField())
                             .append(": ")
                             .append(error.getDefaultMessage())
                             .append("\n")
            );
            ResponseDTO<Object> response = new ResponseDTO<>("Error de validación", errorMessages.toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response); // Devuelve error si la validación falla
        }

        boolean projectUpdated = projectService.updateProject(id, project);
        if (!projectUpdated) {
            ResponseDTO<Object> response = new ResponseDTO<>("Error", "No hay ningún proyecto con el ID: " + id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response); // Devuelve error si el proyecto no existe
        }

        ResponseDTO<Object> response = new ResponseDTO<>("Proyecto actualizado correctamente", project);
        return ResponseEntity.status(HttpStatus.OK).body(response); // Devuelve éxito al actualizar el proyecto
    }

    /**
     * Mueve un proyecto al estado de testing.
     *
     * @param id El ID del proyecto a mover.
     * @return Una respuesta con el mensaje de éxito o error si no se puede mover.
     */
    @PatchMapping("/projects/totesting/{id}")
    public ResponseEntity<String> moveProjectToTesting(@PathVariable Integer id) {
        try {
            boolean result = projectService.moveProjectToTesting(id); // Mueve el proyecto a testing
            if (result) {
                return ResponseEntity.ok("Proyectos movidos a testing correctamente");
            } else if (projectService.findById(id) == null) { 
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Proyecto no encontrado");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ningún proyecto movido a testing");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al mover los proyectos a testing: " + e.getMessage());
        }
    }

    /**
     * Mueve un proyecto al estado de producción.
     *
     * @param id El ID del proyecto a mover.
     * @return Una respuesta con el mensaje de éxito o error si no se puede mover.
     */
    @PatchMapping("/projects/toprod/{id}")
    public ResponseEntity<String> moveProjectToProduction(@PathVariable Integer id) {
        try {
            boolean result = projectService.moveProjectToProduction(id); // Mueve el proyecto a producción
            if (result) {
                return ResponseEntity.ok("Proyectos movidos a producción correctamente");
            } else if (projectService.findById(id) == null) { 
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Proyecto no encontrado");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ningún proyecto movido a producción");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al mover los proyectos a producción: " + e.getMessage());
        }
    }

    /**
     * Obtiene todos los proyectos que utilizan una tecnología específica.
     *
     * @param tech El nombre de la tecnología a buscar.
     * @return Una respuesta con los proyectos encontrados o un error 404 si no hay proyectos con esa tecnología.
     */
    @GetMapping("/projects/tec/{tech}")
    public ResponseEntity<ResponseDTO<List<ProjectDTO>>> getProjectsByTechnology(@PathVariable String tech) {
        List<ProjectDTO> projects = projectService.getProjectsByTechnology(tech); // Busca proyectos por tecnología
    
        if (projects.isEmpty()) {
            ResponseDTO<List<ProjectDTO>> response = new ResponseDTO<>("Ningún proyecto encontrado con esta tecnología", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response); // Devuelve error si no se encuentra ningún proyecto
        }
    
        ResponseDTO<List<ProjectDTO>> response = new ResponseDTO<>("Proyectos encontrados", projects);
        return ResponseEntity.ok(response); // Devuelve los proyectos encontrados
    }
}

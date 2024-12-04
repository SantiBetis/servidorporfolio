package com.vedruna.servidorporfolio.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.vedruna.servidorporfolio.dto.ResponseDTO;
import com.vedruna.servidorporfolio.persistance.model.Technology;
import com.vedruna.servidorporfolio.services.TechnologyServiceI;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
public class TechnologyController {

    @Autowired
    private TechnologyServiceI technologyService;

    /**
     * Saves a technology with the projects associated.
     * @param technology the technology to be saved.
     * @return a ResponseEntity with the HTTP status 201 (Created) and a ResponseDTO containing a success message.
     */
    @PostMapping("/technologies")
    public ResponseEntity<String> createTechnology(@RequestBody Technology technology) {
        try {
            technologyService.saveTechnology(technology); // Llamada al servicio para guardar la tecnología.
            return ResponseEntity.status(HttpStatus.CREATED).body("Tecnología creada"); // Respuesta con mensaje de éxito.
        } catch (IllegalArgumentException e) {
            // Si el ID ya está en uso o algún otro error, se captura y devuelve un 400.
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    /**
     * Deletes a technology by its ID.
     * 
     * @param id the ID of the technology to be deleted.
     * @return a ResponseEntity with the HTTP status 204 (No Content) and a ResponseDTO containing a success message, 
     *         or a 404 if there isn't a technology with the given ID.
     */
    @DeleteMapping("/technologies/{id}")
    public ResponseEntity<ResponseDTO<String>> deleteTechnology(@PathVariable Integer id) {
        boolean technologyDeleted = technologyService.deleteTechnology(id); // Llamada al servicio para eliminar la tecnología.
        if (!technologyDeleted) {
            throw new IllegalArgumentException("Ninguna tecnología con ID: " + id); // Si no se encuentra la tecnología, se lanza una excepción.
        }
        ResponseDTO<String> response = new ResponseDTO<>("Tecnología eliminada", null); // Respuesta exitosa con mensaje.
        return ResponseEntity.status(HttpStatus.OK).body(response); // Respuesta con código 200 OK.
    }

    /**
     * Associate a technology with a project.
     * 
     * @param technologyId the ID of the technology to be used.
     * @param projectId the ID of the project.
     * @return a ResponseEntity with the HTTP status 200 (OK) and a success message.
     */
    @PostMapping("/technologies/used/{projectId}/{technologyId}")
    public ResponseEntity<String> associateTechnologyWithProject(@PathVariable int projectId, @PathVariable int technologyId) {
        try {
            technologyService.associateTechnologyWithProject(projectId, technologyId); // Llamada al servicio para asociar la tecnología al proyecto.
            return ResponseEntity.status(HttpStatus.OK).body("Tecnología asociada al proyecto"); // Respuesta exitosa.
        } catch (IllegalArgumentException e) {
            // Si ocurre un error, se captura y se devuelve un 400 con el mensaje de error.
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}

package com.vedruna.servidorporfolio.services;

import com.vedruna.servidorporfolio.persistance.model.Developer; // Importa la entidad Developer

// Interfaz para los servicios relacionados con la entidad Developer
public interface DeveloperServiceI {

    // Método para guardar un nuevo Developer en la base de datos
    void saveDeveloper(Developer developer);

    // Método para eliminar un Developer por su ID
    boolean deleteDeveloper(Integer id);

    // Método para buscar un Developer por su ID
    Developer findById(Integer developerId);

}

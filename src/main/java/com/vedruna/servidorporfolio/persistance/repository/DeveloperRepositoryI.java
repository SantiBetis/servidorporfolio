package com.vedruna.servidorporfolio.persistance.repository;

import org.springframework.data.jpa.repository.JpaRepository; // Importa JpaRepository para facilitar la interacción con la base de datos

import com.vedruna.servidorporfolio.persistance.model.Developer; // Importa la entidad Developer

// Interfaz que extiende JpaRepository para proporcionar métodos de acceso a la base de datos para la entidad Developer
public interface DeveloperRepositoryI extends JpaRepository<Developer, Integer> {
    // JpaRepository ya proporciona implementaciones predeterminadas para CRUD (crear, leer, actualizar, eliminar) 
    // y búsquedas personalizadas (si se añaden métodos adicionales)
}

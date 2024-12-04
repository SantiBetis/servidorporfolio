package com.vedruna.servidorporfolio.persistance.repository;

import org.springframework.data.jpa.repository.JpaRepository; // Importa JpaRepository para facilitar el acceso a la base de datos

import com.vedruna.servidorporfolio.persistance.model.State; // Importa la entidad State

// Interfaz que extiende JpaRepository para proporcionar acceso a la base de datos de la entidad State
public interface StateRepositoryI extends JpaRepository<State, Integer> {
    // JpaRepository ya proporciona métodos básicos como save, findById, delete, etc.
    // No es necesario agregar métodos adicionales si solo se requiere acceso básico
}

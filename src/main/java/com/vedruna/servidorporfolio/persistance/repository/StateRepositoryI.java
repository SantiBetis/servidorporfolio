package com.vedruna.servidorporfolio.persistance.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vedruna.servidorporfolio.persistance.model.State;

public interface StateRepositoryI extends JpaRepository<State, Integer> {
    
    
} 

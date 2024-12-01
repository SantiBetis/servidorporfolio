package com.vedruna.servidorporfolio.persistance.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vedruna.servidorporfolio.persistance.model.Developer;

public interface DeveloperRepositoryI extends JpaRepository<Developer, Integer> {
    
    
} 

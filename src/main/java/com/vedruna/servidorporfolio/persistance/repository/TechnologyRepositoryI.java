package com.vedruna.servidorporfolio.persistance.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vedruna.servidorporfolio.persistance.model.Technology;

public interface TechnologyRepositoryI extends JpaRepository<Technology, Integer> {
    
    
} 

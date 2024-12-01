package com.vedruna.servidorporfolio.services;



import com.vedruna.servidorporfolio.persistance.model.Technology;

public interface TechnologyServiceI {

    void saveTechnology(Technology technology);
    boolean deleteTechnology(Integer id);
    Technology findById(Integer techId);
    void associateTechnologyWithProject(int projectId, int technologyId);



    
} 
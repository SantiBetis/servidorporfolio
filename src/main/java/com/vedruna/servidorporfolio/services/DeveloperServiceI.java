package com.vedruna.servidorporfolio.services;



import com.vedruna.servidorporfolio.persistance.model.Developer;

public interface DeveloperServiceI {

    void saveDeveloper(Developer developer);
    boolean deleteDeveloper(Integer id);
    Developer findById(Integer developerId);



    
} 
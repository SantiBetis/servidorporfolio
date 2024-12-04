package com.vedruna.servidorporfolio.services;

import com.vedruna.servidorporfolio.persistance.model.Technology;

public interface TechnologyServiceI {

    /**
     * Saves a new technology.
     * @param technology the technology to be saved.
     */
    void saveTechnology(Technology technology);

    /**
     * Deletes a technology by its ID.
     * @param id the ID of the technology to be deleted.
     * @return true if the technology was successfully deleted, otherwise false.
     */
    boolean deleteTechnology(Integer id);

    /**
     * Finds a technology by its ID.
     * @param techId the ID of the technology.
     * @return the technology if found, otherwise null.
     */
    Technology findById(Integer techId);

    /**
     * Associates a technology with a project.
     * @param projectId the ID of the project.
     * @param technologyId the ID of the technology.
     */
    void associateTechnologyWithProject(int projectId, int technologyId);
}

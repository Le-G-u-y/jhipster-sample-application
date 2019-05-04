package io.github.jhipster.application.service;

import io.github.jhipster.application.service.dto.FinishedExcerciseDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link io.github.jhipster.application.domain.FinishedExcercise}.
 */
public interface FinishedExcerciseService {

    /**
     * Save a finishedExcercise.
     *
     * @param finishedExcerciseDTO the entity to save.
     * @return the persisted entity.
     */
    FinishedExcerciseDTO save(FinishedExcerciseDTO finishedExcerciseDTO);

    /**
     * Get all the finishedExcercises.
     *
     * @return the list of entities.
     */
    List<FinishedExcerciseDTO> findAll();


    /**
     * Get the "id" finishedExcercise.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<FinishedExcerciseDTO> findOne(Long id);

    /**
     * Delete the "id" finishedExcercise.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}

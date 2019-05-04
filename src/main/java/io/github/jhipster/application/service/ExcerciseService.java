package io.github.jhipster.application.service;

import io.github.jhipster.application.service.dto.ExcerciseDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link io.github.jhipster.application.domain.Excercise}.
 */
public interface ExcerciseService {

    /**
     * Save a excercise.
     *
     * @param excerciseDTO the entity to save.
     * @return the persisted entity.
     */
    ExcerciseDTO save(ExcerciseDTO excerciseDTO);

    /**
     * Get all the excercises.
     *
     * @return the list of entities.
     */
    List<ExcerciseDTO> findAll();


    /**
     * Get the "id" excercise.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ExcerciseDTO> findOne(Long id);

    /**
     * Delete the "id" excercise.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}

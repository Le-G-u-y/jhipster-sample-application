package io.github.jhipster.application.service;

import io.github.jhipster.application.service.dto.PlanDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link io.github.jhipster.application.domain.Plan}.
 */
public interface PlanService {

    /**
     * Save a plan.
     *
     * @param planDTO the entity to save.
     * @return the persisted entity.
     */
    PlanDTO save(PlanDTO planDTO);

    /**
     * Get all the plans.
     *
     * @return the list of entities.
     */
    List<PlanDTO> findAll();
    /**
     * Get all the PlanDTO where FinishedSession is {@code null}.
     *
     * @return the list of entities.
     */
    List<PlanDTO> findAllWhereFinishedSessionIsNull();


    /**
     * Get the "id" plan.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<PlanDTO> findOne(Long id);

    /**
     * Delete the "id" plan.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}

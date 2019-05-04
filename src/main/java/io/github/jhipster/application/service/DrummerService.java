package io.github.jhipster.application.service;

import io.github.jhipster.application.service.dto.DrummerDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link io.github.jhipster.application.domain.Drummer}.
 */
public interface DrummerService {

    /**
     * Save a drummer.
     *
     * @param drummerDTO the entity to save.
     * @return the persisted entity.
     */
    DrummerDTO save(DrummerDTO drummerDTO);

    /**
     * Get all the drummers.
     *
     * @return the list of entities.
     */
    List<DrummerDTO> findAll();
    /**
     * Get all the DrummerDTO where DrummerStatistics is {@code null}.
     *
     * @return the list of entities.
     */
    List<DrummerDTO> findAllWhereDrummerStatisticsIsNull();


    /**
     * Get the "id" drummer.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DrummerDTO> findOne(Long id);

    /**
     * Delete the "id" drummer.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}

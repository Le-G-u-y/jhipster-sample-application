package io.github.jhipster.application.service;

import io.github.jhipster.application.service.dto.DrummerStatisticsDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link io.github.jhipster.application.domain.DrummerStatistics}.
 */
public interface DrummerStatisticsService {

    /**
     * Save a drummerStatistics.
     *
     * @param drummerStatisticsDTO the entity to save.
     * @return the persisted entity.
     */
    DrummerStatisticsDTO save(DrummerStatisticsDTO drummerStatisticsDTO);

    /**
     * Get all the drummerStatistics.
     *
     * @return the list of entities.
     */
    List<DrummerStatisticsDTO> findAll();


    /**
     * Get the "id" drummerStatistics.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DrummerStatisticsDTO> findOne(Long id);

    /**
     * Delete the "id" drummerStatistics.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}

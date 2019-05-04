package io.github.jhipster.application.service;

import io.github.jhipster.application.service.dto.I18nTextDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link io.github.jhipster.application.domain.I18nText}.
 */
public interface I18nTextService {

    /**
     * Save a i18nText.
     *
     * @param i18nTextDTO the entity to save.
     * @return the persisted entity.
     */
    I18nTextDTO save(I18nTextDTO i18nTextDTO);

    /**
     * Get all the i18nTexts.
     *
     * @return the list of entities.
     */
    List<I18nTextDTO> findAll();


    /**
     * Get the "id" i18nText.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<I18nTextDTO> findOne(Long id);

    /**
     * Delete the "id" i18nText.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}

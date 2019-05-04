package io.github.jhipster.application.service.impl;

import io.github.jhipster.application.service.I18nTextService;
import io.github.jhipster.application.domain.I18nText;
import io.github.jhipster.application.repository.I18nTextRepository;
import io.github.jhipster.application.service.dto.I18nTextDTO;
import io.github.jhipster.application.service.mapper.I18nTextMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link I18nText}.
 */
@Service
@Transactional
public class I18nTextServiceImpl implements I18nTextService {

    private final Logger log = LoggerFactory.getLogger(I18nTextServiceImpl.class);

    private final I18nTextRepository i18nTextRepository;

    private final I18nTextMapper i18nTextMapper;

    public I18nTextServiceImpl(I18nTextRepository i18nTextRepository, I18nTextMapper i18nTextMapper) {
        this.i18nTextRepository = i18nTextRepository;
        this.i18nTextMapper = i18nTextMapper;
    }

    /**
     * Save a i18nText.
     *
     * @param i18nTextDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public I18nTextDTO save(I18nTextDTO i18nTextDTO) {
        log.debug("Request to save I18nText : {}", i18nTextDTO);
        I18nText i18nText = i18nTextMapper.toEntity(i18nTextDTO);
        i18nText = i18nTextRepository.save(i18nText);
        return i18nTextMapper.toDto(i18nText);
    }

    /**
     * Get all the i18nTexts.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<I18nTextDTO> findAll() {
        log.debug("Request to get all I18nTexts");
        return i18nTextRepository.findAll().stream()
            .map(i18nTextMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one i18nText by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<I18nTextDTO> findOne(Long id) {
        log.debug("Request to get I18nText : {}", id);
        return i18nTextRepository.findById(id)
            .map(i18nTextMapper::toDto);
    }

    /**
     * Delete the i18nText by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete I18nText : {}", id);
        i18nTextRepository.deleteById(id);
    }
}

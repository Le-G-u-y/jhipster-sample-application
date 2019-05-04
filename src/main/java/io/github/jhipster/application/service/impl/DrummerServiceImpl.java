package io.github.jhipster.application.service.impl;

import io.github.jhipster.application.service.DrummerService;
import io.github.jhipster.application.domain.Drummer;
import io.github.jhipster.application.repository.DrummerRepository;
import io.github.jhipster.application.service.dto.DrummerDTO;
import io.github.jhipster.application.service.mapper.DrummerMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Service Implementation for managing {@link Drummer}.
 */
@Service
@Transactional
public class DrummerServiceImpl implements DrummerService {

    private final Logger log = LoggerFactory.getLogger(DrummerServiceImpl.class);

    private final DrummerRepository drummerRepository;

    private final DrummerMapper drummerMapper;

    public DrummerServiceImpl(DrummerRepository drummerRepository, DrummerMapper drummerMapper) {
        this.drummerRepository = drummerRepository;
        this.drummerMapper = drummerMapper;
    }

    /**
     * Save a drummer.
     *
     * @param drummerDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public DrummerDTO save(DrummerDTO drummerDTO) {
        log.debug("Request to save Drummer : {}", drummerDTO);
        Drummer drummer = drummerMapper.toEntity(drummerDTO);
        drummer = drummerRepository.save(drummer);
        return drummerMapper.toDto(drummer);
    }

    /**
     * Get all the drummers.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<DrummerDTO> findAll() {
        log.debug("Request to get all Drummers");
        return drummerRepository.findAll().stream()
            .map(drummerMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }



    /**
    *  Get all the drummers where DrummerStatistics is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true) 
    public List<DrummerDTO> findAllWhereDrummerStatisticsIsNull() {
        log.debug("Request to get all drummers where DrummerStatistics is null");
        return StreamSupport
            .stream(drummerRepository.findAll().spliterator(), false)
            .filter(drummer -> drummer.getDrummerStatistics() == null)
            .map(drummerMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one drummer by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<DrummerDTO> findOne(Long id) {
        log.debug("Request to get Drummer : {}", id);
        return drummerRepository.findById(id)
            .map(drummerMapper::toDto);
    }

    /**
     * Delete the drummer by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Drummer : {}", id);
        drummerRepository.deleteById(id);
    }
}

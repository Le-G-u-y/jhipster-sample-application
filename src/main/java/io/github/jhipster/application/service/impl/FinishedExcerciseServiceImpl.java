package io.github.jhipster.application.service.impl;

import io.github.jhipster.application.service.FinishedExcerciseService;
import io.github.jhipster.application.domain.FinishedExcercise;
import io.github.jhipster.application.repository.FinishedExcerciseRepository;
import io.github.jhipster.application.service.dto.FinishedExcerciseDTO;
import io.github.jhipster.application.service.mapper.FinishedExcerciseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link FinishedExcercise}.
 */
@Service
@Transactional
public class FinishedExcerciseServiceImpl implements FinishedExcerciseService {

    private final Logger log = LoggerFactory.getLogger(FinishedExcerciseServiceImpl.class);

    private final FinishedExcerciseRepository finishedExcerciseRepository;

    private final FinishedExcerciseMapper finishedExcerciseMapper;

    public FinishedExcerciseServiceImpl(FinishedExcerciseRepository finishedExcerciseRepository, FinishedExcerciseMapper finishedExcerciseMapper) {
        this.finishedExcerciseRepository = finishedExcerciseRepository;
        this.finishedExcerciseMapper = finishedExcerciseMapper;
    }

    /**
     * Save a finishedExcercise.
     *
     * @param finishedExcerciseDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public FinishedExcerciseDTO save(FinishedExcerciseDTO finishedExcerciseDTO) {
        log.debug("Request to save FinishedExcercise : {}", finishedExcerciseDTO);
        FinishedExcercise finishedExcercise = finishedExcerciseMapper.toEntity(finishedExcerciseDTO);
        finishedExcercise = finishedExcerciseRepository.save(finishedExcercise);
        return finishedExcerciseMapper.toDto(finishedExcercise);
    }

    /**
     * Get all the finishedExcercises.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<FinishedExcerciseDTO> findAll() {
        log.debug("Request to get all FinishedExcercises");
        return finishedExcerciseRepository.findAll().stream()
            .map(finishedExcerciseMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one finishedExcercise by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<FinishedExcerciseDTO> findOne(Long id) {
        log.debug("Request to get FinishedExcercise : {}", id);
        return finishedExcerciseRepository.findById(id)
            .map(finishedExcerciseMapper::toDto);
    }

    /**
     * Delete the finishedExcercise by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete FinishedExcercise : {}", id);
        finishedExcerciseRepository.deleteById(id);
    }
}

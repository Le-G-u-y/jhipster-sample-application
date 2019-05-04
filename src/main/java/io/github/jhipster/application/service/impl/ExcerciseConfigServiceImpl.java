package io.github.jhipster.application.service.impl;

import io.github.jhipster.application.service.ExcerciseConfigService;
import io.github.jhipster.application.domain.ExcerciseConfig;
import io.github.jhipster.application.repository.ExcerciseConfigRepository;
import io.github.jhipster.application.service.dto.ExcerciseConfigDTO;
import io.github.jhipster.application.service.mapper.ExcerciseConfigMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link ExcerciseConfig}.
 */
@Service
@Transactional
public class ExcerciseConfigServiceImpl implements ExcerciseConfigService {

    private final Logger log = LoggerFactory.getLogger(ExcerciseConfigServiceImpl.class);

    private final ExcerciseConfigRepository excerciseConfigRepository;

    private final ExcerciseConfigMapper excerciseConfigMapper;

    public ExcerciseConfigServiceImpl(ExcerciseConfigRepository excerciseConfigRepository, ExcerciseConfigMapper excerciseConfigMapper) {
        this.excerciseConfigRepository = excerciseConfigRepository;
        this.excerciseConfigMapper = excerciseConfigMapper;
    }

    /**
     * Save a excerciseConfig.
     *
     * @param excerciseConfigDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ExcerciseConfigDTO save(ExcerciseConfigDTO excerciseConfigDTO) {
        log.debug("Request to save ExcerciseConfig : {}", excerciseConfigDTO);
        ExcerciseConfig excerciseConfig = excerciseConfigMapper.toEntity(excerciseConfigDTO);
        excerciseConfig = excerciseConfigRepository.save(excerciseConfig);
        return excerciseConfigMapper.toDto(excerciseConfig);
    }

    /**
     * Get all the excerciseConfigs.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<ExcerciseConfigDTO> findAll() {
        log.debug("Request to get all ExcerciseConfigs");
        return excerciseConfigRepository.findAll().stream()
            .map(excerciseConfigMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one excerciseConfig by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ExcerciseConfigDTO> findOne(Long id) {
        log.debug("Request to get ExcerciseConfig : {}", id);
        return excerciseConfigRepository.findById(id)
            .map(excerciseConfigMapper::toDto);
    }

    /**
     * Delete the excerciseConfig by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ExcerciseConfig : {}", id);
        excerciseConfigRepository.deleteById(id);
    }
}

package io.github.jhipster.application.service.impl;

import io.github.jhipster.application.service.DrummerStatisticsService;
import io.github.jhipster.application.domain.DrummerStatistics;
import io.github.jhipster.application.repository.DrummerStatisticsRepository;
import io.github.jhipster.application.service.dto.DrummerStatisticsDTO;
import io.github.jhipster.application.service.mapper.DrummerStatisticsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link DrummerStatistics}.
 */
@Service
@Transactional
public class DrummerStatisticsServiceImpl implements DrummerStatisticsService {

    private final Logger log = LoggerFactory.getLogger(DrummerStatisticsServiceImpl.class);

    private final DrummerStatisticsRepository drummerStatisticsRepository;

    private final DrummerStatisticsMapper drummerStatisticsMapper;

    public DrummerStatisticsServiceImpl(DrummerStatisticsRepository drummerStatisticsRepository, DrummerStatisticsMapper drummerStatisticsMapper) {
        this.drummerStatisticsRepository = drummerStatisticsRepository;
        this.drummerStatisticsMapper = drummerStatisticsMapper;
    }

    /**
     * Save a drummerStatistics.
     *
     * @param drummerStatisticsDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public DrummerStatisticsDTO save(DrummerStatisticsDTO drummerStatisticsDTO) {
        log.debug("Request to save DrummerStatistics : {}", drummerStatisticsDTO);
        DrummerStatistics drummerStatistics = drummerStatisticsMapper.toEntity(drummerStatisticsDTO);
        drummerStatistics = drummerStatisticsRepository.save(drummerStatistics);
        return drummerStatisticsMapper.toDto(drummerStatistics);
    }

    /**
     * Get all the drummerStatistics.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<DrummerStatisticsDTO> findAll() {
        log.debug("Request to get all DrummerStatistics");
        return drummerStatisticsRepository.findAll().stream()
            .map(drummerStatisticsMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one drummerStatistics by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<DrummerStatisticsDTO> findOne(Long id) {
        log.debug("Request to get DrummerStatistics : {}", id);
        return drummerStatisticsRepository.findById(id)
            .map(drummerStatisticsMapper::toDto);
    }

    /**
     * Delete the drummerStatistics by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete DrummerStatistics : {}", id);
        drummerStatisticsRepository.deleteById(id);
    }
}

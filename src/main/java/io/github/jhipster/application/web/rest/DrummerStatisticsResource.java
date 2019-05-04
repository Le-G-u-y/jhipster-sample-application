package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.service.DrummerStatisticsService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.service.dto.DrummerStatisticsDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link io.github.jhipster.application.domain.DrummerStatistics}.
 */
@RestController
@RequestMapping("/api")
public class DrummerStatisticsResource {

    private final Logger log = LoggerFactory.getLogger(DrummerStatisticsResource.class);

    private static final String ENTITY_NAME = "drummerStatistics";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DrummerStatisticsService drummerStatisticsService;

    public DrummerStatisticsResource(DrummerStatisticsService drummerStatisticsService) {
        this.drummerStatisticsService = drummerStatisticsService;
    }

    /**
     * {@code POST  /drummer-statistics} : Create a new drummerStatistics.
     *
     * @param drummerStatisticsDTO the drummerStatisticsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new drummerStatisticsDTO, or with status {@code 400 (Bad Request)} if the drummerStatistics has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/drummer-statistics")
    public ResponseEntity<DrummerStatisticsDTO> createDrummerStatistics(@Valid @RequestBody DrummerStatisticsDTO drummerStatisticsDTO) throws URISyntaxException {
        log.debug("REST request to save DrummerStatistics : {}", drummerStatisticsDTO);
        if (drummerStatisticsDTO.getId() != null) {
            throw new BadRequestAlertException("A new drummerStatistics cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DrummerStatisticsDTO result = drummerStatisticsService.save(drummerStatisticsDTO);
        return ResponseEntity.created(new URI("/api/drummer-statistics/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /drummer-statistics} : Updates an existing drummerStatistics.
     *
     * @param drummerStatisticsDTO the drummerStatisticsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated drummerStatisticsDTO,
     * or with status {@code 400 (Bad Request)} if the drummerStatisticsDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the drummerStatisticsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/drummer-statistics")
    public ResponseEntity<DrummerStatisticsDTO> updateDrummerStatistics(@Valid @RequestBody DrummerStatisticsDTO drummerStatisticsDTO) throws URISyntaxException {
        log.debug("REST request to update DrummerStatistics : {}", drummerStatisticsDTO);
        if (drummerStatisticsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DrummerStatisticsDTO result = drummerStatisticsService.save(drummerStatisticsDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, drummerStatisticsDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /drummer-statistics} : get all the drummerStatistics.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of drummerStatistics in body.
     */
    @GetMapping("/drummer-statistics")
    public List<DrummerStatisticsDTO> getAllDrummerStatistics() {
        log.debug("REST request to get all DrummerStatistics");
        return drummerStatisticsService.findAll();
    }

    /**
     * {@code GET  /drummer-statistics/:id} : get the "id" drummerStatistics.
     *
     * @param id the id of the drummerStatisticsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the drummerStatisticsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/drummer-statistics/{id}")
    public ResponseEntity<DrummerStatisticsDTO> getDrummerStatistics(@PathVariable Long id) {
        log.debug("REST request to get DrummerStatistics : {}", id);
        Optional<DrummerStatisticsDTO> drummerStatisticsDTO = drummerStatisticsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(drummerStatisticsDTO);
    }

    /**
     * {@code DELETE  /drummer-statistics/:id} : delete the "id" drummerStatistics.
     *
     * @param id the id of the drummerStatisticsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/drummer-statistics/{id}")
    public ResponseEntity<Void> deleteDrummerStatistics(@PathVariable Long id) {
        log.debug("REST request to delete DrummerStatistics : {}", id);
        drummerStatisticsService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}

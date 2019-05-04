package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.service.DrummerService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.service.dto.DrummerDTO;

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
import java.util.stream.StreamSupport;

/**
 * REST controller for managing {@link io.github.jhipster.application.domain.Drummer}.
 */
@RestController
@RequestMapping("/api")
public class DrummerResource {

    private final Logger log = LoggerFactory.getLogger(DrummerResource.class);

    private static final String ENTITY_NAME = "drummer";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DrummerService drummerService;

    public DrummerResource(DrummerService drummerService) {
        this.drummerService = drummerService;
    }

    /**
     * {@code POST  /drummers} : Create a new drummer.
     *
     * @param drummerDTO the drummerDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new drummerDTO, or with status {@code 400 (Bad Request)} if the drummer has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/drummers")
    public ResponseEntity<DrummerDTO> createDrummer(@Valid @RequestBody DrummerDTO drummerDTO) throws URISyntaxException {
        log.debug("REST request to save Drummer : {}", drummerDTO);
        if (drummerDTO.getId() != null) {
            throw new BadRequestAlertException("A new drummer cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DrummerDTO result = drummerService.save(drummerDTO);
        return ResponseEntity.created(new URI("/api/drummers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /drummers} : Updates an existing drummer.
     *
     * @param drummerDTO the drummerDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated drummerDTO,
     * or with status {@code 400 (Bad Request)} if the drummerDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the drummerDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/drummers")
    public ResponseEntity<DrummerDTO> updateDrummer(@Valid @RequestBody DrummerDTO drummerDTO) throws URISyntaxException {
        log.debug("REST request to update Drummer : {}", drummerDTO);
        if (drummerDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DrummerDTO result = drummerService.save(drummerDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, drummerDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /drummers} : get all the drummers.
     *
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of drummers in body.
     */
    @GetMapping("/drummers")
    public List<DrummerDTO> getAllDrummers(@RequestParam(required = false) String filter) {
        if ("drummerstatistics-is-null".equals(filter)) {
            log.debug("REST request to get all Drummers where drummerStatistics is null");
            return drummerService.findAllWhereDrummerStatisticsIsNull();
        }
        log.debug("REST request to get all Drummers");
        return drummerService.findAll();
    }

    /**
     * {@code GET  /drummers/:id} : get the "id" drummer.
     *
     * @param id the id of the drummerDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the drummerDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/drummers/{id}")
    public ResponseEntity<DrummerDTO> getDrummer(@PathVariable Long id) {
        log.debug("REST request to get Drummer : {}", id);
        Optional<DrummerDTO> drummerDTO = drummerService.findOne(id);
        return ResponseUtil.wrapOrNotFound(drummerDTO);
    }

    /**
     * {@code DELETE  /drummers/:id} : delete the "id" drummer.
     *
     * @param id the id of the drummerDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/drummers/{id}")
    public ResponseEntity<Void> deleteDrummer(@PathVariable Long id) {
        log.debug("REST request to delete Drummer : {}", id);
        drummerService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}

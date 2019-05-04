package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.service.FinishedExcerciseService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.service.dto.FinishedExcerciseDTO;

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
 * REST controller for managing {@link io.github.jhipster.application.domain.FinishedExcercise}.
 */
@RestController
@RequestMapping("/api")
public class FinishedExcerciseResource {

    private final Logger log = LoggerFactory.getLogger(FinishedExcerciseResource.class);

    private static final String ENTITY_NAME = "finishedExcercise";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FinishedExcerciseService finishedExcerciseService;

    public FinishedExcerciseResource(FinishedExcerciseService finishedExcerciseService) {
        this.finishedExcerciseService = finishedExcerciseService;
    }

    /**
     * {@code POST  /finished-excercises} : Create a new finishedExcercise.
     *
     * @param finishedExcerciseDTO the finishedExcerciseDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new finishedExcerciseDTO, or with status {@code 400 (Bad Request)} if the finishedExcercise has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/finished-excercises")
    public ResponseEntity<FinishedExcerciseDTO> createFinishedExcercise(@Valid @RequestBody FinishedExcerciseDTO finishedExcerciseDTO) throws URISyntaxException {
        log.debug("REST request to save FinishedExcercise : {}", finishedExcerciseDTO);
        if (finishedExcerciseDTO.getId() != null) {
            throw new BadRequestAlertException("A new finishedExcercise cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FinishedExcerciseDTO result = finishedExcerciseService.save(finishedExcerciseDTO);
        return ResponseEntity.created(new URI("/api/finished-excercises/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /finished-excercises} : Updates an existing finishedExcercise.
     *
     * @param finishedExcerciseDTO the finishedExcerciseDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated finishedExcerciseDTO,
     * or with status {@code 400 (Bad Request)} if the finishedExcerciseDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the finishedExcerciseDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/finished-excercises")
    public ResponseEntity<FinishedExcerciseDTO> updateFinishedExcercise(@Valid @RequestBody FinishedExcerciseDTO finishedExcerciseDTO) throws URISyntaxException {
        log.debug("REST request to update FinishedExcercise : {}", finishedExcerciseDTO);
        if (finishedExcerciseDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FinishedExcerciseDTO result = finishedExcerciseService.save(finishedExcerciseDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, finishedExcerciseDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /finished-excercises} : get all the finishedExcercises.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of finishedExcercises in body.
     */
    @GetMapping("/finished-excercises")
    public List<FinishedExcerciseDTO> getAllFinishedExcercises() {
        log.debug("REST request to get all FinishedExcercises");
        return finishedExcerciseService.findAll();
    }

    /**
     * {@code GET  /finished-excercises/:id} : get the "id" finishedExcercise.
     *
     * @param id the id of the finishedExcerciseDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the finishedExcerciseDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/finished-excercises/{id}")
    public ResponseEntity<FinishedExcerciseDTO> getFinishedExcercise(@PathVariable Long id) {
        log.debug("REST request to get FinishedExcercise : {}", id);
        Optional<FinishedExcerciseDTO> finishedExcerciseDTO = finishedExcerciseService.findOne(id);
        return ResponseUtil.wrapOrNotFound(finishedExcerciseDTO);
    }

    /**
     * {@code DELETE  /finished-excercises/:id} : delete the "id" finishedExcercise.
     *
     * @param id the id of the finishedExcerciseDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/finished-excercises/{id}")
    public ResponseEntity<Void> deleteFinishedExcercise(@PathVariable Long id) {
        log.debug("REST request to delete FinishedExcercise : {}", id);
        finishedExcerciseService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}

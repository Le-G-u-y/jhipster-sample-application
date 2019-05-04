package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.service.ExcerciseService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.service.dto.ExcerciseDTO;

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
 * REST controller for managing {@link io.github.jhipster.application.domain.Excercise}.
 */
@RestController
@RequestMapping("/api")
public class ExcerciseResource {

    private final Logger log = LoggerFactory.getLogger(ExcerciseResource.class);

    private static final String ENTITY_NAME = "excercise";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ExcerciseService excerciseService;

    public ExcerciseResource(ExcerciseService excerciseService) {
        this.excerciseService = excerciseService;
    }

    /**
     * {@code POST  /excercises} : Create a new excercise.
     *
     * @param excerciseDTO the excerciseDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new excerciseDTO, or with status {@code 400 (Bad Request)} if the excercise has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/excercises")
    public ResponseEntity<ExcerciseDTO> createExcercise(@Valid @RequestBody ExcerciseDTO excerciseDTO) throws URISyntaxException {
        log.debug("REST request to save Excercise : {}", excerciseDTO);
        if (excerciseDTO.getId() != null) {
            throw new BadRequestAlertException("A new excercise cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ExcerciseDTO result = excerciseService.save(excerciseDTO);
        return ResponseEntity.created(new URI("/api/excercises/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /excercises} : Updates an existing excercise.
     *
     * @param excerciseDTO the excerciseDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated excerciseDTO,
     * or with status {@code 400 (Bad Request)} if the excerciseDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the excerciseDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/excercises")
    public ResponseEntity<ExcerciseDTO> updateExcercise(@Valid @RequestBody ExcerciseDTO excerciseDTO) throws URISyntaxException {
        log.debug("REST request to update Excercise : {}", excerciseDTO);
        if (excerciseDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ExcerciseDTO result = excerciseService.save(excerciseDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, excerciseDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /excercises} : get all the excercises.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of excercises in body.
     */
    @GetMapping("/excercises")
    public List<ExcerciseDTO> getAllExcercises() {
        log.debug("REST request to get all Excercises");
        return excerciseService.findAll();
    }

    /**
     * {@code GET  /excercises/:id} : get the "id" excercise.
     *
     * @param id the id of the excerciseDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the excerciseDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/excercises/{id}")
    public ResponseEntity<ExcerciseDTO> getExcercise(@PathVariable Long id) {
        log.debug("REST request to get Excercise : {}", id);
        Optional<ExcerciseDTO> excerciseDTO = excerciseService.findOne(id);
        return ResponseUtil.wrapOrNotFound(excerciseDTO);
    }

    /**
     * {@code DELETE  /excercises/:id} : delete the "id" excercise.
     *
     * @param id the id of the excerciseDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/excercises/{id}")
    public ResponseEntity<Void> deleteExcercise(@PathVariable Long id) {
        log.debug("REST request to delete Excercise : {}", id);
        excerciseService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}

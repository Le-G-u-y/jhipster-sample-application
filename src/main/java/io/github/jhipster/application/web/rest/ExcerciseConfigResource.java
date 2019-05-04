package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.service.ExcerciseConfigService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.service.dto.ExcerciseConfigDTO;

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
 * REST controller for managing {@link io.github.jhipster.application.domain.ExcerciseConfig}.
 */
@RestController
@RequestMapping("/api")
public class ExcerciseConfigResource {

    private final Logger log = LoggerFactory.getLogger(ExcerciseConfigResource.class);

    private static final String ENTITY_NAME = "excerciseConfig";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ExcerciseConfigService excerciseConfigService;

    public ExcerciseConfigResource(ExcerciseConfigService excerciseConfigService) {
        this.excerciseConfigService = excerciseConfigService;
    }

    /**
     * {@code POST  /excercise-configs} : Create a new excerciseConfig.
     *
     * @param excerciseConfigDTO the excerciseConfigDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new excerciseConfigDTO, or with status {@code 400 (Bad Request)} if the excerciseConfig has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/excercise-configs")
    public ResponseEntity<ExcerciseConfigDTO> createExcerciseConfig(@Valid @RequestBody ExcerciseConfigDTO excerciseConfigDTO) throws URISyntaxException {
        log.debug("REST request to save ExcerciseConfig : {}", excerciseConfigDTO);
        if (excerciseConfigDTO.getId() != null) {
            throw new BadRequestAlertException("A new excerciseConfig cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ExcerciseConfigDTO result = excerciseConfigService.save(excerciseConfigDTO);
        return ResponseEntity.created(new URI("/api/excercise-configs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /excercise-configs} : Updates an existing excerciseConfig.
     *
     * @param excerciseConfigDTO the excerciseConfigDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated excerciseConfigDTO,
     * or with status {@code 400 (Bad Request)} if the excerciseConfigDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the excerciseConfigDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/excercise-configs")
    public ResponseEntity<ExcerciseConfigDTO> updateExcerciseConfig(@Valid @RequestBody ExcerciseConfigDTO excerciseConfigDTO) throws URISyntaxException {
        log.debug("REST request to update ExcerciseConfig : {}", excerciseConfigDTO);
        if (excerciseConfigDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ExcerciseConfigDTO result = excerciseConfigService.save(excerciseConfigDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, excerciseConfigDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /excercise-configs} : get all the excerciseConfigs.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of excerciseConfigs in body.
     */
    @GetMapping("/excercise-configs")
    public List<ExcerciseConfigDTO> getAllExcerciseConfigs() {
        log.debug("REST request to get all ExcerciseConfigs");
        return excerciseConfigService.findAll();
    }

    /**
     * {@code GET  /excercise-configs/:id} : get the "id" excerciseConfig.
     *
     * @param id the id of the excerciseConfigDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the excerciseConfigDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/excercise-configs/{id}")
    public ResponseEntity<ExcerciseConfigDTO> getExcerciseConfig(@PathVariable Long id) {
        log.debug("REST request to get ExcerciseConfig : {}", id);
        Optional<ExcerciseConfigDTO> excerciseConfigDTO = excerciseConfigService.findOne(id);
        return ResponseUtil.wrapOrNotFound(excerciseConfigDTO);
    }

    /**
     * {@code DELETE  /excercise-configs/:id} : delete the "id" excerciseConfig.
     *
     * @param id the id of the excerciseConfigDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/excercise-configs/{id}")
    public ResponseEntity<Void> deleteExcerciseConfig(@PathVariable Long id) {
        log.debug("REST request to delete ExcerciseConfig : {}", id);
        excerciseConfigService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}

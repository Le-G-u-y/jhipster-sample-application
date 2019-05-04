package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.service.I18nTextService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.service.dto.I18nTextDTO;

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
 * REST controller for managing {@link io.github.jhipster.application.domain.I18nText}.
 */
@RestController
@RequestMapping("/api")
public class I18nTextResource {

    private final Logger log = LoggerFactory.getLogger(I18nTextResource.class);

    private static final String ENTITY_NAME = "i18nText";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final I18nTextService i18nTextService;

    public I18nTextResource(I18nTextService i18nTextService) {
        this.i18nTextService = i18nTextService;
    }

    /**
     * {@code POST  /i-18-n-texts} : Create a new i18nText.
     *
     * @param i18nTextDTO the i18nTextDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new i18nTextDTO, or with status {@code 400 (Bad Request)} if the i18nText has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/i-18-n-texts")
    public ResponseEntity<I18nTextDTO> createI18nText(@Valid @RequestBody I18nTextDTO i18nTextDTO) throws URISyntaxException {
        log.debug("REST request to save I18nText : {}", i18nTextDTO);
        if (i18nTextDTO.getId() != null) {
            throw new BadRequestAlertException("A new i18nText cannot already have an ID", ENTITY_NAME, "idexists");
        }
        I18nTextDTO result = i18nTextService.save(i18nTextDTO);
        return ResponseEntity.created(new URI("/api/i-18-n-texts/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /i-18-n-texts} : Updates an existing i18nText.
     *
     * @param i18nTextDTO the i18nTextDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated i18nTextDTO,
     * or with status {@code 400 (Bad Request)} if the i18nTextDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the i18nTextDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/i-18-n-texts")
    public ResponseEntity<I18nTextDTO> updateI18nText(@Valid @RequestBody I18nTextDTO i18nTextDTO) throws URISyntaxException {
        log.debug("REST request to update I18nText : {}", i18nTextDTO);
        if (i18nTextDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        I18nTextDTO result = i18nTextService.save(i18nTextDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, i18nTextDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /i-18-n-texts} : get all the i18nTexts.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of i18nTexts in body.
     */
    @GetMapping("/i-18-n-texts")
    public List<I18nTextDTO> getAllI18nTexts() {
        log.debug("REST request to get all I18nTexts");
        return i18nTextService.findAll();
    }

    /**
     * {@code GET  /i-18-n-texts/:id} : get the "id" i18nText.
     *
     * @param id the id of the i18nTextDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the i18nTextDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/i-18-n-texts/{id}")
    public ResponseEntity<I18nTextDTO> getI18nText(@PathVariable Long id) {
        log.debug("REST request to get I18nText : {}", id);
        Optional<I18nTextDTO> i18nTextDTO = i18nTextService.findOne(id);
        return ResponseUtil.wrapOrNotFound(i18nTextDTO);
    }

    /**
     * {@code DELETE  /i-18-n-texts/:id} : delete the "id" i18nText.
     *
     * @param id the id of the i18nTextDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/i-18-n-texts/{id}")
    public ResponseEntity<Void> deleteI18nText(@PathVariable Long id) {
        log.debug("REST request to delete I18nText : {}", id);
        i18nTextService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}

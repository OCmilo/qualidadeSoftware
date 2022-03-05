package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.repository.FreightFormRepository;
import com.mycompany.myapp.service.FreightFormService;
import com.mycompany.myapp.service.dto.FreightFormDTO;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.mycompany.myapp.domain.FreightForm}.
 */
@RestController
@RequestMapping("/api")
public class FreightFormResource {

    private final Logger log = LoggerFactory.getLogger(FreightFormResource.class);

    private static final String ENTITY_NAME = "freightForm";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FreightFormService freightFormService;

    private final FreightFormRepository freightFormRepository;

    public FreightFormResource(FreightFormService freightFormService, FreightFormRepository freightFormRepository) {
        this.freightFormService = freightFormService;
        this.freightFormRepository = freightFormRepository;
    }

    /**
     * {@code POST  /freight-forms} : Create a new freightForm.
     *
     * @param freightFormDTO the freightFormDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new freightFormDTO, or with status {@code 400 (Bad Request)} if the freightForm has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/freight-forms")
    public ResponseEntity<FreightFormDTO> createFreightForm(@RequestBody FreightFormDTO freightFormDTO) throws URISyntaxException {
        log.debug("REST request to save FreightForm : {}", freightFormDTO);
        if (freightFormDTO.getId() != null) {
            throw new BadRequestAlertException("A new freightForm cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FreightFormDTO result = freightFormService.save(freightFormDTO);
        return ResponseEntity
            .created(new URI("/api/freight-forms/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /freight-forms/:id} : Updates an existing freightForm.
     *
     * @param id the id of the freightFormDTO to save.
     * @param freightFormDTO the freightFormDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated freightFormDTO,
     * or with status {@code 400 (Bad Request)} if the freightFormDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the freightFormDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/freight-forms/{id}")
    public ResponseEntity<FreightFormDTO> updateFreightForm(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody FreightFormDTO freightFormDTO
    ) throws URISyntaxException {
        log.debug("REST request to update FreightForm : {}, {}", id, freightFormDTO);
        if (freightFormDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, freightFormDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!freightFormRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        FreightFormDTO result = freightFormService.save(freightFormDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, freightFormDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /freight-forms/:id} : Partial updates given fields of an existing freightForm, field will ignore if it is null
     *
     * @param id the id of the freightFormDTO to save.
     * @param freightFormDTO the freightFormDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated freightFormDTO,
     * or with status {@code 400 (Bad Request)} if the freightFormDTO is not valid,
     * or with status {@code 404 (Not Found)} if the freightFormDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the freightFormDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/freight-forms/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<FreightFormDTO> partialUpdateFreightForm(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody FreightFormDTO freightFormDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update FreightForm partially : {}, {}", id, freightFormDTO);
        if (freightFormDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, freightFormDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!freightFormRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<FreightFormDTO> result = freightFormService.partialUpdate(freightFormDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, freightFormDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /freight-forms} : get all the freightForms.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of freightForms in body.
     */
    @GetMapping("/freight-forms")
    public List<FreightFormDTO> getAllFreightForms() {
        log.debug("REST request to get all FreightForms");
        return freightFormService.findAll();
    }

    /**
     * {@code GET  /freight-forms/:id} : get the "id" freightForm.
     *
     * @param id the id of the freightFormDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the freightFormDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/freight-forms/{id}")
    public ResponseEntity<FreightFormDTO> getFreightForm(@PathVariable Long id) {
        log.debug("REST request to get FreightForm : {}", id);
        Optional<FreightFormDTO> freightFormDTO = freightFormService.findOne(id);
        return ResponseUtil.wrapOrNotFound(freightFormDTO);
    }

    /**
     * {@code DELETE  /freight-forms/:id} : delete the "id" freightForm.
     *
     * @param id the id of the freightFormDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/freight-forms/{id}")
    public ResponseEntity<Void> deleteFreightForm(@PathVariable Long id) {
        log.debug("REST request to delete FreightForm : {}", id);
        freightFormService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}

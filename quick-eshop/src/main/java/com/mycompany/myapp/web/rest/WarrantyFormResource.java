package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.repository.WarrantyFormRepository;
import com.mycompany.myapp.service.WarrantyFormService;
import com.mycompany.myapp.service.dto.WarrantyFormDTO;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.WarrantyForm}.
 */
@RestController
@RequestMapping("/api")
public class WarrantyFormResource {

    private final Logger log = LoggerFactory.getLogger(WarrantyFormResource.class);

    private static final String ENTITY_NAME = "warrantyForm";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final WarrantyFormService warrantyFormService;

    private final WarrantyFormRepository warrantyFormRepository;

    public WarrantyFormResource(WarrantyFormService warrantyFormService, WarrantyFormRepository warrantyFormRepository) {
        this.warrantyFormService = warrantyFormService;
        this.warrantyFormRepository = warrantyFormRepository;
    }

    /**
     * {@code POST  /warranty-forms} : Create a new warrantyForm.
     *
     * @param warrantyFormDTO the warrantyFormDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new warrantyFormDTO, or with status {@code 400 (Bad Request)} if the warrantyForm has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/warranty-forms")
    public ResponseEntity<WarrantyFormDTO> createWarrantyForm(@RequestBody WarrantyFormDTO warrantyFormDTO) throws URISyntaxException {
        log.debug("REST request to save WarrantyForm : {}", warrantyFormDTO);
        if (warrantyFormDTO.getId() != null) {
            throw new BadRequestAlertException("A new warrantyForm cannot already have an ID", ENTITY_NAME, "idexists");
        }
        WarrantyFormDTO result = warrantyFormService.save(warrantyFormDTO);
        return ResponseEntity
            .created(new URI("/api/warranty-forms/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /warranty-forms/:id} : Updates an existing warrantyForm.
     *
     * @param id the id of the warrantyFormDTO to save.
     * @param warrantyFormDTO the warrantyFormDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated warrantyFormDTO,
     * or with status {@code 400 (Bad Request)} if the warrantyFormDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the warrantyFormDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/warranty-forms/{id}")
    public ResponseEntity<WarrantyFormDTO> updateWarrantyForm(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody WarrantyFormDTO warrantyFormDTO
    ) throws URISyntaxException {
        log.debug("REST request to update WarrantyForm : {}, {}", id, warrantyFormDTO);
        if (warrantyFormDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, warrantyFormDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!warrantyFormRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        WarrantyFormDTO result = warrantyFormService.save(warrantyFormDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, warrantyFormDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /warranty-forms/:id} : Partial updates given fields of an existing warrantyForm, field will ignore if it is null
     *
     * @param id the id of the warrantyFormDTO to save.
     * @param warrantyFormDTO the warrantyFormDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated warrantyFormDTO,
     * or with status {@code 400 (Bad Request)} if the warrantyFormDTO is not valid,
     * or with status {@code 404 (Not Found)} if the warrantyFormDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the warrantyFormDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/warranty-forms/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<WarrantyFormDTO> partialUpdateWarrantyForm(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody WarrantyFormDTO warrantyFormDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update WarrantyForm partially : {}, {}", id, warrantyFormDTO);
        if (warrantyFormDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, warrantyFormDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!warrantyFormRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<WarrantyFormDTO> result = warrantyFormService.partialUpdate(warrantyFormDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, warrantyFormDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /warranty-forms} : get all the warrantyForms.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of warrantyForms in body.
     */
    @GetMapping("/warranty-forms")
    public List<WarrantyFormDTO> getAllWarrantyForms() {
        log.debug("REST request to get all WarrantyForms");
        return warrantyFormService.findAll();
    }

    /**
     * {@code GET  /warranty-forms/:id} : get the "id" warrantyForm.
     *
     * @param id the id of the warrantyFormDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the warrantyFormDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/warranty-forms/{id}")
    public ResponseEntity<WarrantyFormDTO> getWarrantyForm(@PathVariable Long id) {
        log.debug("REST request to get WarrantyForm : {}", id);
        Optional<WarrantyFormDTO> warrantyFormDTO = warrantyFormService.findOne(id);
        return ResponseUtil.wrapOrNotFound(warrantyFormDTO);
    }

    /**
     * {@code DELETE  /warranty-forms/:id} : delete the "id" warrantyForm.
     *
     * @param id the id of the warrantyFormDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/warranty-forms/{id}")
    public ResponseEntity<Void> deleteWarrantyForm(@PathVariable Long id) {
        log.debug("REST request to delete WarrantyForm : {}", id);
        warrantyFormService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}

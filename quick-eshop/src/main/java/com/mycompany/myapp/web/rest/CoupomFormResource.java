package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.repository.CoupomFormRepository;
import com.mycompany.myapp.service.CoupomFormService;
import com.mycompany.myapp.service.dto.CoupomFormDTO;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.CoupomForm}.
 */
@RestController
@RequestMapping("/api")
public class CoupomFormResource {

    private final Logger log = LoggerFactory.getLogger(CoupomFormResource.class);

    private static final String ENTITY_NAME = "coupomForm";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CoupomFormService coupomFormService;

    private final CoupomFormRepository coupomFormRepository;

    public CoupomFormResource(CoupomFormService coupomFormService, CoupomFormRepository coupomFormRepository) {
        this.coupomFormService = coupomFormService;
        this.coupomFormRepository = coupomFormRepository;
    }

    /**
     * {@code POST  /coupom-forms} : Create a new coupomForm.
     *
     * @param coupomFormDTO the coupomFormDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new coupomFormDTO, or with status {@code 400 (Bad Request)} if the coupomForm has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/coupom-forms")
    public ResponseEntity<CoupomFormDTO> createCoupomForm(@RequestBody CoupomFormDTO coupomFormDTO) throws URISyntaxException {
        log.debug("REST request to save CoupomForm : {}", coupomFormDTO);
        if (coupomFormDTO.getId() != null) {
            throw new BadRequestAlertException("A new coupomForm cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CoupomFormDTO result = coupomFormService.save(coupomFormDTO);
        return ResponseEntity
            .created(new URI("/api/coupom-forms/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /coupom-forms/:id} : Updates an existing coupomForm.
     *
     * @param id the id of the coupomFormDTO to save.
     * @param coupomFormDTO the coupomFormDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated coupomFormDTO,
     * or with status {@code 400 (Bad Request)} if the coupomFormDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the coupomFormDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/coupom-forms/{id}")
    public ResponseEntity<CoupomFormDTO> updateCoupomForm(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody CoupomFormDTO coupomFormDTO
    ) throws URISyntaxException {
        log.debug("REST request to update CoupomForm : {}, {}", id, coupomFormDTO);
        if (coupomFormDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, coupomFormDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!coupomFormRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        CoupomFormDTO result = coupomFormService.save(coupomFormDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, coupomFormDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /coupom-forms/:id} : Partial updates given fields of an existing coupomForm, field will ignore if it is null
     *
     * @param id the id of the coupomFormDTO to save.
     * @param coupomFormDTO the coupomFormDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated coupomFormDTO,
     * or with status {@code 400 (Bad Request)} if the coupomFormDTO is not valid,
     * or with status {@code 404 (Not Found)} if the coupomFormDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the coupomFormDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/coupom-forms/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<CoupomFormDTO> partialUpdateCoupomForm(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody CoupomFormDTO coupomFormDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update CoupomForm partially : {}, {}", id, coupomFormDTO);
        if (coupomFormDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, coupomFormDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!coupomFormRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<CoupomFormDTO> result = coupomFormService.partialUpdate(coupomFormDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, coupomFormDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /coupom-forms} : get all the coupomForms.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of coupomForms in body.
     */
    @GetMapping("/coupom-forms")
    public List<CoupomFormDTO> getAllCoupomForms() {
        log.debug("REST request to get all CoupomForms");
        return coupomFormService.findAll();
    }

    /**
     * {@code GET  /coupom-forms/:id} : get the "id" coupomForm.
     *
     * @param id the id of the coupomFormDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the coupomFormDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/coupom-forms/{id}")
    public ResponseEntity<CoupomFormDTO> getCoupomForm(@PathVariable Long id) {
        log.debug("REST request to get CoupomForm : {}", id);
        Optional<CoupomFormDTO> coupomFormDTO = coupomFormService.findOne(id);
        return ResponseUtil.wrapOrNotFound(coupomFormDTO);
    }

    /**
     * {@code DELETE  /coupom-forms/:id} : delete the "id" coupomForm.
     *
     * @param id the id of the coupomFormDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/coupom-forms/{id}")
    public ResponseEntity<Void> deleteCoupomForm(@PathVariable Long id) {
        log.debug("REST request to delete CoupomForm : {}", id);
        coupomFormService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}

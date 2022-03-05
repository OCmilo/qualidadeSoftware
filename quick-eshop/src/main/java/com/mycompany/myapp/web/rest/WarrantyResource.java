package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.repository.WarrantyRepository;
import com.mycompany.myapp.service.WarrantyService;
import com.mycompany.myapp.service.dto.WarrantyDTO;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.Warranty}.
 */
@RestController
@RequestMapping("/api")
public class WarrantyResource {

    private final Logger log = LoggerFactory.getLogger(WarrantyResource.class);

    private static final String ENTITY_NAME = "warranty";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final WarrantyService warrantyService;

    private final WarrantyRepository warrantyRepository;

    public WarrantyResource(WarrantyService warrantyService, WarrantyRepository warrantyRepository) {
        this.warrantyService = warrantyService;
        this.warrantyRepository = warrantyRepository;
    }

    /**
     * {@code POST  /warranties} : Create a new warranty.
     *
     * @param warrantyDTO the warrantyDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new warrantyDTO, or with status {@code 400 (Bad Request)} if the warranty has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/warranties")
    public ResponseEntity<WarrantyDTO> createWarranty(@RequestBody WarrantyDTO warrantyDTO) throws URISyntaxException {
        log.debug("REST request to save Warranty : {}", warrantyDTO);
        if (warrantyDTO.getId() != null) {
            throw new BadRequestAlertException("A new warranty cannot already have an ID", ENTITY_NAME, "idexists");
        }
        WarrantyDTO result = warrantyService.save(warrantyDTO);
        return ResponseEntity
            .created(new URI("/api/warranties/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /warranties/:id} : Updates an existing warranty.
     *
     * @param id the id of the warrantyDTO to save.
     * @param warrantyDTO the warrantyDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated warrantyDTO,
     * or with status {@code 400 (Bad Request)} if the warrantyDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the warrantyDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/warranties/{id}")
    public ResponseEntity<WarrantyDTO> updateWarranty(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody WarrantyDTO warrantyDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Warranty : {}, {}", id, warrantyDTO);
        if (warrantyDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, warrantyDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!warrantyRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        WarrantyDTO result = warrantyService.save(warrantyDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, warrantyDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /warranties/:id} : Partial updates given fields of an existing warranty, field will ignore if it is null
     *
     * @param id the id of the warrantyDTO to save.
     * @param warrantyDTO the warrantyDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated warrantyDTO,
     * or with status {@code 400 (Bad Request)} if the warrantyDTO is not valid,
     * or with status {@code 404 (Not Found)} if the warrantyDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the warrantyDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/warranties/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<WarrantyDTO> partialUpdateWarranty(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody WarrantyDTO warrantyDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Warranty partially : {}, {}", id, warrantyDTO);
        if (warrantyDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, warrantyDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!warrantyRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<WarrantyDTO> result = warrantyService.partialUpdate(warrantyDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, warrantyDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /warranties} : get all the warranties.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of warranties in body.
     */
    @GetMapping("/warranties")
    public List<WarrantyDTO> getAllWarranties() {
        log.debug("REST request to get all Warranties");
        return warrantyService.findAll();
    }

    /**
     * {@code GET  /warranties/:id} : get the "id" warranty.
     *
     * @param id the id of the warrantyDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the warrantyDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/warranties/{id}")
    public ResponseEntity<WarrantyDTO> getWarranty(@PathVariable Long id) {
        log.debug("REST request to get Warranty : {}", id);
        Optional<WarrantyDTO> warrantyDTO = warrantyService.findOne(id);
        return ResponseUtil.wrapOrNotFound(warrantyDTO);
    }

    /**
     * {@code DELETE  /warranties/:id} : delete the "id" warranty.
     *
     * @param id the id of the warrantyDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/warranties/{id}")
    public ResponseEntity<Void> deleteWarranty(@PathVariable Long id) {
        log.debug("REST request to delete Warranty : {}", id);
        warrantyService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}

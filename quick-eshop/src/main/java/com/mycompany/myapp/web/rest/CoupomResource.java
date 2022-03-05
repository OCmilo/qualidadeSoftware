package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.repository.CoupomRepository;
import com.mycompany.myapp.service.CoupomService;
import com.mycompany.myapp.service.dto.CoupomDTO;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.Coupom}.
 */
@RestController
@RequestMapping("/api")
public class CoupomResource {

    private final Logger log = LoggerFactory.getLogger(CoupomResource.class);

    private static final String ENTITY_NAME = "coupom";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CoupomService coupomService;

    private final CoupomRepository coupomRepository;

    public CoupomResource(CoupomService coupomService, CoupomRepository coupomRepository) {
        this.coupomService = coupomService;
        this.coupomRepository = coupomRepository;
    }

    /**
     * {@code POST  /coupoms} : Create a new coupom.
     *
     * @param coupomDTO the coupomDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new coupomDTO, or with status {@code 400 (Bad Request)} if the coupom has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/coupoms")
    public ResponseEntity<CoupomDTO> createCoupom(@RequestBody CoupomDTO coupomDTO) throws URISyntaxException {
        log.debug("REST request to save Coupom : {}", coupomDTO);
        if (coupomDTO.getId() != null) {
            throw new BadRequestAlertException("A new coupom cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CoupomDTO result = coupomService.save(coupomDTO);
        return ResponseEntity
            .created(new URI("/api/coupoms/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /coupoms/:id} : Updates an existing coupom.
     *
     * @param id the id of the coupomDTO to save.
     * @param coupomDTO the coupomDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated coupomDTO,
     * or with status {@code 400 (Bad Request)} if the coupomDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the coupomDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/coupoms/{id}")
    public ResponseEntity<CoupomDTO> updateCoupom(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody CoupomDTO coupomDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Coupom : {}, {}", id, coupomDTO);
        if (coupomDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, coupomDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!coupomRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        CoupomDTO result = coupomService.save(coupomDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, coupomDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /coupoms/:id} : Partial updates given fields of an existing coupom, field will ignore if it is null
     *
     * @param id the id of the coupomDTO to save.
     * @param coupomDTO the coupomDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated coupomDTO,
     * or with status {@code 400 (Bad Request)} if the coupomDTO is not valid,
     * or with status {@code 404 (Not Found)} if the coupomDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the coupomDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/coupoms/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<CoupomDTO> partialUpdateCoupom(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody CoupomDTO coupomDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Coupom partially : {}, {}", id, coupomDTO);
        if (coupomDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, coupomDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!coupomRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<CoupomDTO> result = coupomService.partialUpdate(coupomDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, coupomDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /coupoms} : get all the coupoms.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of coupoms in body.
     */
    @GetMapping("/coupoms")
    public List<CoupomDTO> getAllCoupoms() {
        log.debug("REST request to get all Coupoms");
        return coupomService.findAll();
    }

    /**
     * {@code GET  /coupoms/:id} : get the "id" coupom.
     *
     * @param id the id of the coupomDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the coupomDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/coupoms/{id}")
    public ResponseEntity<CoupomDTO> getCoupom(@PathVariable Long id) {
        log.debug("REST request to get Coupom : {}", id);
        Optional<CoupomDTO> coupomDTO = coupomService.findOne(id);
        return ResponseUtil.wrapOrNotFound(coupomDTO);
    }

    /**
     * {@code DELETE  /coupoms/:id} : delete the "id" coupom.
     *
     * @param id the id of the coupomDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/coupoms/{id}")
    public ResponseEntity<Void> deleteCoupom(@PathVariable Long id) {
        log.debug("REST request to delete Coupom : {}", id);
        coupomService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}

package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.repository.FreightRepository;
import com.mycompany.myapp.service.FreightService;
import com.mycompany.myapp.service.dto.FreightDTO;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.Freight}.
 */
@RestController
@RequestMapping("/api")
public class FreightResource {

    private final Logger log = LoggerFactory.getLogger(FreightResource.class);

    private static final String ENTITY_NAME = "freight";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FreightService freightService;

    private final FreightRepository freightRepository;

    public FreightResource(FreightService freightService, FreightRepository freightRepository) {
        this.freightService = freightService;
        this.freightRepository = freightRepository;
    }

    /**
     * {@code POST  /freights} : Create a new freight.
     *
     * @param freightDTO the freightDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new freightDTO, or with status {@code 400 (Bad Request)} if the freight has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/freights")
    public ResponseEntity<FreightDTO> createFreight(@RequestBody FreightDTO freightDTO) throws URISyntaxException {
        log.debug("REST request to save Freight : {}", freightDTO);
        if (freightDTO.getId() != null) {
            throw new BadRequestAlertException("A new freight cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FreightDTO result = freightService.save(freightDTO);
        return ResponseEntity
            .created(new URI("/api/freights/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /freights/:id} : Updates an existing freight.
     *
     * @param id the id of the freightDTO to save.
     * @param freightDTO the freightDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated freightDTO,
     * or with status {@code 400 (Bad Request)} if the freightDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the freightDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/freights/{id}")
    public ResponseEntity<FreightDTO> updateFreight(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody FreightDTO freightDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Freight : {}, {}", id, freightDTO);
        if (freightDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, freightDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!freightRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        FreightDTO result = freightService.save(freightDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, freightDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /freights/:id} : Partial updates given fields of an existing freight, field will ignore if it is null
     *
     * @param id the id of the freightDTO to save.
     * @param freightDTO the freightDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated freightDTO,
     * or with status {@code 400 (Bad Request)} if the freightDTO is not valid,
     * or with status {@code 404 (Not Found)} if the freightDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the freightDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/freights/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<FreightDTO> partialUpdateFreight(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody FreightDTO freightDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Freight partially : {}, {}", id, freightDTO);
        if (freightDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, freightDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!freightRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<FreightDTO> result = freightService.partialUpdate(freightDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, freightDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /freights} : get all the freights.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of freights in body.
     */
    @GetMapping("/freights")
    public List<FreightDTO> getAllFreights() {
        log.debug("REST request to get all Freights");
        return freightService.findAll();
    }

    /**
     * {@code GET  /freights/:id} : get the "id" freight.
     *
     * @param id the id of the freightDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the freightDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/freights/{id}")
    public ResponseEntity<FreightDTO> getFreight(@PathVariable Long id) {
        log.debug("REST request to get Freight : {}", id);
        Optional<FreightDTO> freightDTO = freightService.findOne(id);
        return ResponseUtil.wrapOrNotFound(freightDTO);
    }

    /**
     * {@code DELETE  /freights/:id} : delete the "id" freight.
     *
     * @param id the id of the freightDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/freights/{id}")
    public ResponseEntity<Void> deleteFreight(@PathVariable Long id) {
        log.debug("REST request to delete Freight : {}", id);
        freightService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}

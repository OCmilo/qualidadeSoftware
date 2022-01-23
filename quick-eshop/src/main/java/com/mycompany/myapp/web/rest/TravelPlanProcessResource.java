package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.TravelPlanProcessService;
import com.mycompany.myapp.service.dto.TravelPlanProcessDTO;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.mycompany.myapp.domain.TravelPlanProcess}.
 */
@RestController
@RequestMapping("/api")
public class TravelPlanProcessResource {

    private final Logger log = LoggerFactory.getLogger(TravelPlanProcessResource.class);

    private static final String ENTITY_NAME = "travelPlanProcess";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TravelPlanProcessService travelPlanProcessService;

    public TravelPlanProcessResource(TravelPlanProcessService travelPlanProcessService) {
        this.travelPlanProcessService = travelPlanProcessService;
    }

    /**
     * {@code POST  /travel-plan-processes} : Create a new travelPlanProcess.
     *
     * @param travelPlanProcessDTO the travelPlanProcessDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new travelPlanProcessDTO, or with status {@code 400 (Bad Request)} if the travelPlanProcess has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/travel-plan-processes")
    public ResponseEntity<TravelPlanProcessDTO> create(@RequestBody TravelPlanProcessDTO travelPlanProcessDTO) throws URISyntaxException {
        log.debug("REST request to save TravelPlanProcess : {}", travelPlanProcessDTO);
        if (travelPlanProcessDTO.getId() != null) {
            throw new BadRequestAlertException("A new travelPlanProcess cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TravelPlanProcessDTO result = travelPlanProcessService.create(travelPlanProcessDTO);
        return ResponseEntity
            .created(new URI("/api/travel-plan-processes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /travel-plan-processes} : get all the travelPlanProcesss.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of travelPlanProcesss in body.
     */
    @GetMapping("/travel-plan-processes")
    public List<TravelPlanProcessDTO> getAllTravelPlanProcesss() {
        log.debug("REST request to get all TravelPlanProcesss");
        return travelPlanProcessService.findAll();
    }

    /**
     * {@code GET  /travel-plan-processes/:id} : get the "id" travelPlanProcess.
     *
     * @param processInstanceId the id of the travelPlanProcessDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the travelPlanProcessDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/travel-plan-processes/{processInstanceId}")
    public ResponseEntity<TravelPlanProcessDTO> getByProcessInstanceId(@PathVariable Long processInstanceId) {
        log.debug("REST request to get TravelPlanProcess by processInstanceId : {}", processInstanceId);
        Optional<TravelPlanProcessDTO> travelPlanProcessDTO = travelPlanProcessService.findByProcessInstanceId(processInstanceId);
        return ResponseUtil.wrapOrNotFound(travelPlanProcessDTO);
    }
}

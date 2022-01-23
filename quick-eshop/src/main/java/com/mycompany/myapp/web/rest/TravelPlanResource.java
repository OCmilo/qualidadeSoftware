package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.repository.TravelPlanRepository;
import com.mycompany.myapp.service.TravelPlanService;
import com.mycompany.myapp.service.dto.TravelPlanDTO;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.TravelPlan}.
 */
@RestController
@RequestMapping("/api")
public class TravelPlanResource {

    private final Logger log = LoggerFactory.getLogger(TravelPlanResource.class);

    private final TravelPlanService travelPlanService;

    private final TravelPlanRepository travelPlanRepository;

    public TravelPlanResource(TravelPlanService travelPlanService, TravelPlanRepository travelPlanRepository) {
        this.travelPlanService = travelPlanService;
        this.travelPlanRepository = travelPlanRepository;
    }

    /**
     * {@code GET  /travel-plans} : get all the travelPlans.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of travelPlans in body.
     */
    @GetMapping("/travel-plans")
    public List<TravelPlanDTO> getAllTravelPlans() {
        log.debug("REST request to get all TravelPlans");
        return travelPlanService.findAll();
    }

    /**
     * {@code GET  /travel-plans/:id} : get the "id" travelPlan.
     *
     * @param id the id of the travelPlanDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the travelPlanDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/travel-plans/{id}")
    public ResponseEntity<TravelPlanDTO> getTravelPlan(@PathVariable Long id) {
        log.debug("REST request to get TravelPlan : {}", id);
        Optional<TravelPlanDTO> travelPlanDTO = travelPlanService.findOne(id);
        return ResponseUtil.wrapOrNotFound(travelPlanDTO);
    }
}

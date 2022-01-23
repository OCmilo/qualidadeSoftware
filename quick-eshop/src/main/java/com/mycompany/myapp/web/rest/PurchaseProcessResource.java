package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.PurchaseProcessService;
import com.mycompany.myapp.service.dto.PurchaseProcessDTO;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.PurchaseProcess}.
 */
@RestController
@RequestMapping("/api")
public class PurchaseProcessResource {

    private final Logger log = LoggerFactory.getLogger(PurchaseProcessResource.class);

    private static final String ENTITY_NAME = "purchaseProcess";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PurchaseProcessService purchaseProcessService;

    public PurchaseProcessResource(PurchaseProcessService purchaseProcessService) {
        this.purchaseProcessService = purchaseProcessService;
    }

    /**
     * {@code POST  /purchase-processes} : Create a new purchaseProcess.
     *
     * @param purchaseProcessDTO the purchaseProcessDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new purchaseProcessDTO, or with status {@code 400 (Bad Request)} if the purchaseProcess has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/purchase-processes")
    public ResponseEntity<PurchaseProcessDTO> create(@RequestBody PurchaseProcessDTO purchaseProcessDTO) throws URISyntaxException {
        log.debug("REST request to save PurchaseProcess : {}", purchaseProcessDTO);
        if (purchaseProcessDTO.getId() != null) {
            throw new BadRequestAlertException("A new purchaseProcess cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PurchaseProcessDTO result = purchaseProcessService.create(purchaseProcessDTO);
        return ResponseEntity
            .created(new URI("/api/purchase-processes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /purchase-processes} : get all the purchaseProcesss.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of purchaseProcesss in body.
     */
    @GetMapping("/purchase-processes")
    public List<PurchaseProcessDTO> getAllPurchaseProcesss() {
        log.debug("REST request to get all PurchaseProcesss");
        return purchaseProcessService.findAll();
    }

    /**
     * {@code GET  /purchase-processes/:id} : get the "id" purchaseProcess.
     *
     * @param processInstanceId the id of the purchaseProcessDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the purchaseProcessDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/purchase-processes/{processInstanceId}")
    public ResponseEntity<PurchaseProcessDTO> getByProcessInstanceId(@PathVariable Long processInstanceId) {
        log.debug("REST request to get PurchaseProcess by processInstanceId : {}", processInstanceId);
        Optional<PurchaseProcessDTO> purchaseProcessDTO = purchaseProcessService.findByProcessInstanceId(processInstanceId);
        return ResponseUtil.wrapOrNotFound(purchaseProcessDTO);
    }
}

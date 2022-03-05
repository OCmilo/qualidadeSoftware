package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.repository.PurchaseRepository;
import com.mycompany.myapp.service.PurchaseService;
import com.mycompany.myapp.service.dto.PurchaseDTO;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.Purchase}.
 */
@RestController
@RequestMapping("/api")
public class PurchaseResource {

    private final Logger log = LoggerFactory.getLogger(PurchaseResource.class);

    private final PurchaseService purchaseService;

    private final PurchaseRepository purchaseRepository;

    public PurchaseResource(PurchaseService purchaseService, PurchaseRepository purchaseRepository) {
        this.purchaseService = purchaseService;
        this.purchaseRepository = purchaseRepository;
    }

    /**
     * {@code GET  /purchases} : get all the purchases.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of purchases in body.
     */
    @GetMapping("/purchases")
    public List<PurchaseDTO> getAllPurchases() {
        log.debug("REST request to get all Purchases");
        return purchaseService.findAll();
    }

    /**
     * {@code GET  /purchases/:id} : get the "id" purchase.
     *
     * @param id the id of the purchaseDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the purchaseDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/purchases/{id}")
    public ResponseEntity<PurchaseDTO> getPurchase(@PathVariable Long id) {
        log.debug("REST request to get Purchase : {}", id);
        Optional<PurchaseDTO> purchaseDTO = purchaseService.findOne(id);
        return ResponseUtil.wrapOrNotFound(purchaseDTO);
    }
}

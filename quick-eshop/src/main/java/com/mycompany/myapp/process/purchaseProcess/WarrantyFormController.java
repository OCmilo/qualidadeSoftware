package com.mycompany.myapp.process.purchaseProcess;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/purchase-process/warranty-form")
public class WarrantyFormController {

    private final Logger log = LoggerFactory.getLogger(WarrantyFormController.class);

    private final WarrantyFormService warrantyFormService;

    public WarrantyFormController(WarrantyFormService warrantyFormService) {
        this.warrantyFormService = warrantyFormService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<WarrantyFormContextDTO> loadContext(@PathVariable Long id) {
        log.debug("REST request to load the context of task hotel {}", id);
        WarrantyFormContextDTO warrantyFormContext = warrantyFormService.loadContext(id);
        return ResponseEntity.ok(warrantyFormContext);
    }

    @GetMapping("/{id}/claim")
    public ResponseEntity<WarrantyFormContextDTO> claim(@PathVariable Long id) {
        log.debug("REST request to load the context of task hotel {}", id);
        WarrantyFormContextDTO warrantyFormContext = warrantyFormService.claim(id);
        return ResponseEntity.ok(warrantyFormContext);
    }

    @PostMapping("/complete")
    public ResponseEntity<Void> complete(@RequestBody WarrantyFormContextDTO warrantyFormContext) {
        log.debug("REST request to complete PurchaseProcess.WarrantyForm {}", warrantyFormContext.getTaskInstance().getId());
        warrantyFormService.complete(warrantyFormContext);
        return ResponseEntity.noContent().build();
    }
}

package com.mycompany.myapp.process.purchaseProcess;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/purchase-process/coupom-form")
public class CoupomFormController {

    private final Logger log = LoggerFactory.getLogger(CoupomFormController.class);

    private final CoupomFormService coupomFormService;

    public CoupomFormController(CoupomFormService coupomFormService) {
        this.coupomFormService = coupomFormService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CoupomFormContextDTO> loadContext(@PathVariable Long id) {
        log.debug("REST request to load the context of task hotel {}", id);
        CoupomFormContextDTO coupomFormContext = coupomFormService.loadContext(id);
        return ResponseEntity.ok(coupomFormContext);
    }

    @GetMapping("/{id}/claim")
    public ResponseEntity<CoupomFormContextDTO> claim(@PathVariable Long id) {
        log.debug("REST request to load the context of task hotel {}", id);
        CoupomFormContextDTO coupomFormContext = coupomFormService.claim(id);
        return ResponseEntity.ok(coupomFormContext);
    }

    @PostMapping("/complete")
    public ResponseEntity<Void> complete(@RequestBody CoupomFormContextDTO coupomFormContext) {
        log.debug("REST request to complete PurchaseProcess.CoupomForm {}", coupomFormContext.getTaskInstance().getId());
        coupomFormService.complete(coupomFormContext);
        return ResponseEntity.noContent().build();
    }
}

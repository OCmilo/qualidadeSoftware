package com.mycompany.myapp.process.purchaseProcess;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/purchase-process/freight-form")
public class FreightFormController {

    private final Logger log = LoggerFactory.getLogger(FreightFormController.class);

    private final FreightFormService freightFormService;

    public FreightFormController(FreightFormService freightFormService) {
        this.freightFormService = freightFormService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<FreightFormContextDTO> loadContext(@PathVariable Long id) {
        log.debug("REST request to load the context of task hotel {}", id);
        FreightFormContextDTO freightFormContext = freightFormService.loadContext(id);
        return ResponseEntity.ok(freightFormContext);
    }

    @GetMapping("/{id}/claim")
    public ResponseEntity<FreightFormContextDTO> claim(@PathVariable Long id) {
        log.debug("REST request to load the context of task hotel {}", id);
        FreightFormContextDTO freightFormContext = freightFormService.claim(id);
        return ResponseEntity.ok(freightFormContext);
    }

    @PostMapping("/complete")
    public ResponseEntity<Void> complete(@RequestBody FreightFormContextDTO freightFormContext) {
        log.debug("REST request to complete PurchaseProcess.FreightForm {}", freightFormContext.getTaskInstance().getId());
        freightFormService.complete(freightFormContext);
        return ResponseEntity.noContent().build();
    }
}

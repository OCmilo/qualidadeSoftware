package com.mycompany.myapp.process.purchaseProcess;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/purchase-process/proceed-checkout")
public class ProceedCheckoutController {

    private final Logger log = LoggerFactory.getLogger(ProceedCheckoutController.class);

    private final ProceedCheckoutService proceedCheckoutService;

    public ProceedCheckoutController(ProceedCheckoutService proceedCheckoutService) {
        this.proceedCheckoutService = proceedCheckoutService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProceedCheckoutContextDTO> loadContext(@PathVariable Long id) {
        log.debug("REST request to load the context of task hotel {}", id);
        ProceedCheckoutContextDTO proceedCheckoutContext = proceedCheckoutService.loadContext(id);
        return ResponseEntity.ok(proceedCheckoutContext);
    }

    @GetMapping("/{id}/claim")
    public ResponseEntity<ProceedCheckoutContextDTO> claim(@PathVariable Long id) {
        log.debug("REST request to load the context of task hotel {}", id);
        ProceedCheckoutContextDTO proceedCheckoutContext = proceedCheckoutService.claim(id);
        return ResponseEntity.ok(proceedCheckoutContext);
    }

    @PostMapping("/complete")
    public ResponseEntity<Void> complete(@RequestBody ProceedCheckoutContextDTO proceedCheckoutContext) {
        log.debug("REST request to complete PurchaseProcess.ProceedCheckout {}", proceedCheckoutContext.getTaskInstance().getId());
        proceedCheckoutService.complete(proceedCheckoutContext);
        return ResponseEntity.noContent().build();
    }
}

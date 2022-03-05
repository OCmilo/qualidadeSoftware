package com.mycompany.myapp.process.purchaseProcess;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/purchase-process/choose-product")
public class ChooseProductController {

    private final Logger log = LoggerFactory.getLogger(ChooseProductController.class);

    private final ChooseProductService chooseProductService;

    public ChooseProductController(ChooseProductService chooseProductService) {
        this.chooseProductService = chooseProductService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChooseProductContextDTO> loadContext(@PathVariable Long id) {
        log.debug("REST request to load the context of task hotel {}", id);
        ChooseProductContextDTO chooseProductContext = chooseProductService.loadContext(id);
        return ResponseEntity.ok(chooseProductContext);
    }

    @GetMapping("/{id}/claim")
    public ResponseEntity<ChooseProductContextDTO> claim(@PathVariable Long id) {
        log.debug("REST request to load the context of task hotel {}", id);
        ChooseProductContextDTO chooseProductContext = chooseProductService.claim(id);
        return ResponseEntity.ok(chooseProductContext);
    }

    @PostMapping("/complete")
    public ResponseEntity<Void> complete(@RequestBody ChooseProductContextDTO chooseProductContext) {
        log.debug("REST request to complete PurchaseProcess.ChooseProduct {}", chooseProductContext.getTaskInstance().getId());
        chooseProductService.complete(chooseProductContext);
        return ResponseEntity.noContent().build();
    }
}

package com.mycompany.myapp.process.purchaseProcess;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/purchase-process/choose-freight")
public class ChooseFreightController {

    private final Logger log = LoggerFactory.getLogger(ChooseFreightController.class);

    private final ChooseFreightService chooseFreightService;

    public ChooseFreightController(ChooseFreightService chooseFreightService) {
        this.chooseFreightService = chooseFreightService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChooseFreightContextDTO> loadContext(@PathVariable Long id) {
        log.debug("REST request to load the context of task hotel {}", id);
        ChooseFreightContextDTO chooseFreightContext = chooseFreightService.loadContext(id);
        return ResponseEntity.ok(chooseFreightContext);
    }

    @GetMapping("/{id}/claim")
    public ResponseEntity<ChooseFreightContextDTO> claim(@PathVariable Long id) {
        log.debug("REST request to load the context of task hotel {}", id);
        ChooseFreightContextDTO chooseFreightContext = chooseFreightService.claim(id);
        return ResponseEntity.ok(chooseFreightContext);
    }

    @PostMapping("/complete")
    public ResponseEntity<Void> complete(@RequestBody ChooseFreightContextDTO chooseFreightContext) {
        log.debug("REST request to complete PurchaseProcess.ChooseFreight {}", chooseFreightContext.getTaskInstance().getId());
        chooseFreightService.complete(chooseFreightContext);
        return ResponseEntity.noContent().build();
    }
}

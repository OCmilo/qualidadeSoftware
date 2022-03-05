package com.mycompany.myapp.process.purchaseProcess;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/purchase-process/add-warranty")
public class AddWarrantyController {

    private final Logger log = LoggerFactory.getLogger(AddWarrantyController.class);

    private final AddWarrantyService addWarrantyService;

    public AddWarrantyController(AddWarrantyService addWarrantyService) {
        this.addWarrantyService = addWarrantyService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddWarrantyContextDTO> loadContext(@PathVariable Long id) {
        log.debug("REST request to load the context of task hotel {}", id);
        AddWarrantyContextDTO addWarrantyContext = addWarrantyService.loadContext(id);
        return ResponseEntity.ok(addWarrantyContext);
    }

    @GetMapping("/{id}/claim")
    public ResponseEntity<AddWarrantyContextDTO> claim(@PathVariable Long id) {
        log.debug("REST request to load the context of task hotel {}", id);
        AddWarrantyContextDTO addWarrantyContext = addWarrantyService.claim(id);
        return ResponseEntity.ok(addWarrantyContext);
    }

    @PostMapping("/complete")
    public ResponseEntity<Void> complete(@RequestBody AddWarrantyContextDTO addWarrantyContext) {
        log.debug("REST request to complete PurchaseProcess.AddWarranty {}", addWarrantyContext.getTaskInstance().getId());
        addWarrantyService.complete(addWarrantyContext);
        return ResponseEntity.noContent().build();
    }
}

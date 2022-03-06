package com.mycompany.myapp.process.purchaseProcess;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/purchase-process/add-products")
public class AddProductsController {

    private final Logger log = LoggerFactory.getLogger(AddProductsController.class);

    private final AddProductsService addProductsService;

    public AddProductsController(AddProductsService addProductsService) {
        this.addProductsService = addProductsService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddProductsContextDTO> loadContext(@PathVariable Long id) {
        log.debug("REST request to load the context of task hotel {}", id);
        AddProductsContextDTO addProductsContext = addProductsService.loadContext(id);
        return ResponseEntity.ok(addProductsContext);
    }

    @GetMapping("/{id}/claim")
    public ResponseEntity<AddProductsContextDTO> claim(@PathVariable Long id) {
        log.debug("REST request to load the context of task hotel {}", id);
        AddProductsContextDTO addProductsContext = addProductsService.claim(id);
        return ResponseEntity.ok(addProductsContext);
    }

    @PostMapping("/complete")
    public ResponseEntity<Void> complete(@RequestBody AddProductsContextDTO addProductsContext) {
        log.debug("REST request to complete PurchaseProcess.AddProducts {}", addProductsContext.getTaskInstance().getId());
        addProductsService.complete(addProductsContext);
        return ResponseEntity.noContent().build();
    }
}

package com.mycompany.myapp.process.purchaseProcess;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/purchase-process/product-form")
public class ProductFormController {

    private final Logger log = LoggerFactory.getLogger(ProductFormController.class);

    private final ProductFormService productFormService;

    public ProductFormController(ProductFormService productFormService) {
        this.productFormService = productFormService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductFormContextDTO> loadContext(@PathVariable Long id) {
        log.debug("REST request to load the context of task hotel {}", id);
        ProductFormContextDTO productFormContext = productFormService.loadContext(id);
        return ResponseEntity.ok(productFormContext);
    }

    @GetMapping("/{id}/claim")
    public ResponseEntity<ProductFormContextDTO> claim(@PathVariable Long id) {
        log.debug("REST request to load the context of task hotel {}", id);
        ProductFormContextDTO productFormContext = productFormService.claim(id);
        return ResponseEntity.ok(productFormContext);
    }

    @PostMapping("/complete")
    public ResponseEntity<Void> complete(@RequestBody ProductFormContextDTO productFormContext) {
        log.debug("REST request to complete PurchaseProcess.ProductForm {}", productFormContext.getTaskInstance().getId());
        productFormService.complete(productFormContext);
        return ResponseEntity.noContent().build();
    }
}

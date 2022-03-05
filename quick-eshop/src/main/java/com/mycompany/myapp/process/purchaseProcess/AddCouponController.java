package com.mycompany.myapp.process.purchaseProcess;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/purchase-process/add-coupon")
public class AddCouponController {

    private final Logger log = LoggerFactory.getLogger(AddCouponController.class);

    private final AddCouponService addCouponService;

    public AddCouponController(AddCouponService addCouponService) {
        this.addCouponService = addCouponService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddCouponContextDTO> loadContext(@PathVariable Long id) {
        log.debug("REST request to load the context of task hotel {}", id);
        AddCouponContextDTO addCouponContext = addCouponService.loadContext(id);
        return ResponseEntity.ok(addCouponContext);
    }

    @GetMapping("/{id}/claim")
    public ResponseEntity<AddCouponContextDTO> claim(@PathVariable Long id) {
        log.debug("REST request to load the context of task hotel {}", id);
        AddCouponContextDTO addCouponContext = addCouponService.claim(id);
        return ResponseEntity.ok(addCouponContext);
    }

    @PostMapping("/complete")
    public ResponseEntity<Void> complete(@RequestBody AddCouponContextDTO addCouponContext) {
        log.debug("REST request to complete PurchaseProcess.AddCoupon {}", addCouponContext.getTaskInstance().getId());
        addCouponService.complete(addCouponContext);
        return ResponseEntity.noContent().build();
    }
}

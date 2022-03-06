package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.PurchaseDTO;
import org.springframework.stereotype.Component;

@Component
public class ResumeService {

    public void showResume(PurchaseDTO purchaseDTO) {
        System.out.println(" ###########################################");
        System.out.println(" Product: " + purchaseDTO.getProduct().getProductName());
        System.out.println(" Quantity: " + purchaseDTO.getQuantity());
        System.out.println(" Freight: " + purchaseDTO.getFreight().getFreighter());
        System.out.println(" ###########################################");
        System.out.println(" ###########################################");
        System.out.println(" ###########################################");
        System.out.println(" ###########################################");
        System.out.println(" ###########################################\n\n\n");
    }
}

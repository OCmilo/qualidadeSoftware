package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.PurchaseDTO;
import java.util.ArrayList;
import org.springframework.stereotype.Component;

@Component
public class ResumeService {

    public void showResume(PurchaseDTO purchaseDTO) {
        System.out.println(" ###########################################");
        for (int i = 0; i < AddToChartService.arrProducts.size(); i += 4) {
            System.out.println(" ###########################################");
            System.out.println(" Product: " + AddToChartService.arrProducts.get(i));
            System.out.println(" Price: " + AddToChartService.arrProducts.get(i + 1));
            System.out.println(" Quantity: " + AddToChartService.arrProducts.get(i + 2));
            System.out.println(" Warranty: " + AddToChartService.arrProducts.get(i + 3));
            System.out.println(" Freight: " + purchaseDTO.getFreight().getFreighter());
            System.out.println(" ###########################################");
        }
        System.out.println(" ###########################################");
        System.out.println(" ###########################################");
        System.out.println(" ###########################################");
        System.out.println(" ###########################################");
        System.out.println(" ###########################################\n\n\n");
    }
}

package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.PurchaseDTO;
import java.util.ArrayList;
import org.springframework.stereotype.Component;

@Component
public class AddToChartService {

    static ArrayList<Object> arrProducts = new ArrayList<Object>();

    public static void addChart(PurchaseDTO purchaseDTO) {
        arrProducts.add(purchaseDTO.getProduct().getProductName());
        arrProducts.add(purchaseDTO.getProduct().getPrice());
        arrProducts.add(purchaseDTO.getQuantity());
        if (purchaseDTO.getWithWarranty()) {
            arrProducts.add(purchaseDTO.getWarranty().getWarrantyDescription());
        } else {
            arrProducts.add("Sem garantia");
        }
    }
    /*public void decrementStorage(PurchaseDTO purchaseDTO){
        purchaseDTO.getProduct().setAvailableQuantity(purchaseDTO.getProduct().getAvailableQuantity() - purchaseDTO.getQuantity());
    }*/
}

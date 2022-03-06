package com.mycompany.myapp.delegate;

import com.mycompany.myapp.service.AddToChartService;
import com.mycompany.myapp.service.dto.PurchaseProcessDTO;
import java.util.ArrayList;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddToChart implements JavaDelegate {

    @Autowired
    AddToChartService atcService;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        PurchaseProcessDTO purchaseProcess = (PurchaseProcessDTO) delegateExecution.getVariable("processInstance");

        //Confirming the flight
        atcService.addChart(purchaseProcess.getPurchase());
        // registra retirada do produto do estoque
        //atcService.decrementStorage(purchaseProcess.getPurchase());
    }
}

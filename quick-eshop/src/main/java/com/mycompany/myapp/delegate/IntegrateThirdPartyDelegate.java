package com.mycompany.myapp.delegate;

import com.mycompany.myapp.service.ResumeService;
import com.mycompany.myapp.service.dto.PurchaseProcessDTO;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class IntegrateThirdPartyDelegate implements JavaDelegate {

    @Autowired
    ResumeService resumeService;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        PurchaseProcessDTO purchaseProcess = (PurchaseProcessDTO) delegateExecution.getVariable("processInstance");

        //Confirming the flight
        resumeService.showResume(purchaseProcess.getPurchase());
    }
}

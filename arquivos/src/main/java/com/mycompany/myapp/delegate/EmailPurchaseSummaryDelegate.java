package com.mycompany.myapp.delegate;

import com.mycompany.myapp.service.dto.PurchaseDTO;
import com.mycompany.myapp.service.dto.PurchaseProcessDTO;
import com.mycompany.myapp.service.MailService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import java.util.Locale;


@Component
public class EmailPurchaseSummaryDelegate implements JavaDelegate {

    @Autowired
    MailService mailService;

    @Autowired
    SpringTemplateEngine templateEngine;


    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        PurchaseProcessDTO purchaseProcess = (PurchaseProcessDTO) delegateExecution.getVariable("processInstance");
        PurchaseDTO purchase = purchaseProcess.getPurchase();

        Double total = 10.0;

        String dest = purchase.getUserEmail();
        String subject = "[Quick eShop] Summary of your order number " + purchase.getId().toString();

        Context context = new Context(Locale.getDefault());
        context.setVariable("purchase", purchase);
        context.setVariable("total", total);
        String content = templateEngine.process("purchaseProcess/purchaseSummaryEmail", context);

        mailService.sendEmail(dest, subject, content, false, true);

    }
}

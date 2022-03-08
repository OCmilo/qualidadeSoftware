package com.mycompany.myapp.process.purchaseProcess;

import com.mycompany.myapp.service.dto.PurchaseProcessDTO;
import org.akip.service.dto.TaskInstanceDTO;

public class AddProductsContextDTO {

    private PurchaseProcessDTO purchaseProcess;
    private TaskInstanceDTO taskInstance;

    public PurchaseProcessDTO getPurchaseProcess() {
        return purchaseProcess;
    }

    public void setPurchaseProcess(PurchaseProcessDTO purchaseProcess) {
        this.purchaseProcess = purchaseProcess;
    }

    public TaskInstanceDTO getTaskInstance() {
        return taskInstance;
    }

    public void setTaskInstance(TaskInstanceDTO taskInstance) {
        this.taskInstance = taskInstance;
    }
}

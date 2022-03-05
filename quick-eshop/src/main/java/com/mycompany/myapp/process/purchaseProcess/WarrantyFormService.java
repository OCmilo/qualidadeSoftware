package com.mycompany.myapp.process.purchaseProcess;

import com.mycompany.myapp.repository.PurchaseProcessRepository;
import com.mycompany.myapp.service.WarrantyService;
import com.mycompany.myapp.service.dto.PurchaseProcessDTO;
import com.mycompany.myapp.service.dto.WarrantyDTO;
import com.mycompany.myapp.service.mapper.PurchaseProcessMapper;
import org.akip.repository.TaskInstanceRepository;
import org.akip.service.TaskInstanceService;
import org.akip.service.dto.TaskInstanceDTO;
import org.akip.service.mapper.TaskInstanceMapper;
import org.springframework.stereotype.Component;

@Component
public class WarrantyFormService {

    private final TaskInstanceService taskInstanceService;

    private final WarrantyService warrantyService;

    private final TaskInstanceRepository taskInstanceRepository;

    private final PurchaseProcessRepository purchaseProcessRepository;

    private final TaskInstanceMapper taskInstanceMapper;

    private final WarrantyFormMapper warrantyFormMapper;

    private final PurchaseProcessMapper purchaseProcessMapper;

    public WarrantyFormService(
        TaskInstanceService taskInstanceService,
        WarrantyService warrantyService,
        TaskInstanceRepository taskInstanceRepository,
        PurchaseProcessRepository purchaseProcessRepository,
        TaskInstanceMapper taskInstanceMapper,
        WarrantyFormMapper warrantyFormMapper,
        PurchaseProcessMapper purchaseProcessMapper
    ) {
        this.taskInstanceService = taskInstanceService;
        this.warrantyService = warrantyService;
        this.taskInstanceRepository = taskInstanceRepository;
        this.purchaseProcessRepository = purchaseProcessRepository;
        this.taskInstanceMapper = taskInstanceMapper;
        this.warrantyFormMapper = warrantyFormMapper;
        this.purchaseProcessMapper = purchaseProcessMapper;
    }

    public WarrantyFormContextDTO loadContext(Long taskInstanceId) {
        TaskInstanceDTO taskInstanceDTO = taskInstanceRepository
            .findById(taskInstanceId)
            .map(taskInstanceMapper::toDTOLoadTaskContext)
            .orElseThrow();

        PurchaseProcessDTO purchaseProcess = purchaseProcessRepository
            .findByProcessInstanceId(taskInstanceDTO.getProcessInstance().getId())
            .map(warrantyFormMapper::toPurchaseProcessDTO)
            .orElseThrow();

        WarrantyFormContextDTO warrantyFormContext = new WarrantyFormContextDTO();
        warrantyFormContext.setTaskInstance(taskInstanceDTO);
        warrantyFormContext.setPurchaseProcess(purchaseProcess);

        return warrantyFormContext;
    }

    public WarrantyFormContextDTO claim(Long taskInstanceId) {
        taskInstanceService.claim(taskInstanceId);
        return loadContext(taskInstanceId);
    }

    public void save(WarrantyFormContextDTO warrantyFormContext) {
        WarrantyDTO warrantyDTO = warrantyService.findOne(warrantyFormContext.getPurchaseProcess().getWarranty().getId()).orElseThrow();
        warrantyDTO.setGarantia(warrantyFormContext.getPurchaseProcess().getWarranty().getGarantia());
        warrantyService.save(warrantyDTO);
    }

    public void complete(WarrantyFormContextDTO warrantyFormContext) {
        save(warrantyFormContext);
        PurchaseProcessDTO purchaseProcess = purchaseProcessRepository
            .findByProcessInstanceId(warrantyFormContext.getPurchaseProcess().getProcessInstance().getId())
            .map(purchaseProcessMapper::toDto)
            .orElseThrow();
        taskInstanceService.complete(warrantyFormContext.getTaskInstance(), purchaseProcess);
    }
}

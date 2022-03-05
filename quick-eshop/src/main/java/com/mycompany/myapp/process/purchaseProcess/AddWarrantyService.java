package com.mycompany.myapp.process.purchaseProcess;

import com.mycompany.myapp.repository.PurchaseProcessRepository;
import com.mycompany.myapp.service.PurchaseService;
import com.mycompany.myapp.service.dto.PurchaseDTO;
import com.mycompany.myapp.service.dto.PurchaseProcessDTO;
import com.mycompany.myapp.service.mapper.PurchaseProcessMapper;
import org.akip.repository.TaskInstanceRepository;
import org.akip.service.TaskInstanceService;
import org.akip.service.dto.TaskInstanceDTO;
import org.akip.service.mapper.TaskInstanceMapper;
import org.springframework.stereotype.Component;

@Component
public class AddWarrantyService {

    private final TaskInstanceService taskInstanceService;

    private final PurchaseService purchaseService;

    private final TaskInstanceRepository taskInstanceRepository;

    private final PurchaseProcessRepository purchaseProcessRepository;

    private final TaskInstanceMapper taskInstanceMapper;

    private final AddWarrantyMapper addWarrantyMapper;

    private final PurchaseProcessMapper purchaseProcessMapper;

    public AddWarrantyService(
        TaskInstanceService taskInstanceService,
        PurchaseService purchaseService,
        TaskInstanceRepository taskInstanceRepository,
        PurchaseProcessRepository purchaseProcessRepository,
        TaskInstanceMapper taskInstanceMapper,
        AddWarrantyMapper addWarrantyMapper,
        PurchaseProcessMapper purchaseProcessMapper
    ) {
        this.taskInstanceService = taskInstanceService;
        this.purchaseService = purchaseService;
        this.taskInstanceRepository = taskInstanceRepository;
        this.purchaseProcessRepository = purchaseProcessRepository;
        this.taskInstanceMapper = taskInstanceMapper;
        this.addWarrantyMapper = addWarrantyMapper;
        this.purchaseProcessMapper = purchaseProcessMapper;
    }

    public AddWarrantyContextDTO loadContext(Long taskInstanceId) {
        TaskInstanceDTO taskInstanceDTO = taskInstanceRepository
            .findById(taskInstanceId)
            .map(taskInstanceMapper::toDTOLoadTaskContext)
            .orElseThrow();

        PurchaseProcessDTO purchaseProcess = purchaseProcessRepository
            .findByProcessInstanceId(taskInstanceDTO.getProcessInstance().getId())
            .map(addWarrantyMapper::toPurchaseProcessDTO)
            .orElseThrow();

        AddWarrantyContextDTO addWarrantyContext = new AddWarrantyContextDTO();
        addWarrantyContext.setTaskInstance(taskInstanceDTO);
        addWarrantyContext.setPurchaseProcess(purchaseProcess);

        return addWarrantyContext;
    }

    public AddWarrantyContextDTO claim(Long taskInstanceId) {
        taskInstanceService.claim(taskInstanceId);
        return loadContext(taskInstanceId);
    }

    public void save(AddWarrantyContextDTO addWarrantyContext) {
        PurchaseDTO purchaseDTO = purchaseService.findOne(addWarrantyContext.getPurchaseProcess().getPurchase().getId()).orElseThrow();
        purchaseDTO.setWarranty(addWarrantyContext.getPurchaseProcess().getPurchase().getWarranty());
        purchaseService.save(purchaseDTO);
    }

    public void complete(AddWarrantyContextDTO addWarrantyContext) {
        save(addWarrantyContext);
        PurchaseProcessDTO purchaseProcess = purchaseProcessRepository
            .findByProcessInstanceId(addWarrantyContext.getPurchaseProcess().getProcessInstance().getId())
            .map(purchaseProcessMapper::toDto)
            .orElseThrow();
        taskInstanceService.complete(addWarrantyContext.getTaskInstance(), purchaseProcess);
    }
}

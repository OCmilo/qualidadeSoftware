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
public class ChooseProductService {

    private final TaskInstanceService taskInstanceService;

    private final PurchaseService purchaseService;

    private final TaskInstanceRepository taskInstanceRepository;

    private final PurchaseProcessRepository purchaseProcessRepository;

    private final TaskInstanceMapper taskInstanceMapper;

    private final ChooseProductMapper chooseProductMapper;

    private final PurchaseProcessMapper purchaseProcessMapper;

    public ChooseProductService(
        TaskInstanceService taskInstanceService,
        PurchaseService purchaseService,
        TaskInstanceRepository taskInstanceRepository,
        PurchaseProcessRepository purchaseProcessRepository,
        TaskInstanceMapper taskInstanceMapper,
        ChooseProductMapper chooseProductMapper,
        PurchaseProcessMapper purchaseProcessMapper
    ) {
        this.taskInstanceService = taskInstanceService;
        this.purchaseService = purchaseService;
        this.taskInstanceRepository = taskInstanceRepository;
        this.purchaseProcessRepository = purchaseProcessRepository;
        this.taskInstanceMapper = taskInstanceMapper;
        this.chooseProductMapper = chooseProductMapper;
        this.purchaseProcessMapper = purchaseProcessMapper;
    }

    public ChooseProductContextDTO loadContext(Long taskInstanceId) {
        TaskInstanceDTO taskInstanceDTO = taskInstanceRepository
            .findById(taskInstanceId)
            .map(taskInstanceMapper::toDTOLoadTaskContext)
            .orElseThrow();

        PurchaseProcessDTO purchaseProcess = purchaseProcessRepository
            .findByProcessInstanceId(taskInstanceDTO.getProcessInstance().getId())
            .map(chooseProductMapper::toPurchaseProcessDTO)
            .orElseThrow();

        ChooseProductContextDTO chooseProductContext = new ChooseProductContextDTO();
        chooseProductContext.setTaskInstance(taskInstanceDTO);
        chooseProductContext.setPurchaseProcess(purchaseProcess);

        return chooseProductContext;
    }

    public ChooseProductContextDTO claim(Long taskInstanceId) {
        taskInstanceService.claim(taskInstanceId);
        return loadContext(taskInstanceId);
    }

    public void save(ChooseProductContextDTO chooseProductContext) {
        PurchaseDTO purchaseDTO = purchaseService.findOne(chooseProductContext.getPurchaseProcess().getPurchase().getId()).orElseThrow();
        purchaseDTO.setQuantity(chooseProductContext.getPurchaseProcess().getPurchase().getQuantity());
        purchaseDTO.setWithWarranty(chooseProductContext.getPurchaseProcess().getPurchase().getWithWarranty());
        purchaseDTO.setProduct(chooseProductContext.getPurchaseProcess().getPurchase().getProduct());
        purchaseService.save(purchaseDTO);
    }

    public void complete(ChooseProductContextDTO chooseProductContext) {
        save(chooseProductContext);
        PurchaseProcessDTO purchaseProcess = purchaseProcessRepository
            .findByProcessInstanceId(chooseProductContext.getPurchaseProcess().getProcessInstance().getId())
            .map(purchaseProcessMapper::toDto)
            .orElseThrow();
        taskInstanceService.complete(chooseProductContext.getTaskInstance(), purchaseProcess);
    }
}

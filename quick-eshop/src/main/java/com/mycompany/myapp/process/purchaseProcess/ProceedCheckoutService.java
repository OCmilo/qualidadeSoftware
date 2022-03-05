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
public class ProceedCheckoutService {

    private final TaskInstanceService taskInstanceService;

    private final PurchaseService purchaseService;

    private final TaskInstanceRepository taskInstanceRepository;

    private final PurchaseProcessRepository purchaseProcessRepository;

    private final TaskInstanceMapper taskInstanceMapper;

    private final ProceedCheckoutMapper proceedCheckoutMapper;

    private final PurchaseProcessMapper purchaseProcessMapper;

    public ProceedCheckoutService(
        TaskInstanceService taskInstanceService,
        PurchaseService purchaseService,
        TaskInstanceRepository taskInstanceRepository,
        PurchaseProcessRepository purchaseProcessRepository,
        TaskInstanceMapper taskInstanceMapper,
        ProceedCheckoutMapper proceedCheckoutMapper,
        PurchaseProcessMapper purchaseProcessMapper
    ) {
        this.taskInstanceService = taskInstanceService;
        this.purchaseService = purchaseService;
        this.taskInstanceRepository = taskInstanceRepository;
        this.purchaseProcessRepository = purchaseProcessRepository;
        this.taskInstanceMapper = taskInstanceMapper;
        this.proceedCheckoutMapper = proceedCheckoutMapper;
        this.purchaseProcessMapper = purchaseProcessMapper;
    }

    public ProceedCheckoutContextDTO loadContext(Long taskInstanceId) {
        TaskInstanceDTO taskInstanceDTO = taskInstanceRepository
            .findById(taskInstanceId)
            .map(taskInstanceMapper::toDTOLoadTaskContext)
            .orElseThrow();

        PurchaseProcessDTO purchaseProcess = purchaseProcessRepository
            .findByProcessInstanceId(taskInstanceDTO.getProcessInstance().getId())
            .map(proceedCheckoutMapper::toPurchaseProcessDTO)
            .orElseThrow();

        ProceedCheckoutContextDTO proceedCheckoutContext = new ProceedCheckoutContextDTO();
        proceedCheckoutContext.setTaskInstance(taskInstanceDTO);
        proceedCheckoutContext.setPurchaseProcess(purchaseProcess);

        return proceedCheckoutContext;
    }

    public ProceedCheckoutContextDTO claim(Long taskInstanceId) {
        taskInstanceService.claim(taskInstanceId);
        return loadContext(taskInstanceId);
    }

    public void save(ProceedCheckoutContextDTO proceedCheckoutContext) {
        PurchaseDTO purchaseDTO = purchaseService.findOne(proceedCheckoutContext.getPurchaseProcess().getPurchase().getId()).orElseThrow();
        purchaseDTO.setConfirmacao(proceedCheckoutContext.getPurchaseProcess().getPurchase().getConfirmacao());
        purchaseService.save(purchaseDTO);
    }

    public void complete(ProceedCheckoutContextDTO proceedCheckoutContext) {
        save(proceedCheckoutContext);
        PurchaseProcessDTO purchaseProcess = purchaseProcessRepository
            .findByProcessInstanceId(proceedCheckoutContext.getPurchaseProcess().getProcessInstance().getId())
            .map(purchaseProcessMapper::toDto)
            .orElseThrow();
        taskInstanceService.complete(proceedCheckoutContext.getTaskInstance(), purchaseProcess);
    }
}

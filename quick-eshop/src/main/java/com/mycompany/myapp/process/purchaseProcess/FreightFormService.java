package com.mycompany.myapp.process.purchaseProcess;

import com.mycompany.myapp.repository.PurchaseProcessRepository;
import com.mycompany.myapp.service.FreightService;
import com.mycompany.myapp.service.dto.FreightDTO;
import com.mycompany.myapp.service.dto.PurchaseProcessDTO;
import com.mycompany.myapp.service.mapper.PurchaseProcessMapper;
import org.akip.repository.TaskInstanceRepository;
import org.akip.service.TaskInstanceService;
import org.akip.service.dto.TaskInstanceDTO;
import org.akip.service.mapper.TaskInstanceMapper;
import org.springframework.stereotype.Component;

@Component
public class FreightFormService {

    private final TaskInstanceService taskInstanceService;

    private final FreightService freightService;

    private final TaskInstanceRepository taskInstanceRepository;

    private final PurchaseProcessRepository purchaseProcessRepository;

    private final TaskInstanceMapper taskInstanceMapper;

    private final FreightFormMapper freightFormMapper;

    private final PurchaseProcessMapper purchaseProcessMapper;

    public FreightFormService(
        TaskInstanceService taskInstanceService,
        FreightService freightService,
        TaskInstanceRepository taskInstanceRepository,
        PurchaseProcessRepository purchaseProcessRepository,
        TaskInstanceMapper taskInstanceMapper,
        FreightFormMapper freightFormMapper,
        PurchaseProcessMapper purchaseProcessMapper
    ) {
        this.taskInstanceService = taskInstanceService;
        this.freightService = freightService;
        this.taskInstanceRepository = taskInstanceRepository;
        this.purchaseProcessRepository = purchaseProcessRepository;
        this.taskInstanceMapper = taskInstanceMapper;
        this.freightFormMapper = freightFormMapper;
        this.purchaseProcessMapper = purchaseProcessMapper;
    }

    public FreightFormContextDTO loadContext(Long taskInstanceId) {
        TaskInstanceDTO taskInstanceDTO = taskInstanceRepository
            .findById(taskInstanceId)
            .map(taskInstanceMapper::toDTOLoadTaskContext)
            .orElseThrow();

        PurchaseProcessDTO purchaseProcess = purchaseProcessRepository
            .findByProcessInstanceId(taskInstanceDTO.getProcessInstance().getId())
            .map(freightFormMapper::toPurchaseProcessDTO)
            .orElseThrow();

        FreightFormContextDTO freightFormContext = new FreightFormContextDTO();
        freightFormContext.setTaskInstance(taskInstanceDTO);
        freightFormContext.setPurchaseProcess(purchaseProcess);

        return freightFormContext;
    }

    public FreightFormContextDTO claim(Long taskInstanceId) {
        taskInstanceService.claim(taskInstanceId);
        return loadContext(taskInstanceId);
    }

    public void save(FreightFormContextDTO freightFormContext) {
        FreightDTO freightDTO = freightService.findOne(freightFormContext.getPurchaseProcess().getFreight().getId()).orElseThrow();
        freightDTO.setFrete(freightFormContext.getPurchaseProcess().getFreight().getFrete());
        freightService.save(freightDTO);
    }

    public void complete(FreightFormContextDTO freightFormContext) {
        save(freightFormContext);
        PurchaseProcessDTO purchaseProcess = purchaseProcessRepository
            .findByProcessInstanceId(freightFormContext.getPurchaseProcess().getProcessInstance().getId())
            .map(purchaseProcessMapper::toDto)
            .orElseThrow();
        taskInstanceService.complete(freightFormContext.getTaskInstance(), purchaseProcess);
    }
}

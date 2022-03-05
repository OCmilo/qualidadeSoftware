package com.mycompany.myapp.process.purchaseProcess;

import com.mycompany.myapp.repository.PurchaseProcessRepository;
import com.mycompany.myapp.service.CoupomService;
import com.mycompany.myapp.service.dto.CoupomDTO;
import com.mycompany.myapp.service.dto.PurchaseProcessDTO;
import com.mycompany.myapp.service.mapper.PurchaseProcessMapper;
import org.akip.repository.TaskInstanceRepository;
import org.akip.service.TaskInstanceService;
import org.akip.service.dto.TaskInstanceDTO;
import org.akip.service.mapper.TaskInstanceMapper;
import org.springframework.stereotype.Component;

@Component
public class CoupomFormService {

    private final TaskInstanceService taskInstanceService;

    private final CoupomService coupomService;

    private final TaskInstanceRepository taskInstanceRepository;

    private final PurchaseProcessRepository purchaseProcessRepository;

    private final TaskInstanceMapper taskInstanceMapper;

    private final CoupomFormMapper coupomFormMapper;

    private final PurchaseProcessMapper purchaseProcessMapper;

    public CoupomFormService(
        TaskInstanceService taskInstanceService,
        CoupomService coupomService,
        TaskInstanceRepository taskInstanceRepository,
        PurchaseProcessRepository purchaseProcessRepository,
        TaskInstanceMapper taskInstanceMapper,
        CoupomFormMapper coupomFormMapper,
        PurchaseProcessMapper purchaseProcessMapper
    ) {
        this.taskInstanceService = taskInstanceService;
        this.coupomService = coupomService;
        this.taskInstanceRepository = taskInstanceRepository;
        this.purchaseProcessRepository = purchaseProcessRepository;
        this.taskInstanceMapper = taskInstanceMapper;
        this.coupomFormMapper = coupomFormMapper;
        this.purchaseProcessMapper = purchaseProcessMapper;
    }

    public CoupomFormContextDTO loadContext(Long taskInstanceId) {
        TaskInstanceDTO taskInstanceDTO = taskInstanceRepository
            .findById(taskInstanceId)
            .map(taskInstanceMapper::toDTOLoadTaskContext)
            .orElseThrow();

        PurchaseProcessDTO purchaseProcess = purchaseProcessRepository
            .findByProcessInstanceId(taskInstanceDTO.getProcessInstance().getId())
            .map(coupomFormMapper::toPurchaseProcessDTO)
            .orElseThrow();

        CoupomFormContextDTO coupomFormContext = new CoupomFormContextDTO();
        coupomFormContext.setTaskInstance(taskInstanceDTO);
        coupomFormContext.setPurchaseProcess(purchaseProcess);

        return coupomFormContext;
    }

    public CoupomFormContextDTO claim(Long taskInstanceId) {
        taskInstanceService.claim(taskInstanceId);
        return loadContext(taskInstanceId);
    }

    public void save(CoupomFormContextDTO coupomFormContext) {
        CoupomDTO coupomDTO = coupomService.findOne(coupomFormContext.getPurchaseProcess().getCoupom().getId()).orElseThrow();
        coupomDTO.setCupom(coupomFormContext.getPurchaseProcess().getCoupom().getCupom());
        coupomService.save(coupomDTO);
    }

    public void complete(CoupomFormContextDTO coupomFormContext) {
        save(coupomFormContext);
        PurchaseProcessDTO purchaseProcess = purchaseProcessRepository
            .findByProcessInstanceId(coupomFormContext.getPurchaseProcess().getProcessInstance().getId())
            .map(purchaseProcessMapper::toDto)
            .orElseThrow();
        taskInstanceService.complete(coupomFormContext.getTaskInstance(), purchaseProcess);
    }
}

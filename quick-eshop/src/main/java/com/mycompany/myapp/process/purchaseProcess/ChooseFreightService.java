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
public class ChooseFreightService {

    private final TaskInstanceService taskInstanceService;

    private final PurchaseService purchaseService;

    private final TaskInstanceRepository taskInstanceRepository;

    private final PurchaseProcessRepository purchaseProcessRepository;

    private final TaskInstanceMapper taskInstanceMapper;

    private final ChooseFreightMapper chooseFreightMapper;

    private final PurchaseProcessMapper purchaseProcessMapper;

    public ChooseFreightService(
        TaskInstanceService taskInstanceService,
        PurchaseService purchaseService,
        TaskInstanceRepository taskInstanceRepository,
        PurchaseProcessRepository purchaseProcessRepository,
        TaskInstanceMapper taskInstanceMapper,
        ChooseFreightMapper chooseFreightMapper,
        PurchaseProcessMapper purchaseProcessMapper
    ) {
        this.taskInstanceService = taskInstanceService;
        this.purchaseService = purchaseService;
        this.taskInstanceRepository = taskInstanceRepository;
        this.purchaseProcessRepository = purchaseProcessRepository;
        this.taskInstanceMapper = taskInstanceMapper;
        this.chooseFreightMapper = chooseFreightMapper;
        this.purchaseProcessMapper = purchaseProcessMapper;
    }

    public ChooseFreightContextDTO loadContext(Long taskInstanceId) {
        TaskInstanceDTO taskInstanceDTO = taskInstanceRepository
            .findById(taskInstanceId)
            .map(taskInstanceMapper::toDTOLoadTaskContext)
            .orElseThrow();

        PurchaseProcessDTO purchaseProcess = purchaseProcessRepository
            .findByProcessInstanceId(taskInstanceDTO.getProcessInstance().getId())
            .map(chooseFreightMapper::toPurchaseProcessDTO)
            .orElseThrow();

        ChooseFreightContextDTO chooseFreightContext = new ChooseFreightContextDTO();
        chooseFreightContext.setTaskInstance(taskInstanceDTO);
        chooseFreightContext.setPurchaseProcess(purchaseProcess);

        return chooseFreightContext;
    }

    public ChooseFreightContextDTO claim(Long taskInstanceId) {
        taskInstanceService.claim(taskInstanceId);
        return loadContext(taskInstanceId);
    }

    public void save(ChooseFreightContextDTO chooseFreightContext) {
        PurchaseDTO purchaseDTO = purchaseService.findOne(chooseFreightContext.getPurchaseProcess().getPurchase().getId()).orElseThrow();
        purchaseDTO.setAddress(chooseFreightContext.getPurchaseProcess().getPurchase().getAddress());
        purchaseDTO.setWithCoupon(chooseFreightContext.getPurchaseProcess().getPurchase().getWithCoupon());
        purchaseDTO.setFreight(chooseFreightContext.getPurchaseProcess().getPurchase().getFreight());
        purchaseService.save(purchaseDTO);
    }

    public void complete(ChooseFreightContextDTO chooseFreightContext) {
        save(chooseFreightContext);
        PurchaseProcessDTO purchaseProcess = purchaseProcessRepository
            .findByProcessInstanceId(chooseFreightContext.getPurchaseProcess().getProcessInstance().getId())
            .map(purchaseProcessMapper::toDto)
            .orElseThrow();
        taskInstanceService.complete(chooseFreightContext.getTaskInstance(), purchaseProcess);
    }
}

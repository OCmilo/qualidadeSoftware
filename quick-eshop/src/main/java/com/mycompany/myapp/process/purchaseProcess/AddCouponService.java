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
public class AddCouponService {

    private final TaskInstanceService taskInstanceService;

    private final PurchaseService purchaseService;

    private final TaskInstanceRepository taskInstanceRepository;

    private final PurchaseProcessRepository purchaseProcessRepository;

    private final TaskInstanceMapper taskInstanceMapper;

    private final AddCouponMapper addCouponMapper;

    private final PurchaseProcessMapper purchaseProcessMapper;

    public AddCouponService(
        TaskInstanceService taskInstanceService,
        PurchaseService purchaseService,
        TaskInstanceRepository taskInstanceRepository,
        PurchaseProcessRepository purchaseProcessRepository,
        TaskInstanceMapper taskInstanceMapper,
        AddCouponMapper addCouponMapper,
        PurchaseProcessMapper purchaseProcessMapper
    ) {
        this.taskInstanceService = taskInstanceService;
        this.purchaseService = purchaseService;
        this.taskInstanceRepository = taskInstanceRepository;
        this.purchaseProcessRepository = purchaseProcessRepository;
        this.taskInstanceMapper = taskInstanceMapper;
        this.addCouponMapper = addCouponMapper;
        this.purchaseProcessMapper = purchaseProcessMapper;
    }

    public AddCouponContextDTO loadContext(Long taskInstanceId) {
        TaskInstanceDTO taskInstanceDTO = taskInstanceRepository
            .findById(taskInstanceId)
            .map(taskInstanceMapper::toDTOLoadTaskContext)
            .orElseThrow();

        PurchaseProcessDTO purchaseProcess = purchaseProcessRepository
            .findByProcessInstanceId(taskInstanceDTO.getProcessInstance().getId())
            .map(addCouponMapper::toPurchaseProcessDTO)
            .orElseThrow();

        AddCouponContextDTO addCouponContext = new AddCouponContextDTO();
        addCouponContext.setTaskInstance(taskInstanceDTO);
        addCouponContext.setPurchaseProcess(purchaseProcess);

        return addCouponContext;
    }

    public AddCouponContextDTO claim(Long taskInstanceId) {
        taskInstanceService.claim(taskInstanceId);
        return loadContext(taskInstanceId);
    }

    public void save(AddCouponContextDTO addCouponContext) {
        PurchaseDTO purchaseDTO = purchaseService.findOne(addCouponContext.getPurchaseProcess().getPurchase().getId()).orElseThrow();
        purchaseDTO.setCoupon(addCouponContext.getPurchaseProcess().getPurchase().getCoupon());
        purchaseService.save(purchaseDTO);
    }

    public void complete(AddCouponContextDTO addCouponContext) {
        save(addCouponContext);
        PurchaseProcessDTO purchaseProcess = purchaseProcessRepository
            .findByProcessInstanceId(addCouponContext.getPurchaseProcess().getProcessInstance().getId())
            .map(purchaseProcessMapper::toDto)
            .orElseThrow();
        taskInstanceService.complete(addCouponContext.getTaskInstance(), purchaseProcess);
    }
}

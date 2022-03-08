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
public class AddProductsService {

    private final TaskInstanceService taskInstanceService;

    private final PurchaseService purchaseService;

    private final TaskInstanceRepository taskInstanceRepository;

    private final PurchaseProcessRepository purchaseProcessRepository;

    private final TaskInstanceMapper taskInstanceMapper;

    private final AddProductsMapper addProductsMapper;

    private final PurchaseProcessMapper purchaseProcessMapper;

    public AddProductsService(
        TaskInstanceService taskInstanceService,
        PurchaseService purchaseService,
        TaskInstanceRepository taskInstanceRepository,
        PurchaseProcessRepository purchaseProcessRepository,
        TaskInstanceMapper taskInstanceMapper,
        AddProductsMapper addProductsMapper,
        PurchaseProcessMapper purchaseProcessMapper
    ) {
        this.taskInstanceService = taskInstanceService;
        this.purchaseService = purchaseService;
        this.taskInstanceRepository = taskInstanceRepository;
        this.purchaseProcessRepository = purchaseProcessRepository;
        this.taskInstanceMapper = taskInstanceMapper;
        this.addProductsMapper = addProductsMapper;
        this.purchaseProcessMapper = purchaseProcessMapper;
    }

    public AddProductsContextDTO loadContext(Long taskInstanceId) {
        TaskInstanceDTO taskInstanceDTO = taskInstanceRepository
            .findById(taskInstanceId)
            .map(taskInstanceMapper::toDTOLoadTaskContext)
            .orElseThrow();

        PurchaseProcessDTO purchaseProcess = purchaseProcessRepository
            .findByProcessInstanceId(taskInstanceDTO.getProcessInstance().getId())
            .map(addProductsMapper::toPurchaseProcessDTO)
            .orElseThrow();

        AddProductsContextDTO addProductsContext = new AddProductsContextDTO();
        addProductsContext.setTaskInstance(taskInstanceDTO);
        addProductsContext.setPurchaseProcess(purchaseProcess);

        return addProductsContext;
    }

    public AddProductsContextDTO claim(Long taskInstanceId) {
        taskInstanceService.claim(taskInstanceId);
        return loadContext(taskInstanceId);
    }

    public void save(AddProductsContextDTO addProductsContext) {
        PurchaseDTO purchaseDTO = purchaseService.findOne(addProductsContext.getPurchaseProcess().getPurchase().getId()).orElseThrow();
        purchaseDTO.setAddProducts(addProductsContext.getPurchaseProcess().getPurchase().getAddProducts());
        purchaseService.save(purchaseDTO);
    }

    public void complete(AddProductsContextDTO addProductsContext) {
        save(addProductsContext);
        PurchaseProcessDTO purchaseProcess = purchaseProcessRepository
            .findByProcessInstanceId(addProductsContext.getPurchaseProcess().getProcessInstance().getId())
            .map(purchaseProcessMapper::toDto)
            .orElseThrow();
        taskInstanceService.complete(addProductsContext.getTaskInstance(), purchaseProcess);
    }
}

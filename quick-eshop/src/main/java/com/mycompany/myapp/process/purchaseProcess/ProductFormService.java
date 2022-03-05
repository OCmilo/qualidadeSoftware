package com.mycompany.myapp.process.purchaseProcess;

import com.mycompany.myapp.repository.PurchaseProcessRepository;
import com.mycompany.myapp.service.ProductService;
import com.mycompany.myapp.service.dto.ProductDTO;
import com.mycompany.myapp.service.dto.PurchaseProcessDTO;
import com.mycompany.myapp.service.mapper.PurchaseProcessMapper;
import org.akip.repository.TaskInstanceRepository;
import org.akip.service.TaskInstanceService;
import org.akip.service.dto.TaskInstanceDTO;
import org.akip.service.mapper.TaskInstanceMapper;
import org.springframework.stereotype.Component;

@Component
public class ProductFormService {

    private final TaskInstanceService taskInstanceService;

    private final ProductService productService;

    private final TaskInstanceRepository taskInstanceRepository;

    private final PurchaseProcessRepository purchaseProcessRepository;

    private final TaskInstanceMapper taskInstanceMapper;

    private final ProductFormMapper productFormMapper;

    private final PurchaseProcessMapper purchaseProcessMapper;

    public ProductFormService(
        TaskInstanceService taskInstanceService,
        ProductService productService,
        TaskInstanceRepository taskInstanceRepository,
        PurchaseProcessRepository purchaseProcessRepository,
        TaskInstanceMapper taskInstanceMapper,
        ProductFormMapper productFormMapper,
        PurchaseProcessMapper purchaseProcessMapper
    ) {
        this.taskInstanceService = taskInstanceService;
        this.productService = productService;
        this.taskInstanceRepository = taskInstanceRepository;
        this.purchaseProcessRepository = purchaseProcessRepository;
        this.taskInstanceMapper = taskInstanceMapper;
        this.productFormMapper = productFormMapper;
        this.purchaseProcessMapper = purchaseProcessMapper;
    }

    public ProductFormContextDTO loadContext(Long taskInstanceId) {
        TaskInstanceDTO taskInstanceDTO = taskInstanceRepository
            .findById(taskInstanceId)
            .map(taskInstanceMapper::toDTOLoadTaskContext)
            .orElseThrow();

        PurchaseProcessDTO purchaseProcess = purchaseProcessRepository
            .findByProcessInstanceId(taskInstanceDTO.getProcessInstance().getId())
            .map(productFormMapper::toPurchaseProcessDTO)
            .orElseThrow();

        ProductFormContextDTO productFormContext = new ProductFormContextDTO();
        productFormContext.setTaskInstance(taskInstanceDTO);
        productFormContext.setPurchaseProcess(purchaseProcess);

        return productFormContext;
    }

    public ProductFormContextDTO claim(Long taskInstanceId) {
        taskInstanceService.claim(taskInstanceId);
        return loadContext(taskInstanceId);
    }

    public void save(ProductFormContextDTO productFormContext) {
        ProductDTO productDTO = productService.findOne(productFormContext.getPurchaseProcess().getProduct().getId()).orElseThrow();
        productDTO.setQuantidade(productFormContext.getPurchaseProcess().getProduct().getQuantidade());
        productDTO.setProduto(productFormContext.getPurchaseProcess().getProduct().getProduto());
        productService.save(productDTO);
    }

    public void complete(ProductFormContextDTO productFormContext) {
        save(productFormContext);
        PurchaseProcessDTO purchaseProcess = purchaseProcessRepository
            .findByProcessInstanceId(productFormContext.getPurchaseProcess().getProcessInstance().getId())
            .map(purchaseProcessMapper::toDto)
            .orElseThrow();
        taskInstanceService.complete(productFormContext.getTaskInstance(), purchaseProcess);
    }
}

package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.PurchaseProcess;
import com.mycompany.myapp.repository.ProductRepository;
import com.mycompany.myapp.repository.PurchaseProcessRepository;
import com.mycompany.myapp.service.dto.PurchaseProcessDTO;
import com.mycompany.myapp.service.mapper.PurchaseProcessMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.akip.domain.ProcessInstance;
import org.akip.service.ProcessInstanceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link PurchaseProcess}.
 */
@Service
@Transactional
public class PurchaseProcessService {

    public static final String BPMN_PROCESS_DEFINITION_ID = "PurchaseProcess";

    private final Logger log = LoggerFactory.getLogger(PurchaseProcessService.class);

    private final ProcessInstanceService processInstanceService;

    private final ProductRepository productRepository;

    private final PurchaseProcessRepository purchaseProcessRepository;

    private final PurchaseProcessMapper purchaseProcessMapper;

    public PurchaseProcessService(
        ProcessInstanceService processInstanceService,
        ProductRepository productRepository,
        PurchaseProcessRepository purchaseProcessRepository,
        PurchaseProcessMapper purchaseProcessMapper
    ) {
        this.processInstanceService = processInstanceService;
        this.productRepository = productRepository;
        this.purchaseProcessRepository = purchaseProcessRepository;
        this.purchaseProcessMapper = purchaseProcessMapper;
    }

    /**
     * Save a purchaseProcess.
     *
     * @param purchaseProcessDTO the entity to save.
     * @return the persisted entity.
     */
    public PurchaseProcessDTO create(PurchaseProcessDTO purchaseProcessDTO) {
        log.debug("Request to save PurchaseProcess : {}", purchaseProcessDTO);

        PurchaseProcess purchaseProcess = purchaseProcessMapper.toEntity(purchaseProcessDTO);

        //Saving the domainEntity
        productRepository.save(purchaseProcess.getProduct());

        //Creating the process instance in the Camunda and setting it in the process entity
        ProcessInstance processInstance = processInstanceService.create(
            BPMN_PROCESS_DEFINITION_ID,
            "Product#" + purchaseProcess.getProduct().getId(),
            purchaseProcess
        );
        purchaseProcess.setProcessInstance(processInstance);

        //Saving the process entity
        purchaseProcess = purchaseProcessRepository.save(purchaseProcess);
        return purchaseProcessMapper.toDto(purchaseProcess);
    }

    /**
     * Get all the purchaseProcesss.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<PurchaseProcessDTO> findAll() {
        log.debug("Request to get all PurchaseProcesss");
        return purchaseProcessRepository
            .findAll()
            .stream()
            .map(purchaseProcessMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one purchaseProcess by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<PurchaseProcessDTO> findOne(Long id) {
        log.debug("Request to get PurchaseProcess : {}", id);
        return purchaseProcessRepository.findById(id).map(purchaseProcessMapper::toDto);
    }

    /**
     * Get one purchaseProcess by id.
     *
     * @param processInstanceId the id of the processInstance associated to the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<PurchaseProcessDTO> findByProcessInstanceId(Long processInstanceId) {
        log.debug("Request to get PurchaseProcess by  processInstanceId: {}", processInstanceId);
        return purchaseProcessRepository.findByProcessInstanceId(processInstanceId).map(purchaseProcessMapper::toDto);
    }
}

package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.TravelPlanProcess;
import com.mycompany.myapp.repository.TravelPlanProcessRepository;
import com.mycompany.myapp.repository.TravelPlanRepository;
import com.mycompany.myapp.service.dto.TravelPlanProcessDTO;
import com.mycompany.myapp.service.mapper.TravelPlanProcessMapper;
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
 * Service Implementation for managing {@link TravelPlanProcess}.
 */
@Service
@Transactional
public class TravelPlanProcessService {

    public static final String BPMN_PROCESS_DEFINITION_ID = "TravelPlanProcess";

    private final Logger log = LoggerFactory.getLogger(TravelPlanProcessService.class);

    private final ProcessInstanceService processInstanceService;

    private final TravelPlanRepository travelPlanRepository;

    private final TravelPlanProcessRepository travelPlanProcessRepository;

    private final TravelPlanProcessMapper travelPlanProcessMapper;

    public TravelPlanProcessService(
        ProcessInstanceService processInstanceService,
        TravelPlanRepository travelPlanRepository,
        TravelPlanProcessRepository travelPlanProcessRepository,
        TravelPlanProcessMapper travelPlanProcessMapper
    ) {
        this.processInstanceService = processInstanceService;
        this.travelPlanRepository = travelPlanRepository;
        this.travelPlanProcessRepository = travelPlanProcessRepository;
        this.travelPlanProcessMapper = travelPlanProcessMapper;
    }

    /**
     * Save a travelPlanProcess.
     *
     * @param travelPlanProcessDTO the entity to save.
     * @return the persisted entity.
     */
    public TravelPlanProcessDTO create(TravelPlanProcessDTO travelPlanProcessDTO) {
        log.debug("Request to save TravelPlanProcess : {}", travelPlanProcessDTO);

        TravelPlanProcess travelPlanProcess = travelPlanProcessMapper.toEntity(travelPlanProcessDTO);

        //Saving the domainEntity
        travelPlanRepository.save(travelPlanProcess.getTravelPlan());

        //Creating the process instance in the Camunda and setting it in the process entity
        ProcessInstance processInstance = processInstanceService.create(
            BPMN_PROCESS_DEFINITION_ID,
            "TravelPlan#" + travelPlanProcess.getTravelPlan().getId(),
            travelPlanProcess
        );
        travelPlanProcess.setProcessInstance(processInstance);

        //Saving the process entity
        travelPlanProcess = travelPlanProcessRepository.save(travelPlanProcess);
        return travelPlanProcessMapper.toDto(travelPlanProcess);
    }

    /**
     * Get all the travelPlanProcesss.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<TravelPlanProcessDTO> findAll() {
        log.debug("Request to get all TravelPlanProcesss");
        return travelPlanProcessRepository
            .findAll()
            .stream()
            .map(travelPlanProcessMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one travelPlanProcess by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<TravelPlanProcessDTO> findOne(Long id) {
        log.debug("Request to get TravelPlanProcess : {}", id);
        return travelPlanProcessRepository.findById(id).map(travelPlanProcessMapper::toDto);
    }

    /**
     * Get one travelPlanProcess by id.
     *
     * @param processInstanceId the id of the processInstance associated to the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<TravelPlanProcessDTO> findByProcessInstanceId(Long processInstanceId) {
        log.debug("Request to get TravelPlanProcess by  processInstanceId: {}", processInstanceId);
        return travelPlanProcessRepository.findByProcessInstanceId(processInstanceId).map(travelPlanProcessMapper::toDto);
    }
}

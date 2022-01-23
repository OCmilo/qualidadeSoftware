package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.TravelPlan;
import com.mycompany.myapp.repository.TravelPlanRepository;
import com.mycompany.myapp.service.dto.TravelPlanDTO;
import com.mycompany.myapp.service.mapper.TravelPlanMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link TravelPlan}.
 */
@Service
@Transactional
public class TravelPlanService {

    private final Logger log = LoggerFactory.getLogger(TravelPlanService.class);

    private final TravelPlanRepository travelPlanRepository;

    private final TravelPlanMapper travelPlanMapper;

    public TravelPlanService(TravelPlanRepository travelPlanRepository, TravelPlanMapper travelPlanMapper) {
        this.travelPlanRepository = travelPlanRepository;
        this.travelPlanMapper = travelPlanMapper;
    }

    /**
     * Save a travelPlan.
     *
     * @param travelPlanDTO the entity to save.
     * @return the persisted entity.
     */
    public TravelPlanDTO save(TravelPlanDTO travelPlanDTO) {
        log.debug("Request to save TravelPlan : {}", travelPlanDTO);
        TravelPlan travelPlan = travelPlanMapper.toEntity(travelPlanDTO);
        travelPlan = travelPlanRepository.save(travelPlan);
        return travelPlanMapper.toDto(travelPlan);
    }

    /**
     * Partially update a travelPlan.
     *
     * @param travelPlanDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<TravelPlanDTO> partialUpdate(TravelPlanDTO travelPlanDTO) {
        log.debug("Request to partially update TravelPlan : {}", travelPlanDTO);

        return travelPlanRepository
            .findById(travelPlanDTO.getId())
            .map(
                existingTravelPlan -> {
                    travelPlanMapper.partialUpdate(existingTravelPlan, travelPlanDTO);
                    return existingTravelPlan;
                }
            )
            .map(travelPlanRepository::save)
            .map(travelPlanMapper::toDto);
    }

    /**
     * Get all the travelPlans.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<TravelPlanDTO> findAll() {
        log.debug("Request to get all TravelPlans");
        return travelPlanRepository.findAll().stream().map(travelPlanMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one travelPlan by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<TravelPlanDTO> findOne(Long id) {
        log.debug("Request to get TravelPlan : {}", id);
        return travelPlanRepository.findById(id).map(travelPlanMapper::toDto);
    }

    /**
     * Delete the travelPlan by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete TravelPlan : {}", id);
        travelPlanRepository.deleteById(id);
    }
}

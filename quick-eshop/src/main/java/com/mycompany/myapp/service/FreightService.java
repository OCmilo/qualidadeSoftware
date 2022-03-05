package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.Freight;
import com.mycompany.myapp.repository.FreightRepository;
import com.mycompany.myapp.service.dto.FreightDTO;
import com.mycompany.myapp.service.mapper.FreightMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Freight}.
 */
@Service
@Transactional
public class FreightService {

    private final Logger log = LoggerFactory.getLogger(FreightService.class);

    private final FreightRepository freightRepository;

    private final FreightMapper freightMapper;

    public FreightService(FreightRepository freightRepository, FreightMapper freightMapper) {
        this.freightRepository = freightRepository;
        this.freightMapper = freightMapper;
    }

    /**
     * Save a freight.
     *
     * @param freightDTO the entity to save.
     * @return the persisted entity.
     */
    public FreightDTO save(FreightDTO freightDTO) {
        log.debug("Request to save Freight : {}", freightDTO);
        Freight freight = freightMapper.toEntity(freightDTO);
        freight = freightRepository.save(freight);
        return freightMapper.toDto(freight);
    }

    /**
     * Partially update a freight.
     *
     * @param freightDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<FreightDTO> partialUpdate(FreightDTO freightDTO) {
        log.debug("Request to partially update Freight : {}", freightDTO);

        return freightRepository
            .findById(freightDTO.getId())
            .map(
                existingFreight -> {
                    freightMapper.partialUpdate(existingFreight, freightDTO);
                    return existingFreight;
                }
            )
            .map(freightRepository::save)
            .map(freightMapper::toDto);
    }

    /**
     * Get all the freights.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<FreightDTO> findAll() {
        log.debug("Request to get all Freights");
        return freightRepository.findAll().stream().map(freightMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one freight by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<FreightDTO> findOne(Long id) {
        log.debug("Request to get Freight : {}", id);
        return freightRepository.findById(id).map(freightMapper::toDto);
    }

    /**
     * Delete the freight by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Freight : {}", id);
        freightRepository.deleteById(id);
    }
}

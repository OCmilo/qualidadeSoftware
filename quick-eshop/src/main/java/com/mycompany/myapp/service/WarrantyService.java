package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.Warranty;
import com.mycompany.myapp.repository.WarrantyRepository;
import com.mycompany.myapp.service.dto.WarrantyDTO;
import com.mycompany.myapp.service.mapper.WarrantyMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Warranty}.
 */
@Service
@Transactional
public class WarrantyService {

    private final Logger log = LoggerFactory.getLogger(WarrantyService.class);

    private final WarrantyRepository warrantyRepository;

    private final WarrantyMapper warrantyMapper;

    public WarrantyService(WarrantyRepository warrantyRepository, WarrantyMapper warrantyMapper) {
        this.warrantyRepository = warrantyRepository;
        this.warrantyMapper = warrantyMapper;
    }

    /**
     * Save a warranty.
     *
     * @param warrantyDTO the entity to save.
     * @return the persisted entity.
     */
    public WarrantyDTO save(WarrantyDTO warrantyDTO) {
        log.debug("Request to save Warranty : {}", warrantyDTO);
        Warranty warranty = warrantyMapper.toEntity(warrantyDTO);
        warranty = warrantyRepository.save(warranty);
        return warrantyMapper.toDto(warranty);
    }

    /**
     * Partially update a warranty.
     *
     * @param warrantyDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<WarrantyDTO> partialUpdate(WarrantyDTO warrantyDTO) {
        log.debug("Request to partially update Warranty : {}", warrantyDTO);

        return warrantyRepository
            .findById(warrantyDTO.getId())
            .map(
                existingWarranty -> {
                    warrantyMapper.partialUpdate(existingWarranty, warrantyDTO);
                    return existingWarranty;
                }
            )
            .map(warrantyRepository::save)
            .map(warrantyMapper::toDto);
    }

    /**
     * Get all the warranties.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<WarrantyDTO> findAll() {
        log.debug("Request to get all Warranties");
        return warrantyRepository.findAll().stream().map(warrantyMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one warranty by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<WarrantyDTO> findOne(Long id) {
        log.debug("Request to get Warranty : {}", id);
        return warrantyRepository.findById(id).map(warrantyMapper::toDto);
    }

    /**
     * Delete the warranty by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Warranty : {}", id);
        warrantyRepository.deleteById(id);
    }
}

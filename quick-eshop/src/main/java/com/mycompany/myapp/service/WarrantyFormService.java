package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.WarrantyForm;
import com.mycompany.myapp.repository.WarrantyFormRepository;
import com.mycompany.myapp.service.dto.WarrantyFormDTO;
import com.mycompany.myapp.service.mapper.WarrantyFormMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link WarrantyForm}.
 */
@Service
@Transactional
public class WarrantyFormService {

    private final Logger log = LoggerFactory.getLogger(WarrantyFormService.class);

    private final WarrantyFormRepository warrantyFormRepository;

    private final WarrantyFormMapper warrantyFormMapper;

    public WarrantyFormService(WarrantyFormRepository warrantyFormRepository, WarrantyFormMapper warrantyFormMapper) {
        this.warrantyFormRepository = warrantyFormRepository;
        this.warrantyFormMapper = warrantyFormMapper;
    }

    /**
     * Save a warrantyForm.
     *
     * @param warrantyFormDTO the entity to save.
     * @return the persisted entity.
     */
    public WarrantyFormDTO save(WarrantyFormDTO warrantyFormDTO) {
        log.debug("Request to save WarrantyForm : {}", warrantyFormDTO);
        WarrantyForm warrantyForm = warrantyFormMapper.toEntity(warrantyFormDTO);
        warrantyForm = warrantyFormRepository.save(warrantyForm);
        return warrantyFormMapper.toDto(warrantyForm);
    }

    /**
     * Partially update a warrantyForm.
     *
     * @param warrantyFormDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<WarrantyFormDTO> partialUpdate(WarrantyFormDTO warrantyFormDTO) {
        log.debug("Request to partially update WarrantyForm : {}", warrantyFormDTO);

        return warrantyFormRepository
            .findById(warrantyFormDTO.getId())
            .map(
                existingWarrantyForm -> {
                    warrantyFormMapper.partialUpdate(existingWarrantyForm, warrantyFormDTO);
                    return existingWarrantyForm;
                }
            )
            .map(warrantyFormRepository::save)
            .map(warrantyFormMapper::toDto);
    }

    /**
     * Get all the warrantyForms.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<WarrantyFormDTO> findAll() {
        log.debug("Request to get all WarrantyForms");
        return warrantyFormRepository.findAll().stream().map(warrantyFormMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one warrantyForm by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<WarrantyFormDTO> findOne(Long id) {
        log.debug("Request to get WarrantyForm : {}", id);
        return warrantyFormRepository.findById(id).map(warrantyFormMapper::toDto);
    }

    /**
     * Delete the warrantyForm by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete WarrantyForm : {}", id);
        warrantyFormRepository.deleteById(id);
    }
}

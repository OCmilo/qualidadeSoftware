package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.FreightForm;
import com.mycompany.myapp.repository.FreightFormRepository;
import com.mycompany.myapp.service.dto.FreightFormDTO;
import com.mycompany.myapp.service.mapper.FreightFormMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link FreightForm}.
 */
@Service
@Transactional
public class FreightFormService {

    private final Logger log = LoggerFactory.getLogger(FreightFormService.class);

    private final FreightFormRepository freightFormRepository;

    private final FreightFormMapper freightFormMapper;

    public FreightFormService(FreightFormRepository freightFormRepository, FreightFormMapper freightFormMapper) {
        this.freightFormRepository = freightFormRepository;
        this.freightFormMapper = freightFormMapper;
    }

    /**
     * Save a freightForm.
     *
     * @param freightFormDTO the entity to save.
     * @return the persisted entity.
     */
    public FreightFormDTO save(FreightFormDTO freightFormDTO) {
        log.debug("Request to save FreightForm : {}", freightFormDTO);
        FreightForm freightForm = freightFormMapper.toEntity(freightFormDTO);
        freightForm = freightFormRepository.save(freightForm);
        return freightFormMapper.toDto(freightForm);
    }

    /**
     * Partially update a freightForm.
     *
     * @param freightFormDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<FreightFormDTO> partialUpdate(FreightFormDTO freightFormDTO) {
        log.debug("Request to partially update FreightForm : {}", freightFormDTO);

        return freightFormRepository
            .findById(freightFormDTO.getId())
            .map(
                existingFreightForm -> {
                    freightFormMapper.partialUpdate(existingFreightForm, freightFormDTO);
                    return existingFreightForm;
                }
            )
            .map(freightFormRepository::save)
            .map(freightFormMapper::toDto);
    }

    /**
     * Get all the freightForms.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<FreightFormDTO> findAll() {
        log.debug("Request to get all FreightForms");
        return freightFormRepository.findAll().stream().map(freightFormMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one freightForm by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<FreightFormDTO> findOne(Long id) {
        log.debug("Request to get FreightForm : {}", id);
        return freightFormRepository.findById(id).map(freightFormMapper::toDto);
    }

    /**
     * Delete the freightForm by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete FreightForm : {}", id);
        freightFormRepository.deleteById(id);
    }
}

package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.CoupomForm;
import com.mycompany.myapp.repository.CoupomFormRepository;
import com.mycompany.myapp.service.dto.CoupomFormDTO;
import com.mycompany.myapp.service.mapper.CoupomFormMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link CoupomForm}.
 */
@Service
@Transactional
public class CoupomFormService {

    private final Logger log = LoggerFactory.getLogger(CoupomFormService.class);

    private final CoupomFormRepository coupomFormRepository;

    private final CoupomFormMapper coupomFormMapper;

    public CoupomFormService(CoupomFormRepository coupomFormRepository, CoupomFormMapper coupomFormMapper) {
        this.coupomFormRepository = coupomFormRepository;
        this.coupomFormMapper = coupomFormMapper;
    }

    /**
     * Save a coupomForm.
     *
     * @param coupomFormDTO the entity to save.
     * @return the persisted entity.
     */
    public CoupomFormDTO save(CoupomFormDTO coupomFormDTO) {
        log.debug("Request to save CoupomForm : {}", coupomFormDTO);
        CoupomForm coupomForm = coupomFormMapper.toEntity(coupomFormDTO);
        coupomForm = coupomFormRepository.save(coupomForm);
        return coupomFormMapper.toDto(coupomForm);
    }

    /**
     * Partially update a coupomForm.
     *
     * @param coupomFormDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<CoupomFormDTO> partialUpdate(CoupomFormDTO coupomFormDTO) {
        log.debug("Request to partially update CoupomForm : {}", coupomFormDTO);

        return coupomFormRepository
            .findById(coupomFormDTO.getId())
            .map(
                existingCoupomForm -> {
                    coupomFormMapper.partialUpdate(existingCoupomForm, coupomFormDTO);
                    return existingCoupomForm;
                }
            )
            .map(coupomFormRepository::save)
            .map(coupomFormMapper::toDto);
    }

    /**
     * Get all the coupomForms.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<CoupomFormDTO> findAll() {
        log.debug("Request to get all CoupomForms");
        return coupomFormRepository.findAll().stream().map(coupomFormMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one coupomForm by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CoupomFormDTO> findOne(Long id) {
        log.debug("Request to get CoupomForm : {}", id);
        return coupomFormRepository.findById(id).map(coupomFormMapper::toDto);
    }

    /**
     * Delete the coupomForm by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete CoupomForm : {}", id);
        coupomFormRepository.deleteById(id);
    }
}

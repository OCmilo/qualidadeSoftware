package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.Coupom;
import com.mycompany.myapp.repository.CoupomRepository;
import com.mycompany.myapp.service.dto.CoupomDTO;
import com.mycompany.myapp.service.mapper.CoupomMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Coupom}.
 */
@Service
@Transactional
public class CoupomService {

    private final Logger log = LoggerFactory.getLogger(CoupomService.class);

    private final CoupomRepository coupomRepository;

    private final CoupomMapper coupomMapper;

    public CoupomService(CoupomRepository coupomRepository, CoupomMapper coupomMapper) {
        this.coupomRepository = coupomRepository;
        this.coupomMapper = coupomMapper;
    }

    /**
     * Save a coupom.
     *
     * @param coupomDTO the entity to save.
     * @return the persisted entity.
     */
    public CoupomDTO save(CoupomDTO coupomDTO) {
        log.debug("Request to save Coupom : {}", coupomDTO);
        Coupom coupom = coupomMapper.toEntity(coupomDTO);
        coupom = coupomRepository.save(coupom);
        return coupomMapper.toDto(coupom);
    }

    /**
     * Partially update a coupom.
     *
     * @param coupomDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<CoupomDTO> partialUpdate(CoupomDTO coupomDTO) {
        log.debug("Request to partially update Coupom : {}", coupomDTO);

        return coupomRepository
            .findById(coupomDTO.getId())
            .map(
                existingCoupom -> {
                    coupomMapper.partialUpdate(existingCoupom, coupomDTO);
                    return existingCoupom;
                }
            )
            .map(coupomRepository::save)
            .map(coupomMapper::toDto);
    }

    /**
     * Get all the coupoms.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<CoupomDTO> findAll() {
        log.debug("Request to get all Coupoms");
        return coupomRepository.findAll().stream().map(coupomMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one coupom by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CoupomDTO> findOne(Long id) {
        log.debug("Request to get Coupom : {}", id);
        return coupomRepository.findById(id).map(coupomMapper::toDto);
    }

    /**
     * Delete the coupom by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Coupom : {}", id);
        coupomRepository.deleteById(id);
    }
}

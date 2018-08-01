package io.github.jhipster.application.service.impl;

import io.github.jhipster.application.service.ResturantService;
import io.github.jhipster.application.domain.Resturant;
import io.github.jhipster.application.repository.ResturantRepository;
import io.github.jhipster.application.service.dto.ResturantDTO;
import io.github.jhipster.application.service.mapper.ResturantMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
/**
 * Service Implementation for managing Resturant.
 */
@Service
@Transactional
public class ResturantServiceImpl implements ResturantService {

    private final Logger log = LoggerFactory.getLogger(ResturantServiceImpl.class);

    private final ResturantRepository resturantRepository;

    private final ResturantMapper resturantMapper;

    public ResturantServiceImpl(ResturantRepository resturantRepository, ResturantMapper resturantMapper) {
        this.resturantRepository = resturantRepository;
        this.resturantMapper = resturantMapper;
    }

    /**
     * Save a resturant.
     *
     * @param resturantDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ResturantDTO save(ResturantDTO resturantDTO) {
        log.debug("Request to save Resturant : {}", resturantDTO);
        Resturant resturant = resturantMapper.toEntity(resturantDTO);
        resturant = resturantRepository.save(resturant);
        return resturantMapper.toDto(resturant);
    }

    /**
     * Get all the resturants.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ResturantDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Resturants");
        return resturantRepository.findAll(pageable)
            .map(resturantMapper::toDto);
    }

    /**
     * Get all the Resturant with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    public Page<ResturantDTO> findAllWithEagerRelationships(Pageable pageable) {
        return resturantRepository.findAllWithEagerRelationships(pageable).map(resturantMapper::toDto);
    }
    

    /**
     * Get one resturant by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ResturantDTO> findOne(Long id) {
        log.debug("Request to get Resturant : {}", id);
        return resturantRepository.findOneWithEagerRelationships(id)
            .map(resturantMapper::toDto);
    }

    /**
     * Delete the resturant by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Resturant : {}", id);
        resturantRepository.deleteById(id);
    }
}

package io.github.jhipster.application.service;

import io.github.jhipster.application.service.dto.ResturantDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Resturant.
 */
public interface ResturantService {

    /**
     * Save a resturant.
     *
     * @param resturantDTO the entity to save
     * @return the persisted entity
     */
    ResturantDTO save(ResturantDTO resturantDTO);

    /**
     * Get all the resturants.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<ResturantDTO> findAll(Pageable pageable);

    /**
     * Get all the Resturant with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    Page<ResturantDTO> findAllWithEagerRelationships(Pageable pageable);
    
    /**
     * Get the "id" resturant.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<ResturantDTO> findOne(Long id);

    /**
     * Delete the "id" resturant.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}

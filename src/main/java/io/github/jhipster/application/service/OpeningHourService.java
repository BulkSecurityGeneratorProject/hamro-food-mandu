package io.github.jhipster.application.service;

import io.github.jhipster.application.service.dto.OpeningHourDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing OpeningHour.
 */
public interface OpeningHourService {

    /**
     * Save a openingHour.
     *
     * @param openingHourDTO the entity to save
     * @return the persisted entity
     */
    OpeningHourDTO save(OpeningHourDTO openingHourDTO);

    /**
     * Get all the openingHours.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<OpeningHourDTO> findAll(Pageable pageable);


    /**
     * Get the "id" openingHour.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<OpeningHourDTO> findOne(Long id);

    /**
     * Delete the "id" openingHour.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}

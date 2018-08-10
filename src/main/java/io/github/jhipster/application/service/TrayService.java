package io.github.jhipster.application.service;

import io.github.jhipster.application.service.dto.TrayDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Tray.
 */
public interface TrayService {

    /**
     * Save a tray.
     *
     * @param trayDTO the entity to save
     * @return the persisted entity
     */
    TrayDTO save(TrayDTO trayDTO);

    /**
     * Get all the trays.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<TrayDTO> findAll(Pageable pageable);


    /**
     * Get the "id" tray.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<TrayDTO> findOne(Long id);

    /**
     * Delete the "id" tray.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    Optional<TrayDTO> findOneByUserId(Long id);
}

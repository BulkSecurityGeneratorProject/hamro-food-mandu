package io.github.jhipster.application.service;

import io.github.jhipster.application.service.dto.HFMOrderDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing HFMOrder.
 */
public interface HFMOrderService {

    /**
     * Save a hFMOrder.
     *
     * @param hFMOrderDTO the entity to save
     * @return the persisted entity
     */
    HFMOrderDTO save(HFMOrderDTO hFMOrderDTO);

    /**
     * Get all the hFMOrders.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<HFMOrderDTO> findAll(Pageable pageable);


    /**
     * Get the "id" hFMOrder.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<HFMOrderDTO> findOne(Long id);

    /**
     * Delete the "id" hFMOrder.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    Optional<List<HFMOrderDTO>> findAllByUserId(Long userid);
}

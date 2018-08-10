package io.github.jhipster.application.service.impl;

import io.github.jhipster.application.service.HFMOrderService;
import io.github.jhipster.application.domain.HFMOrder;
import io.github.jhipster.application.repository.HFMOrderRepository;
import io.github.jhipster.application.service.dto.HFMOrderDTO;
import io.github.jhipster.application.service.mapper.HFMOrderMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;
/**
 * Service Implementation for managing HFMOrder.
 */
@Service
@Transactional
public class HFMOrderServiceImpl implements HFMOrderService {

    private final Logger log = LoggerFactory.getLogger(HFMOrderServiceImpl.class);

    private final HFMOrderRepository hFMOrderRepository;

    private final HFMOrderMapper hFMOrderMapper;

    public HFMOrderServiceImpl(HFMOrderRepository hFMOrderRepository, HFMOrderMapper hFMOrderMapper) {
        this.hFMOrderRepository = hFMOrderRepository;
        this.hFMOrderMapper = hFMOrderMapper;
    }

    /**
     * Save a hFMOrder.
     *
     * @param hFMOrderDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public HFMOrderDTO save(HFMOrderDTO hFMOrderDTO) {
        log.debug("Request to save HFMOrder : {}", hFMOrderDTO);
        HFMOrder hFMOrder = hFMOrderMapper.toEntity(hFMOrderDTO);
        hFMOrder = hFMOrderRepository.save(hFMOrder);
        return hFMOrderMapper.toDto(hFMOrder);
    }

    /**
     * Get all the hFMOrders.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<HFMOrderDTO> findAll(Pageable pageable) {
        log.debug("Request to get all HFMOrders");
        return hFMOrderRepository.findAll(pageable)
            .map(hFMOrderMapper::toDto);
    }


    /**
     * Get one hFMOrder by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<HFMOrderDTO> findOne(Long id) {
        log.debug("Request to get HFMOrder : {}", id);
        return hFMOrderRepository.findById(id)
            .map(hFMOrderMapper::toDto);
    }

    /**
     * Delete the hFMOrder by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete HFMOrder : {}", id);
        hFMOrderRepository.deleteById(id);
    }

    @Override
    public Optional<List<HFMOrderDTO>> findAllByUserId(Long userid) {
        log.debug("Request to get all HFMOrder");
        return hFMOrderRepository.findByUserId(userid).map(hFMOrderMapper::toDto);
    }
}

package io.github.jhipster.application.service.impl;

import io.github.jhipster.application.service.TrayService;
import io.github.jhipster.application.domain.Tray;
import io.github.jhipster.application.repository.TrayRepository;
import io.github.jhipster.application.service.dto.TrayDTO;
import io.github.jhipster.application.service.mapper.TrayMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
/**
 * Service Implementation for managing Tray.
 */
@Service
@Transactional
public class TrayServiceImpl implements TrayService {

    private final Logger log = LoggerFactory.getLogger(TrayServiceImpl.class);

    private final TrayRepository trayRepository;

    private final TrayMapper trayMapper;

    public TrayServiceImpl(TrayRepository trayRepository, TrayMapper trayMapper) {
        this.trayRepository = trayRepository;
        this.trayMapper = trayMapper;
    }

    /**
     * Save a tray.
     *
     * @param trayDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public TrayDTO save(TrayDTO trayDTO) {
        log.debug("Request to save Tray : {}", trayDTO);
        Tray tray = trayMapper.toEntity(trayDTO);
        tray = trayRepository.save(tray);
        return trayMapper.toDto(tray);
    }

    /**
     * Get all the trays.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<TrayDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Trays");
        return trayRepository.findAll(pageable)
            .map(trayMapper::toDto);
    }


    /**
     * Get one tray by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<TrayDTO> findOne(Long id) {
        log.debug("Request to get Tray : {}", id);
        return trayRepository.findById(id)
            .map(trayMapper::toDto);
    }

    /**
     * Delete the tray by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Tray : {}", id);
        trayRepository.deleteById(id);
    }

    @Override
    public Optional<TrayDTO> findOneByUserId(Long userId) {
        log.debug("Request to get Tray : {}", userId);
        return trayRepository.findByUserId(userId)
            .map(trayMapper::toDto);
    }
}

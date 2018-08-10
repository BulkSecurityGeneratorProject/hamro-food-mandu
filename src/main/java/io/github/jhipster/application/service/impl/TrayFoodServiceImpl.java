package io.github.jhipster.application.service.impl;

import io.github.jhipster.application.domain.TrayFood;
import io.github.jhipster.application.repository.TrayFoodRepository;
import io.github.jhipster.application.service.TrayFoodService;
import io.github.jhipster.application.service.dto.TrayFoodDTO;
import io.github.jhipster.application.service.mapper.TrayFoodMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing TrayFood.
 */
@Service
@Transactional
public class TrayFoodServiceImpl implements TrayFoodService {

    private final Logger log = LoggerFactory.getLogger(TrayFoodServiceImpl.class);

    private final TrayFoodRepository trayFoodRepository;

    private final TrayFoodMapper trayFoodMapper;

    public TrayFoodServiceImpl(TrayFoodRepository trayFoodRepository, TrayFoodMapper trayFoodMapper) {
        this.trayFoodRepository = trayFoodRepository;
        this.trayFoodMapper = trayFoodMapper;
    }

    /**
     * Save a trayFood.
     *
     * @param trayFoodDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public TrayFoodDTO save(TrayFoodDTO trayFoodDTO) {
        log.debug("Request to save TrayFood : {}", trayFoodDTO);
        TrayFood trayFood = trayFoodMapper.toEntity(trayFoodDTO);
        trayFood = trayFoodRepository.save(trayFood);
        return trayFoodMapper.toDto(trayFood);
    }

    /**
     * Get all the trayFoods.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<TrayFoodDTO> findAll(Pageable pageable) {
        log.debug("Request to get all TrayFoods");
        return trayFoodRepository.findAll(pageable)
            .map(trayFoodMapper::toDto);
    }


    /**
     * Get one trayFood by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<TrayFoodDTO> findOne(Long id) {
        log.debug("Request to get TrayFood : {}", id);
        return trayFoodRepository.findById(id)
            .map(trayFoodMapper::toDto);
    }

    /**
     * Delete the trayFood by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete TrayFood : {}", id);
        trayFoodRepository.deleteById(id);
    }

    @Override
    public Optional<List<TrayFoodDTO>> findAllByTrayId(Long trayId) {
        log.debug("Request to get all TrayFoods");
        return trayFoodRepository.findByTrayId(trayId).map(trayFoodMapper::toDto);
    }
}

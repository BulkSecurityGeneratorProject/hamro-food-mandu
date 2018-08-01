package io.github.jhipster.application.service.impl;

import io.github.jhipster.application.service.OpeningHourService;
import io.github.jhipster.application.domain.OpeningHour;
import io.github.jhipster.application.repository.OpeningHourRepository;
import io.github.jhipster.application.service.dto.OpeningHourDTO;
import io.github.jhipster.application.service.mapper.OpeningHourMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
/**
 * Service Implementation for managing OpeningHour.
 */
@Service
@Transactional
public class OpeningHourServiceImpl implements OpeningHourService {

    private final Logger log = LoggerFactory.getLogger(OpeningHourServiceImpl.class);

    private final OpeningHourRepository openingHourRepository;

    private final OpeningHourMapper openingHourMapper;

    public OpeningHourServiceImpl(OpeningHourRepository openingHourRepository, OpeningHourMapper openingHourMapper) {
        this.openingHourRepository = openingHourRepository;
        this.openingHourMapper = openingHourMapper;
    }

    /**
     * Save a openingHour.
     *
     * @param openingHourDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public OpeningHourDTO save(OpeningHourDTO openingHourDTO) {
        log.debug("Request to save OpeningHour : {}", openingHourDTO);
        OpeningHour openingHour = openingHourMapper.toEntity(openingHourDTO);
        openingHour = openingHourRepository.save(openingHour);
        return openingHourMapper.toDto(openingHour);
    }

    /**
     * Get all the openingHours.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<OpeningHourDTO> findAll(Pageable pageable) {
        log.debug("Request to get all OpeningHours");
        return openingHourRepository.findAll(pageable)
            .map(openingHourMapper::toDto);
    }


    /**
     * Get one openingHour by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<OpeningHourDTO> findOne(Long id) {
        log.debug("Request to get OpeningHour : {}", id);
        return openingHourRepository.findById(id)
            .map(openingHourMapper::toDto);
    }

    /**
     * Delete the openingHour by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete OpeningHour : {}", id);
        openingHourRepository.deleteById(id);
    }
}

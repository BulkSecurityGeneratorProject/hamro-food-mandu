package io.github.jhipster.application.service.impl;

import io.github.jhipster.application.service.DeliveryInfoService;
import io.github.jhipster.application.domain.DeliveryInfo;
import io.github.jhipster.application.repository.DeliveryInfoRepository;
import io.github.jhipster.application.service.dto.DeliveryInfoDTO;
import io.github.jhipster.application.service.mapper.DeliveryInfoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
/**
 * Service Implementation for managing DeliveryInfo.
 */
@Service
@Transactional
public class DeliveryInfoServiceImpl implements DeliveryInfoService {

    private final Logger log = LoggerFactory.getLogger(DeliveryInfoServiceImpl.class);

    private final DeliveryInfoRepository deliveryInfoRepository;

    private final DeliveryInfoMapper deliveryInfoMapper;

    public DeliveryInfoServiceImpl(DeliveryInfoRepository deliveryInfoRepository, DeliveryInfoMapper deliveryInfoMapper) {
        this.deliveryInfoRepository = deliveryInfoRepository;
        this.deliveryInfoMapper = deliveryInfoMapper;
    }

    /**
     * Save a deliveryInfo.
     *
     * @param deliveryInfoDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public DeliveryInfoDTO save(DeliveryInfoDTO deliveryInfoDTO) {
        log.debug("Request to save DeliveryInfo : {}", deliveryInfoDTO);
        DeliveryInfo deliveryInfo = deliveryInfoMapper.toEntity(deliveryInfoDTO);
        deliveryInfo = deliveryInfoRepository.save(deliveryInfo);
        return deliveryInfoMapper.toDto(deliveryInfo);
    }

    /**
     * Get all the deliveryInfos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<DeliveryInfoDTO> findAll(Pageable pageable) {
        log.debug("Request to get all DeliveryInfos");
        return deliveryInfoRepository.findAll(pageable)
            .map(deliveryInfoMapper::toDto);
    }


    /**
     * Get one deliveryInfo by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<DeliveryInfoDTO> findOne(Long id) {
        log.debug("Request to get DeliveryInfo : {}", id);
        return deliveryInfoRepository.findById(id)
            .map(deliveryInfoMapper::toDto);
    }

    /**
     * Delete the deliveryInfo by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete DeliveryInfo : {}", id);
        deliveryInfoRepository.deleteById(id);
    }
}

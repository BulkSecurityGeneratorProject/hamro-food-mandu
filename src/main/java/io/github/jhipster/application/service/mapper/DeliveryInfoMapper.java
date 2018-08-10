package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.DeliveryInfoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity DeliveryInfo and its DTO DeliveryInfoDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface DeliveryInfoMapper extends EntityMapper<DeliveryInfoDTO, DeliveryInfo> {



    default DeliveryInfo fromId(Long id) {
        if (id == null) {
            return null;
        }
        DeliveryInfo deliveryInfo = new DeliveryInfo();
        deliveryInfo.setId(id);
        return deliveryInfo;
    }
}

package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.PaymentTypeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity PaymentType and its DTO PaymentTypeDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface PaymentTypeMapper extends EntityMapper<PaymentTypeDTO, PaymentType> {



    default PaymentType fromId(Long id) {
        if (id == null) {
            return null;
        }
        PaymentType paymentType = new PaymentType();
        paymentType.setId(id);
        return paymentType;
    }
}

package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.OpeningHourDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity OpeningHour and its DTO OpeningHourDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface OpeningHourMapper extends EntityMapper<OpeningHourDTO, OpeningHour> {



    default OpeningHour fromId(Long id) {
        if (id == null) {
            return null;
        }
        OpeningHour openingHour = new OpeningHour();
        openingHour.setId(id);
        return openingHour;
    }
}

package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.ResturantDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Resturant and its DTO ResturantDTO.
 */
@Mapper(componentModel = "spring", uses = {LocationMapper.class, OpeningHourMapper.class})
public interface ResturantMapper extends EntityMapper<ResturantDTO, Resturant> {

    @Mapping(source = "location.id", target = "locationId")
    @Mapping(source = "location.name", target = "locationName")
    ResturantDTO toDto(Resturant resturant);

    @Mapping(source = "locationId", target = "location")
    Resturant toEntity(ResturantDTO resturantDTO);

    default Resturant fromId(Long id) {
        if (id == null) {
            return null;
        }
        Resturant resturant = new Resturant();
        resturant.setId(id);
        return resturant;
    }
}

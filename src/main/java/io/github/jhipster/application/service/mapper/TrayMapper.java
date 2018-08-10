package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.TrayDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Tray and its DTO TrayDTO.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface TrayMapper extends EntityMapper<TrayDTO, Tray> {

    @Mapping(source = "user.id", target = "userId")
    TrayDTO toDto(Tray tray);

    @Mapping(source = "userId", target = "user")
    Tray toEntity(TrayDTO trayDTO);

    default Tray fromId(Long id) {
        if (id == null) {
            return null;
        }
        Tray tray = new Tray();
        tray.setId(id);
        return tray;
    }
}

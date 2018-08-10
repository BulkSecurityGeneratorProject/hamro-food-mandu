package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.TrayFoodDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity TrayFood and its DTO TrayFoodDTO.
 */
@Mapper(componentModel = "spring", uses = {TrayMapper.class, FoodMapper.class})
public interface TrayFoodMapper extends EntityMapper<TrayFoodDTO, TrayFood> {

    @Mapping(source = "tray.id", target = "trayId")
    @Mapping(source = "food.id", target = "foodId")
    @Mapping(source = "food.name", target = "foodName")
    TrayFoodDTO toDto(TrayFood trayFood);

    @Mapping(source = "trayId", target = "tray")
    @Mapping(source = "foodId", target = "food")
    TrayFood toEntity(TrayFoodDTO trayFoodDTO);

    default TrayFood fromId(Long id) {
        if (id == null) {
            return null;
        }
        TrayFood trayFood = new TrayFood();
        trayFood.setId(id);
        return trayFood;
    }
}

package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.FoodDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Food and its DTO FoodDTO.
 */
@Mapper(componentModel = "spring", uses = {CategoryMapper.class, ResturantMapper.class})
public interface FoodMapper extends EntityMapper<FoodDTO, Food> {

    @Mapping(source = "category.id", target = "categoryId")
    @Mapping(source = "category.name", target = "categoryName")
    @Mapping(source = "resturant.id", target = "resturantId")
    @Mapping(source = "resturant.name", target = "resturantName")
    FoodDTO toDto(Food food);

    @Mapping(source = "categoryId", target = "category")
    @Mapping(source = "resturantId", target = "resturant")
    Food toEntity(FoodDTO foodDTO);

    default Food fromId(Long id) {
        if (id == null) {
            return null;
        }
        Food food = new Food();
        food.setId(id);
        return food;
    }
}

package com.coderscampus.SpringSecurityJWTDemo.mappers;

import com.coderscampus.SpringSecurityJWTDemo.domain.Ingredient;
import com.coderscampus.SpringSecurityJWTDemo.dto.IngredientDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {RecipeMapper.class, OrderMapper.class})

public interface IngredientMapper {

    IngredientDto entityToDto(Ingredient ingredient);

    Ingredient dtoToEntity(IngredientDto ingredientDto);

    List<Ingredient> dtoListToEntityList(List<IngredientDto> ingredientDtos);

    List<IngredientDto> entityListToDtoList(List<Ingredient> ingredients);
}

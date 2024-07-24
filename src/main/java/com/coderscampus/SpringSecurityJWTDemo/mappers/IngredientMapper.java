package com.coderscampus.SpringSecurityJWTDemo.mappers;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {RecipeMapper.class, OrderMapper.class})
public interface IngredientMapper {
}

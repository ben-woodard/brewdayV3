package com.coderscampus.SpringSecurityJWTDemo.mappers;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {ProductMapper.class, IngredientMapper.class})
public interface RecipeMapper {
}

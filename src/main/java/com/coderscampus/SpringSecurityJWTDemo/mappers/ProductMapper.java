package com.coderscampus.SpringSecurityJWTDemo.mappers;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {RecipeMapper.class, BatchMapper.class, TurnMapper.class})
public interface ProductMapper {
}

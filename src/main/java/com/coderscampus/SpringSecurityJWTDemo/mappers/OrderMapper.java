package com.coderscampus.SpringSecurityJWTDemo.mappers;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {IngredientMapper.class, UserMapper.class})
public interface OrderMapper {
}

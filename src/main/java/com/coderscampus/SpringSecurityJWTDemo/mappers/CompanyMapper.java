package com.coderscampus.SpringSecurityJWTDemo.mappers;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {ProductMapper.class, OrderMapper.class, IngredientMapper.class, UserMapper.class})
public interface CompanyMapper {
}

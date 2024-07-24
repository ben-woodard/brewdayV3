package com.coderscampus.SpringSecurityJWTDemo.mappers;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {BatchMapper.class, ProductMapper.class})
public interface TurnMapper {
}

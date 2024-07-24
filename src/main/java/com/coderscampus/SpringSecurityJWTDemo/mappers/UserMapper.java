package com.coderscampus.SpringSecurityJWTDemo.mappers;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {ProductMapper.class, OrderMapper.class})
public interface UserMapper {
}

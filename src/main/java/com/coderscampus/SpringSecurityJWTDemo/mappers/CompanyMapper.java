package com.coderscampus.SpringSecurityJWTDemo.mappers;

import com.coderscampus.SpringSecurityJWTDemo.dto.UserDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ProductMapper.class, OrderMapper.class, IngredientMapper.class, UserMapper.class})
public interface CompanyMapper {

}

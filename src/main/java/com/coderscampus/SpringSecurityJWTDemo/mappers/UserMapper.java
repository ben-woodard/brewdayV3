package com.coderscampus.SpringSecurityJWTDemo.mappers;

import com.coderscampus.SpringSecurityJWTDemo.domain.User;
import com.coderscampus.SpringSecurityJWTDemo.dto.UserDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ProductMapper.class, OrderMapper.class})
public interface UserMapper {

    UserDto entityToDto (User user);

    User dtoToEntity (UserDto userDto);

    List<User> dtoListToEntity(List<UserDto> userDtos);

    List<UserDto> entityListToDto(List<User> users);
}

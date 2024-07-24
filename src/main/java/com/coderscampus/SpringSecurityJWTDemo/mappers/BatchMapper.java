package com.coderscampus.SpringSecurityJWTDemo.mappers;

import com.coderscampus.SpringSecurityJWTDemo.domain.Batch;
import com.coderscampus.SpringSecurityJWTDemo.dto.BatchDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {ProductMapper.class, TurnMapper.class})
public interface BatchMapper {

    BatchDto entityToDto(Batch batch);

    Batch dtoToEntity(BatchDto batchDto);

}

package com.youcode.citronix.mappers;

import com.youcode.citronix.dto.requestDto.ArbreRequestDto;
import com.youcode.citronix.dto.responseDto.ArbreResponseDto;
import com.youcode.citronix.entity.Arbre;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ArbreMapper {

    ArbreMapper INSTANCE = Mappers.getMapper(ArbreMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "champ.id", source = "champId")
    Arbre ArbreRequestDtoToArbre(ArbreRequestDto arbreRequestDto);

    ArbreResponseDto toResponseDto(Arbre arbre);
}

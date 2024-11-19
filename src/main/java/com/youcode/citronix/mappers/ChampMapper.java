package com.youcode.citronix.mappers;

import com.youcode.citronix.dto.requestDto.ChampRequestDto;
import com.youcode.citronix.dto.responseDto.ChampResponseDto;
import com.youcode.citronix.entity.Champ;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ChampMapper {
    ChampMapper INSTANCE = Mappers.getMapper(ChampMapper.class);

    @Mapping(target = "id", ignore = true)
    Champ ChampRequestDtoToChamp(ChampRequestDto ChampRequestDto);
    ChampResponseDto  champToChampResponseDto(Champ champ);
}

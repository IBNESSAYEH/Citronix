package com.youcode.citronix.mappers;

import com.youcode.citronix.dto.requestDto.RecoltDetailRequestDto;
import com.youcode.citronix.dto.responseDto.RecoltDetailResponseDto;
import com.youcode.citronix.entity.RecoltDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RecoltDetailMapper {

    RecoltDetailMapper INSTANCE = Mappers.getMapper(RecoltDetailMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "recolte.id", source = "recolteId")
    @Mapping(target = "arbre.id", source = "arbreId")
    RecoltDetail toEntity(RecoltDetailRequestDto recoltDetailRequestDto);

    RecoltDetailResponseDto toResponseDto(RecoltDetail recoltDetail);
}

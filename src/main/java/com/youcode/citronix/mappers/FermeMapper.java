package com.youcode.citronix.mappers;

import com.youcode.citronix.dto.requestDto.FermeRequestDto;
import com.youcode.citronix.dto.responseDto.FermeResponseDto;
import com.youcode.citronix.entity.Ferme;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;



@Mapper(componentModel = "spring")
public interface FermeMapper {

        FermeMapper INSTANCE = Mappers.getMapper(FermeMapper.class);

        @Mapping(target = "id", ignore = true)
        Ferme FermeRequestDtotoFerme(FermeRequestDto fermeRequestDto);


        FermeResponseDto toResponseDto(Ferme ferme);
}

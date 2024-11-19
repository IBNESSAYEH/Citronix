package com.youcode.citronix.mappers;


import com.youcode.citronix.dto.requestDto.VenteRequestDto;
import com.youcode.citronix.dto.responseDto.VenteResponseDto;
import com.youcode.citronix.entity.Vente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface VenteMapper {

    VenteMapper INSTANCE = Mappers.getMapper(VenteMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "recolte.id", source = "recolteId")
    Vente toEntity(VenteRequestDto venteRequestDto);

    VenteResponseDto toResponseDto(Vente vente);
}


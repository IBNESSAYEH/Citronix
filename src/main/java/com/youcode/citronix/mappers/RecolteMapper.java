package com.youcode.citronix.mappers;
import com.youcode.citronix.dto.requestDto.RecolteRequestDto;
import com.youcode.citronix.dto.responseDto.RecolteResponseDto;
import com.youcode.citronix.entity.Recolte;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RecolteMapper {

    RecolteMapper INSTANCE = Mappers.getMapper(RecolteMapper.class);

    @Mapping(target = "id", ignore = true)
    Recolte toEntity(RecolteRequestDto recolteRequestDto);

    RecolteResponseDto toResponseDto(Recolte recolte);
}

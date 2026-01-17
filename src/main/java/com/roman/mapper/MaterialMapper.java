package com.roman.mapper;

import com.roman.config.MapperConfig;
import com.roman.dto.MaterialRequestDto;
import com.roman.dto.MaterialResponseDto;
import com.roman.dto.material.MaterialUpdateRequestDto;
import com.roman.model.Material;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfig.class)
public interface MaterialMapper {
    Material toModel(MaterialRequestDto requestDto);

    MaterialResponseDto toDto(Material material);

    @Mapping(target = "id", ignore = true)
    void updateDto(@MappingTarget Material material, MaterialUpdateRequestDto requestDto);
}

package com.roman.mapper;

import com.roman.config.MapperConfig;
import com.roman.dto.material.MaterialRequestDto;
import com.roman.dto.material.MaterialResponseDto;
import com.roman.dto.material.MaterialUpdateRequestDto;
import com.roman.model.Material;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfig.class)
public interface MaterialMapper {
    Material toModel(MaterialRequestDto requestDto);

    MaterialResponseDto toDto(Material material);

    MaterialRequestDto toRequestDto(Material material);

    @Mapping(target = "id", ignore = true)
    void updateDto(@MappingTarget Material material, MaterialUpdateRequestDto requestDto);
}

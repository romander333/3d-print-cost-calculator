package com.roman.mapper;

import com.roman.config.MapperConfig;
import com.roman.dto.spool.SpoolMaterialRequestDto;
import com.roman.dto.spool.SpoolMaterialResponseDto;
import com.roman.model.Spool;
import jakarta.persistence.Entity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.jpa.repository.EntityGraph;

@Mapper(config = MapperConfig.class)
public interface SpoolMapper {
    Spool toModel(SpoolMaterialRequestDto requestDto);

    @Mapping(target = "materialId", source = "material.id")
    SpoolMaterialResponseDto toDto(Spool spoolMaterial);
}

package com.roman.dto.material;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record MaterialResponseDto(
        Long id,
        String type,
        String description,
        BigDecimal price,
        Integer weight,
        Double filamentDiameter,
        Double density,
        String color,
        String manufacturer,
        Integer quantity,
        Boolean active
) {}

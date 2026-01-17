package com.roman.dto;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record MaterialResponseDto(
        String type,
        String description,
        BigDecimal price,
        Integer weight,
        Double filamentDiameter,
        Double density,
        String color,
        String manufacturer,
        Boolean active
) {}

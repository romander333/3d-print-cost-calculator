package com.roman.dto.material;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record MaterialRequestDto(
        @NotBlank
        String type,

        String description,

        @NotNull
        @Positive
        BigDecimal price,

        @NotNull
        @Positive
        Integer weight,

        @Positive
        Double filamentDiameter,

        @NotNull
        @Positive
        Double density,

        @NotBlank
        String color,

        @NotBlank
        String manufacturer,

        @Positive
        Integer quantity,

        Boolean active
) {}
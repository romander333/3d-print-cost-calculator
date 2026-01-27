package com.roman.dto.spool;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;

public record SpoolMaterialRequestDto(
        @NotNull
        Long materialId,
        @NotNull
        @Positive
        BigDecimal price,
        @NotNull
        Integer currentWeight,
        @NotNull
        Integer initialWeight,
        LocalDate purchaseDate,
        Integer amount,
        Boolean active
)
{}

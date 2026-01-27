package com.roman.dto.material;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record MaterialUpdateRequestDto(
        @Size(min = 1)
        String description,

        @Positive
        BigDecimal price,

        @Positive
        Integer weight,

        @Positive Double density,

        @Size(min = 1, max = 255)
        String color,

        @Size(min = 1, max = 255)
        String material,

        @Size(min = 1, max = 255)
        String manufacturer,

        @Positive
        Integer quantity,

        Boolean active
) {}

package com.roman.service.util;

import com.roman.dto.material.MaterialRequestDto;
import com.roman.dto.material.MaterialResponseDto;
import com.roman.dto.material.MaterialUpdateRequestDto;
import com.roman.model.Material;

import java.awt.*;
import java.math.BigDecimal;

public class MaterialUtil {
    public static Material getMaterial() {
        return Material.builder()
                .id(1L)
                .color(String.valueOf(Color.BLACK))
                .type(Material.MaterialType.PLA)
                .price(BigDecimal.valueOf(120))
                .description("Nice material")
                .density(1.25)
                .manufacturer("PLA")
                .weight(1500)
                .filamentDiameter(1.75)
                .quantity(1)
                .build();
    }

    public static MaterialResponseDto getMaterialResponseDto() {
        return MaterialResponseDto.builder()
                .id(1L)
                .color(String.valueOf(Color.BLACK))
                .type(String.valueOf(Material.MaterialType.PLA))
                .price(BigDecimal.valueOf(120))
                .description("Nice material")
                .density(1.25)
                .manufacturer("PLA")
                .weight(1500)
                .filamentDiameter(1.75)
                .quantity(1)
                .build();
    }

    public static MaterialRequestDto getMaterialRequestDto() {
        return MaterialRequestDto.builder()
                .color(String.valueOf(Color.BLACK))
                .type(String.valueOf(Material.MaterialType.PLA))
                .price(BigDecimal.valueOf(120))
                .description("Nice material")
                .density(1.25)
                .manufacturer("PLA")
                .weight(1500)
                .filamentDiameter(1.75)
                .quantity(1)
                .build();
    }

    public static MaterialResponseDto getMaterialResponseDtoAfterUpdate() {
        return MaterialResponseDto.builder()
                .id(1L)
                .color(String.valueOf(Color.WHITE))
                .type(String.valueOf(Material.MaterialType.ABS))
                .price(BigDecimal.valueOf(100))
                .description("Nice material")
                .density(1.25)
                .manufacturer("PLA")
                .weight(2000)
                .filamentDiameter(1.75)
                .quantity(2)
                .build();
    }

    public static MaterialUpdateRequestDto getMaterialUpdateRequestDto() {
        return MaterialUpdateRequestDto.builder()
                .color(String.valueOf(Color.WHITE))
                .price(BigDecimal.valueOf(100))
                .description("Nice material")
                .density(1.25)
                .manufacturer("PLA")
                .weight(2000)
                .quantity(2)
                .build();
    }
}

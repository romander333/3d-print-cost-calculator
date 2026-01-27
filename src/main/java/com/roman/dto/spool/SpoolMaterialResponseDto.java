package com.roman.dto.spool;

import lombok.Builder;
import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
public record SpoolMaterialResponseDto(
        Long id,
        Long materialId,
        BigDecimal price,
        Integer currentWeight,
        Integer initialWeight,
        LocalDate purchaseDate,
        Boolean active
) {
}

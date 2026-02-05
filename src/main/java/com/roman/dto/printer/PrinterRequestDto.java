package com.roman.dto.printer;

import com.roman.model.Printer;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

@Builder
public record PrinterRequestDto(
        @NotBlank
        String name,
        @NotBlank
        String model,
        @NotNull
        @Positive
        double powerConsumptionWatts,
        Printer.Status status,
        Long currentSpool,
        @NotNull
        @Positive
        Integer maxWidth,
        @NotNull
        @Positive
        Integer maxDepth,
        @NotNull
        @Positive
        Integer maxHeight
) {
}

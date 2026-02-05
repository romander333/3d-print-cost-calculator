package com.roman.dto.printer;

import com.roman.model.Printer;
import jakarta.validation.constraints.Positive;

public record PrinterUpdateRequestDto(
        String name,
        String model,
        @Positive
        Double powerConsumptionWatts,
        Printer.Status status,
        Long currentSpool,
        @Positive
        Integer maxWidth,
        @Positive
        Integer maxDepth,
        @Positive
        Integer maxHeight
) {
}

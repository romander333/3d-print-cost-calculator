package com.roman.dto.printer;

import com.roman.model.Printer;
import lombok.Builder;

@Builder
public record PrinterResponseDto(
        Long id,
        String name,
        String model,
        double powerConsumptionWatts,
        Printer.Status status,
        Long currentSpool,
        int maxWidth,
        int maxDepth,
        int maxHeight
) {
}

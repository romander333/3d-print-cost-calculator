package com.roman.service.util;

import com.roman.model.Spool;

import java.math.BigDecimal;
import java.time.LocalDate;

import static com.roman.service.util.MaterialUtil.getMaterial;
import static com.roman.service.util.PrinterUtil.getPrinter;

public class SpoolUtil {
    public static Spool getSpool() {
        return Spool.builder()
                .id(1L)
                .material(getMaterial())
                .price(BigDecimal.valueOf(120))
                .currentWeight(1500)
                .purchaseDate(LocalDate.now())
                .currentPrinter(getPrinter())
                .build();
    }
}

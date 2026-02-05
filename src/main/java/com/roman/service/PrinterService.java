package com.roman.service;

import com.roman.dto.printer.PrinterRequestDto;
import com.roman.dto.printer.PrinterResponseDto;
import com.roman.dto.printer.PrinterUpdateRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PrinterService {
    PrinterResponseDto createPrinter(PrinterRequestDto printerRequestDto);

    PrinterResponseDto getPrinterById(Long id);

    Page<PrinterResponseDto> getAllPrinters(Pageable pageable);

    PrinterResponseDto updatePrinter(Long id, PrinterUpdateRequestDto requestDto);

    PrinterResponseDto unmountSpool(Long printerId);

    PrinterResponseDto mountSpool(Long printerId, Long spoolId);

    void addPrintingHours(Long printerId, Double hours);

    void restorePrinter(Long id);

    void deletePrinter(Long id);
}

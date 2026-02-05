package com.roman.controller;

import com.roman.dto.printer.PrinterRequestDto;
import com.roman.dto.printer.PrinterResponseDto;
import com.roman.dto.printer.PrinterUpdateRequestDto;
import com.roman.service.PrinterService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/printers")
public class PrinterController {
    private final PrinterService printerService;

    @GetMapping("/{id}")
    public PrinterResponseDto getPrinterById(@PathVariable Long id) {
        return printerService.getPrinterById(id);
    }

    @GetMapping
    public Page<PrinterResponseDto> getAllPrinters(Pageable pageable) {
        return printerService.getAllPrinters(pageable);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public PrinterResponseDto createPrinter(@RequestBody @Valid PrinterRequestDto printerRequestDto) {
        return printerService.createPrinter(printerRequestDto);
    }

    @PutMapping("/{id}")
    public PrinterResponseDto updatePrinter(@PathVariable Long id, @RequestBody @Valid PrinterUpdateRequestDto printerRequestDto) {
        return printerService.updatePrinter(id, printerRequestDto);
    }

    @PatchMapping("/unmountSpool/{id}")
    public PrinterResponseDto unmountSpool(@PathVariable Long id) {
        return printerService.unmountSpool(id);
    }

    @PatchMapping("/{id}/mountSpool/{spoolId}")
    public PrinterResponseDto mountSpool(@PathVariable Long id,@PathVariable Long spoolId) {
        return printerService.mountSpool(id, spoolId);
    }

    @PatchMapping("/restore/{id}")
    public void restore(@PathVariable Long id) {
        printerService.restorePrinter(id);
    }

    @DeleteMapping("/{id}")
    public void deletePrinter(@PathVariable Long id) {
        printerService.deletePrinter(id);
    }
}

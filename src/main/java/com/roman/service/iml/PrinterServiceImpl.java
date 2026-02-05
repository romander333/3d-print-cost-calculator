package com.roman.service.iml;

import com.roman.dto.printer.PrinterRequestDto;
import com.roman.dto.printer.PrinterResponseDto;
import com.roman.dto.printer.PrinterUpdateRequestDto;
import com.roman.exception.EntityNotFoundException;
import com.roman.exception.SpoolAlreadyMountedException;
import com.roman.mapper.PrinterMapper;
import com.roman.model.Printer;
import com.roman.model.Spool;
import com.roman.repository.PrinterRepository;
import com.roman.repository.SpoolRepository;
import com.roman.service.PrinterService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PrinterServiceImpl implements PrinterService {
    private final PrinterRepository printerRepository;
    private final PrinterMapper printerMapper;
    private final SpoolRepository spoolRepository;

    @Override
    public PrinterResponseDto createPrinter(PrinterRequestDto printerRequestDto) {
        Printer model = printerMapper.toModel(printerRequestDto);
        if (printerRequestDto.status() != null) {
            model.setStatus(printerRequestDto.status());
        }
        if (model.getCurrentSpool().getCurrentPrinter() != null) {
            throw new SpoolAlreadyMountedException("Cannot mount spool more than once");
        }
        Printer savedPrinter = printerRepository.save(model);
        return printerMapper.toDto(savedPrinter);
    }

    @Override
    public PrinterResponseDto getPrinterById(Long id) {
        return printerMapper.toDto(findPrinterById(id));
    }

    @Override
    public Page<PrinterResponseDto> getAllPrinters(Pageable pageable) {
        Page<Printer> existingPrinters = printerRepository.findAll(pageable);
        return existingPrinters.map(printerMapper::toDto);
    }

    @Override
    @Transactional
    public PrinterResponseDto updatePrinter(Long id, PrinterUpdateRequestDto requestDto) {
        Printer printerById = findPrinterById(id);
        printerMapper.updateModel(printerById, requestDto);
        if (requestDto.currentSpool() != null) {
            Spool spool = getSpool(requestDto.currentSpool());
            printerById.setCurrentSpool(spool);
        }
        Printer savedPrinter = printerRepository.save(printerById);

        return printerMapper.toDto(savedPrinter);
    }

    @Override
    public PrinterResponseDto unmountSpool(Long printerId) {
        Printer printerById = findPrinterById(printerId);
        printerById.setCurrentSpool(null);
        printerById.setStatus(Printer.Status.PAUSED);
        return printerMapper.toDto(printerRepository.save(printerById));
    }

    @Override
    @Transactional
    public PrinterResponseDto mountSpool(Long printerId, Long spoolId) {
        Spool spool = getSpool(spoolId);
        Printer printerById = findPrinterById(printerId);

        printerById.setStatus(Printer.Status.IDLE);
        printerById.setCurrentSpool(spool);

        return printerMapper.toDto(printerRepository.save(printerById));
    }

    @Override
    public void addPrintingHours(Long printerId, Double hours) {
        Printer printerById = findPrinterById(printerId);
        printerById.setTotalPrintHours(hours);
        printerRepository.save(printerById);
    }

    @Override
    public void restorePrinter(Long id) {
        Printer printerById = printerRepository.findIncludeDeletedById(id);
        printerById.setDeleted(false);
        printerRepository.save(printerById);
    }

    @Override
    public void deletePrinter(Long id) {
        printerRepository.deleteById(id);
    }
    
    private Printer findPrinterById(Long id) {
        return printerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cannot find printer with id: " + id));
    }

    private Spool getSpool(Long spoolId) {
        Spool spool = spoolRepository.findById(spoolId)
                .orElseThrow(() -> new EntityNotFoundException("Spool not found with id: " + spoolId));
        if (spool.getCurrentPrinter() != null) {
            throw new SpoolAlreadyMountedException("Cannot mount spool with id: " + spoolId);
        }
        return spool;
    }

}

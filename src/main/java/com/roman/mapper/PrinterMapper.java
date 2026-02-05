package com.roman.mapper;

import com.roman.config.MapperConfig;
import com.roman.dto.printer.PrinterRequestDto;
import com.roman.dto.printer.PrinterResponseDto;
import com.roman.dto.printer.PrinterUpdateRequestDto;
import com.roman.model.Printer;
import org.mapstruct.*;

@Mapper(config = MapperConfig.class)
public interface PrinterMapper {

    @Mapping(target = "currentSpool", ignore = true)
    Printer toModel(PrinterRequestDto requestDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "currentSpool", ignore = true)
    void updateModel(@MappingTarget Printer printer, PrinterUpdateRequestDto requestDto);

    @Mapping(target = "currentSpool", source = "currentSpool.id")
    PrinterResponseDto toDto(Printer printer);
}

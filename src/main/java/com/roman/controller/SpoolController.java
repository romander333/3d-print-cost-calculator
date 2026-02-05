package com.roman.controller;

import com.roman.dto.spool.SpoolMaterialRequestDto;
import com.roman.dto.spool.SpoolMaterialResponseDto;
import com.roman.service.SpoolService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/spools")
@RequiredArgsConstructor
public class SpoolController {
    private final SpoolService spoolMaterialService;

    @GetMapping("/{materialId}")
    public List<SpoolMaterialResponseDto> getSpoolsByMaterialId(@PathVariable Long materialId) {
        return spoolMaterialService.getAllSpoolsByMaterialId(materialId);
    }

    @GetMapping("/{id}")
    public SpoolMaterialResponseDto getSpoolById(@PathVariable Long id) {
        return spoolMaterialService.getSpoolById(id);
    }

    @PostMapping("/create")
    public List<SpoolMaterialResponseDto> addSpools(@RequestBody @Valid SpoolMaterialRequestDto requestDto) {
        return spoolMaterialService.addSpools(requestDto);
    }

}

package com.roman.controller;

import com.roman.dto.material.MaterialRequestDto;
import com.roman.dto.material.MaterialResponseDto;
import com.roman.dto.material.MaterialUpdateRequestDto;
import com.roman.service.MaterialService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/materials")
public class MaterialController {
    private final MaterialService materialService;

    @GetMapping("/{id}")
    public MaterialResponseDto getMaterial(@PathVariable Long id) {
        return materialService.getMaterialById(id);
    }

    @GetMapping
    public List<MaterialResponseDto> getAllMaterials() {
        return materialService.getAllMaterials();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MaterialResponseDto createMaterial(@RequestBody @Valid MaterialRequestDto requestDto) {
        return materialService.createMaterial(requestDto);
    }

    @PutMapping("/update/{id}")
    public MaterialResponseDto updateMaterial(@PathVariable Long id, @RequestBody @Valid MaterialUpdateRequestDto requestDto) {
        return materialService.updateMaterial(id, requestDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMaterial(@PathVariable Long id) {
        materialService.deleteMaterial(id);
    }
}

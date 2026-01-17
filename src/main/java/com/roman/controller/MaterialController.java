package com.roman.controller;

import com.roman.dto.MaterialRequestDto;
import com.roman.dto.MaterialResponseDto;
import com.roman.dto.material.MaterialUpdateRequestDto;
import com.roman.service.MaterialService;
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

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public MaterialResponseDto createMaterial(@RequestBody MaterialRequestDto requestDto) {
        return materialService.createMaterial(requestDto);
    }

    @PutMapping("/update/{id}")
    public MaterialResponseDto updateMaterial(@PathVariable Long id, @RequestBody MaterialUpdateRequestDto requestDto) {
        return materialService.updateMaterial(id, requestDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMaterial(@PathVariable Long id) {
        materialService.deleteMaterial(id);
    }
}

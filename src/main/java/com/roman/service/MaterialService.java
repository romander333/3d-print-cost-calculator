package com.roman.service;

import com.roman.dto.MaterialRequestDto;
import com.roman.dto.MaterialResponseDto;
import com.roman.dto.material.MaterialUpdateRequestDto;

import java.util.List;

public interface MaterialService {
    MaterialResponseDto getMaterialById(Long id);

    List<MaterialResponseDto> getAllMaterials();

    MaterialResponseDto createMaterial(MaterialRequestDto requestDto);

    MaterialResponseDto updateMaterial(Long id, MaterialUpdateRequestDto requestDto);

    void deleteMaterial(Long id);
}

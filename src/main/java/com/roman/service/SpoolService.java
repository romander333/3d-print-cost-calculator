package com.roman.service;

import com.roman.dto.spool.SpoolMaterialRequestDto;
import com.roman.dto.spool.SpoolMaterialResponseDto;

import java.util.List;

public interface SpoolService {
    List<SpoolMaterialResponseDto> addSpools(SpoolMaterialRequestDto requestDto);

    List<SpoolMaterialResponseDto> getAllSpoolsByMaterialId(Long materialId);

    SpoolMaterialResponseDto getSpoolById(Long id);
}

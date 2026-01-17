package com.roman.service.iml;

import com.roman.dto.MaterialRequestDto;
import com.roman.dto.MaterialResponseDto;
import com.roman.dto.material.MaterialUpdateRequestDto;
import com.roman.exception.EntityNotFoundException;
import com.roman.mapper.MaterialMapper;
import com.roman.model.Material;
import com.roman.repository.MaterialRepository;
import com.roman.service.MaterialService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MaterialServiceImpl implements MaterialService {
    private final MaterialRepository materialRepository;
    private final MaterialMapper materialMapper;
    @Override
    public MaterialResponseDto getMaterialById(Long id) {
        Material material = findMaterialById(id);
        return materialMapper.toDto(material);
    }

    @Override
    public List<MaterialResponseDto> getAllMaterials() {
        return materialRepository.findAll().stream()
                .map(materialMapper::toDto)
                .toList();
    }

    @Override
    public MaterialResponseDto createMaterial(MaterialRequestDto requestDto) {
        Material materialModel = materialMapper.toModel(requestDto);
        Material savedMaterial = materialRepository.save(materialModel);
        return materialMapper.toDto(savedMaterial);
    }

    @Override
    public MaterialResponseDto updateMaterial(Long id, MaterialUpdateRequestDto requestDto) {
        Material material = findMaterialById(id);
        materialMapper.updateDto(material, requestDto);
        Material savedMaterial = materialRepository.save(material);
        return materialMapper.toDto(savedMaterial);
    }

    @Override
    public void deleteMaterial(Long id) {
        findMaterialById(id);
        materialRepository.deleteById(id);
    }

    private Material findMaterialById(Long id) {
        return materialRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Material not found"));
    }
}

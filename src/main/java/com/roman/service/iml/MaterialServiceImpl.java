package com.roman.service.iml;

import com.roman.dto.material.MaterialRequestDto;
import com.roman.dto.material.MaterialResponseDto;
import com.roman.dto.material.MaterialUpdateRequestDto;
import com.roman.exception.EntityNotFoundException;
import com.roman.mapper.MaterialMapper;
import com.roman.model.Material;
import com.roman.model.Spool;
import com.roman.repository.MaterialRepository;
import com.roman.repository.SpoolRepository;
import com.roman.service.MaterialService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MaterialServiceImpl implements MaterialService {
    private final MaterialRepository materialRepository;
    private final MaterialMapper materialMapper;
    private final SpoolRepository spoolRepository;
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
    @Transactional
    public MaterialResponseDto createMaterial(MaterialRequestDto requestDto) {
        Material materialModel = materialMapper.toModel(requestDto);

        int spoolsQuantity = (requestDto.quantity() == null || requestDto.quantity() < 1) ? 1 : requestDto.quantity();
        Integer singeWeight = materialModel.getWeight();
        materialModel.setWeight(spoolsQuantity * materialModel.getWeight());
        Material savedMaterial = materialRepository.save(materialModel);

        createSpools(spoolsQuantity, savedMaterial, singeWeight);

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
    public MaterialResponseDto saveMaterial(MaterialRequestDto requestDto) {
        if (requestDto == null) {
           throw new EntityNotFoundException("Cannot mapping and save null entity");
        }
        Material savedMaterial = materialRepository.save(materialMapper.toModel(requestDto));

        return materialMapper.toDto(savedMaterial);
    }

    @Override
    public void deleteMaterial(Long id) {
        findMaterialById(id);
        materialRepository.deleteById(id);
    }

    private Material findMaterialById(Long id) {
        return materialRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Material not found by id" + id));
    }

    private List<Spool> createSpools(int spoolsQuantity, Material material, Integer singeWeight) {
        List<Spool> spoolCollection = new ArrayList<>();
        for (int i = 0; i < spoolsQuantity; i++) {
            Spool spoolMaterial = Spool.builder()
                    .material(material)
                    .currentWeight(singeWeight)
                    .initialWeight(singeWeight)
                    .purchaseDate(LocalDate.now())
                    .price(material.getPrice())
                    .build();

            spoolCollection.add(spoolMaterial);
        }
        return spoolRepository.saveAll(spoolCollection);
    }
}

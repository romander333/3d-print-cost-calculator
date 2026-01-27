package com.roman.service.iml;

import com.roman.dto.spool.SpoolMaterialRequestDto;
import com.roman.dto.spool.SpoolMaterialResponseDto;
import com.roman.exception.EntityNotFoundException;
import com.roman.mapper.SpoolMapper;
import com.roman.model.Material;
import com.roman.model.Spool;
import com.roman.repository.MaterialRepository;
import com.roman.repository.SpoolRepository;
import com.roman.service.SpoolMaterialService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SpoolServiceImpl implements SpoolMaterialService {
    private final SpoolRepository spoolMaterialRepository;
    private final SpoolMapper spoolMapper;
    private final MaterialRepository materialRepository;

    @Override
    @Transactional
    public List<SpoolMaterialResponseDto> addSpools(SpoolMaterialRequestDto requestDto) {
        List<Spool> spoolMaterials = new ArrayList<>();
        Integer generalNewSpoolsWight = requestDto.currentWeight() * requestDto.amount();
        Material materialFromSpool = materialRepository.findById(requestDto.materialId())
                .orElseThrow(() -> new EntityNotFoundException("Material not found by id: " + requestDto.materialId()));

        int amountToCreate = requestDto.amount() != null ? requestDto.amount() : 1;

        for (int i = 0; i < amountToCreate; i++) {
            Spool spool = Spool.builder()
                    .price(requestDto.price())
                    .currentWeight(requestDto.currentWeight())
                    .initialWeight(requestDto.initialWeight())
                    .material(materialFromSpool)
                    .purchaseDate(requestDto.purchaseDate() != null ? requestDto.purchaseDate() : LocalDate.now())
                    .active(requestDto.active() != null ? requestDto.active() : true)
                    .build();

            spoolMaterials.add(spool);
        }
        List<Spool> spoolMaterialsCollection = spoolMaterialRepository.saveAll(spoolMaterials);

        BigDecimal actualPrice = getActualPrice(materialFromSpool, spoolMaterialsCollection.get(0), generalNewSpoolsWight);

        materialFromSpool.setQuantity(materialFromSpool.getQuantity() + amountToCreate);
        materialFromSpool.setWeight(materialFromSpool.getWeight() + generalNewSpoolsWight);
        materialFromSpool.setPrice(actualPrice);

        return spoolMaterialsCollection.stream()
                .map(spoolMapper::toDto)
                .toList();
    }

    @Override
    public List<SpoolMaterialResponseDto> getAllSpoolsByMaterialId(Long materialId) {
        List<Spool> allSpoolsByMaterialId = spoolMaterialRepository.findAllByMaterial_Id(materialId);
        return allSpoolsByMaterialId.stream()
                .map(spoolMapper::toDto)
                .toList();
    }

    @Override
    public SpoolMaterialResponseDto getSpoolById(Long id) {
        Spool spoolMaterial = spoolMaterialRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Spool not found by id: " + id));
        return spoolMapper.toDto(spoolMaterial);
    }

    private BigDecimal getActualPrice(Material material, Spool spoolMaterial, Integer generalNewSpoolsWight) {
        int oldPrice = material.getPrice().intValue();
        int actualPrice = spoolMaterial.getPrice().intValue();
        int generalMaterialWeight = material.getWeight();

        log.info("Old price: " + oldPrice);
        log.info("New price: " + actualPrice);
        log.info("General materialWeight: " + generalMaterialWeight);
        log.info("General new materialWeight: " + generalNewSpoolsWight);
        int averagePrice = (((generalMaterialWeight * oldPrice) + (generalNewSpoolsWight * actualPrice))) / (generalMaterialWeight + generalNewSpoolsWight);

        return BigDecimal.valueOf(averagePrice);
    }
}

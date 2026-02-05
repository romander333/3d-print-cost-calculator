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
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.roman.service.util.MaterialUtil.*;

import static com.roman.service.util.SpoolUtil.getSpool;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MaterialServiceImplTest {
    @Mock
    private MaterialRepository materialRepository;
    @Mock
    private MaterialMapper materialMapper;
    @Mock
    private SpoolRepository spoolRepository;

    @InjectMocks
    private MaterialServiceImpl materialServiceImpl;


    @Test
    @DisplayName("Create Material when valid request provided and should return dto")
    void createMaterial_WithValidData_ShouldReturnDto() {
        Material material = getMaterial();

        MaterialResponseDto expected = getMaterialResponseDto();

        MaterialRequestDto materialRequestDto = getMaterialRequestDto();

        when(materialMapper.toModel(materialRequestDto)).thenReturn(material);
        when(materialMapper.toDto(material)).thenReturn(expected);
        when(materialRepository.save(material)).thenReturn(material);

        MaterialResponseDto actual = materialServiceImpl.createMaterial(materialRequestDto);

        assertThat(actual).isEqualTo(expected);

        verify(materialRepository, times(1)).save(material);
        verify(materialMapper, times(1)).toModel(materialRequestDto);
        verify(materialMapper, times(1)).toDto(material);
    }

    @Test
    @DisplayName("Save Material when valid request provided and should return dto")
    void saveMaterial_WithValidData_ShouldReturnDto() {
        Material material = getMaterial();
        MaterialResponseDto expected = getMaterialResponseDto();
        MaterialRequestDto materialRequestDto = getMaterialRequestDto();

        when(materialMapper.toModel(materialRequestDto)).thenReturn(material);
        when(materialMapper.toDto(material)).thenReturn(expected);
        when(materialRepository.save(material)).thenReturn(material);
        MaterialResponseDto actual = materialServiceImpl.saveMaterial(materialRequestDto);

        assertThat(actual).isEqualTo(expected);

        verify(materialRepository, times(1)).save(material);
        verify(materialMapper, times(1)).toModel(materialRequestDto);
        verify(materialMapper, times(1)).toDto(material);
    }

    @Test
    @DisplayName("Try to Save Material when invalid request provided and should throw EntityNotFoundException")
    void saveMaterial_WithInData_ShouldThrowNewEntityNotFoundException() {
        MaterialRequestDto materialRequestDto = null;

        Exception exception = assertThrows(EntityNotFoundException.class,
                () -> materialServiceImpl.saveMaterial(materialRequestDto));
        assertThat(exception.getMessage()).isEqualTo("Cannot mapping and save null entity");
    }

    @Test
    @DisplayName("Update Material when valid request provided and should return dto")
    void updateMaterial_WithValidData_ShouldReturnDto() {
        Material material = getMaterial();
        MaterialResponseDto expected = getMaterialResponseDtoAfterUpdate();
        MaterialUpdateRequestDto materialUpdateRequestDto = getMaterialUpdateRequestDto();

        doNothing().when(materialMapper).updateDto(material, materialUpdateRequestDto);
        when(materialRepository.findById(material.getId())).thenReturn(Optional.of(material));
        when(materialRepository.save(material)).thenReturn(material);
        when(materialMapper.toDto(material)).thenReturn(expected);

        MaterialResponseDto actual = materialServiceImpl.updateMaterial(material.getId(), materialUpdateRequestDto);

        assertThat(actual).isEqualTo(expected);

        verify(materialRepository, times(1)).findById(material.getId());
        verify(materialMapper, times(1)).updateDto(material, materialUpdateRequestDto);
        verify(materialMapper, times(1)).toDto(material);
    }

    @Test
    @DisplayName("Try to Update Material when invalid request provided and should throw EntityNotFoundException")
    void updateMaterial_WithInValidData_ShouldThrowNewEntityNotFoundException() {
        Long incorrectId = -10L;
        MaterialUpdateRequestDto materialRequestDto = getMaterialUpdateRequestDto();

        Exception exception = assertThrows(EntityNotFoundException.class,
                () -> materialServiceImpl.updateMaterial(incorrectId, materialRequestDto));

        assertThat(exception.getMessage()).isEqualTo("Material not found by id" + incorrectId);
    }
}
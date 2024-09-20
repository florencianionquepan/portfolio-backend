package com.flower.portfolio.dto.mapper;

import com.flower.portfolio.dto.ProgramDTO;
import com.flower.portfolio.model.AcademicProgram;

import java.util.List;


public interface IProgramMapper {
    AcademicProgram mapToEntity(ProgramDTO dto);
    ProgramDTO mapToDTO(AcademicProgram entity);
    List<ProgramDTO> mapToListDTO(List<AcademicProgram> programs);
}

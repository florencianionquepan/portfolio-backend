package com.flower.portfolio.dto.mapper;

import com.flower.portfolio.dto.ProgramDTO;
import com.flower.portfolio.model.AcademicProgram;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProgramMapper implements IProgramMapper{

    @Override
    public List<ProgramDTO> mapToListDTO(List<AcademicProgram> programs) {
        return programs.stream()
                .map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public AcademicProgram mapToEntity(ProgramDTO dto) {
        AcademicProgram entity=new AcademicProgram();
        if(dto.getId()!=null){
            entity.setId(dto.getId());
        }
        entity.setName(dto.getName());
        entity.setInstitution(dto.getInstitution());
        entity.setDegreeType(dto.getDegreeType());
        entity.setStartDate(dto.getStartDate());
        entity.setEndDate(dto.getEndDate());
        entity.setStatus(dto.getStatus());
        return entity;
    }

    @Override
    public ProgramDTO mapToDTO(AcademicProgram entity) {
        ProgramDTO dto = new ProgramDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setInstitution(entity.getInstitution());
        dto.setDegreeType(entity.getDegreeType());
        dto.setStartDate(entity.getStartDate());
        dto.setEndDate(entity.getEndDate());
        dto.setStatus(entity.getStatus());

        return dto;
    }
}

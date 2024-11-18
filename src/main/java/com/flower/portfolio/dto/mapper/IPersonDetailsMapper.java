package com.flower.portfolio.dto.mapper;

import com.flower.portfolio.dto.PersonDTO;
import com.flower.portfolio.dto.PersonWithDetailsDTO;
import com.flower.portfolio.dto.ProgramDTO;
import com.flower.portfolio.dto.WebProjectDTO;

import java.util.List;

public interface IPersonDetailsMapper {
    PersonWithDetailsDTO mapToDto(PersonDTO person, List<ProgramDTO> programs, List<WebProjectDTO> projects);
}

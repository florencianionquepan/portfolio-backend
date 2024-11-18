package com.flower.portfolio.dto.mapper;

import com.flower.portfolio.dto.PersonDTO;
import com.flower.portfolio.dto.PersonWithDetailsDTO;
import com.flower.portfolio.dto.ProgramDTO;
import com.flower.portfolio.dto.WebProjectDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PersonDetailsMapper implements IPersonDetailsMapper{


    @Override
    public PersonWithDetailsDTO mapToDto(PersonDTO person, List<ProgramDTO> programs, List<WebProjectDTO> projects) {
        PersonWithDetailsDTO dto=new PersonWithDetailsDTO();
        dto.setPerson(person);
        dto.setPrograms(programs);
        dto.setProjects(projects);
        return dto;
    }
}

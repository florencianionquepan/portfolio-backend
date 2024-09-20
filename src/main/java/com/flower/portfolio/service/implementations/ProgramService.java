package com.flower.portfolio.service.implementations;

import com.flower.portfolio.dto.ProgramDTO;
import com.flower.portfolio.dto.mapper.IProgramMapper;
import com.flower.portfolio.model.AcademicProgram;
import com.flower.portfolio.repository.ProgramRepository;
import com.flower.portfolio.service.interfaces.IProgramService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProgramService implements IProgramService {

    private IProgramMapper mapper;
    private ProgramRepository repo;
    private PersonService personService;

    @Override
    public List<ProgramDTO> programsByPerson(Long idPerson) {
        List<AcademicProgram> programs=this.repo.findByPersonId(idPerson);
        return this.mapper.mapToListDTO(programs);
    }

    @Override
    public ProgramDTO post(ProgramDTO dto) {
        AcademicProgram entity=this.mapper.mapToEntity(dto);
        return this.mapper.mapToDTO(this.repo.save(entity));
    }

    @Override
    public ProgramDTO update(ProgramDTO dto, Long id) {
        return null;
    }
}

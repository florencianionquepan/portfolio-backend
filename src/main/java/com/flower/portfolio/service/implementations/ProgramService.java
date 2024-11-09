package com.flower.portfolio.service.implementations;

import com.flower.portfolio.dto.ProgramDTO;
import com.flower.portfolio.dto.mapper.IProgramMapper;
import com.flower.portfolio.model.AcademicProgram;
import com.flower.portfolio.model.Person;
import com.flower.portfolio.repository.IPersonRepository;
import com.flower.portfolio.repository.IProgramRepository;
import com.flower.portfolio.service.interfaces.IProgramService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProgramService implements IProgramService {

    private final IProgramMapper mapper;
    private final IProgramRepository repo;
    private final IPersonRepository personRepo;

    public ProgramService(IProgramMapper mapper,
                          IProgramRepository repo,
                          IPersonRepository personRepo) {
        this.mapper = mapper;
        this.repo = repo;
        this.personRepo = personRepo;
    }

    @Override
    public List<ProgramDTO> programsByPerson(Long idPerson) {
        List<AcademicProgram> programs=this.repo.findByPersonId(idPerson);
        return this.mapper.mapToListDTO(programs);
    }

    @Override
    public ProgramDTO post(ProgramDTO dto, Long idPerson) {
        Optional<Person> oPerson=this.personRepo.findById(idPerson);
        if(oPerson.isEmpty()){
            //...lanzar excepcion
        }
        AcademicProgram entity=this.mapper.mapToEntity(dto);
        entity.setPerson(oPerson.get());
        return this.mapper.mapToDTO(this.repo.save(entity));
    }

    @Override
    public ProgramDTO update(ProgramDTO dto, Long idProgram) {
        Optional<AcademicProgram> oP=this.repo.findById(idProgram);
        if(oP.isEmpty()){
            //excepcion
        }
        Person person=oP.get().getPerson();
        AcademicProgram programModified=this.mapper.mapToEntity(dto);
        programModified.setPerson(person);
        programModified.setId(oP.get().getId());
        return this.mapper.mapToDTO(this.repo.save(programModified));
    }
}

package com.flower.portfolio.service.implementations;

import com.flower.portfolio.dto.*;
import com.flower.portfolio.dto.mapper.IPersonDetailsMapper;
import com.flower.portfolio.dto.mapper.IPersonMapper;
import com.flower.portfolio.exception.NonExistingException;
import com.flower.portfolio.model.Person;
import com.flower.portfolio.repository.IPersonRepository;
import com.flower.portfolio.service.interfaces.IPersonService;
import com.flower.portfolio.service.interfaces.IProgramService;
import com.flower.portfolio.service.interfaces.IWebProjectService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService implements IPersonService {

    private final IPersonRepository repo;
    private final IPersonMapper mapper;
    private final IProgramService programService;
    private final IWebProjectService projectService;
    private final IPersonDetailsMapper detailsMapper;

    public PersonService(IPersonRepository PersonRepo,
                         IPersonMapper mapper,
                         IProgramService programService,
                         IWebProjectService projectService,
                         IPersonDetailsMapper detailsMapper) {
        repo = PersonRepo;
        this.mapper = mapper;
        this.programService = programService;
        this.projectService = projectService;
        this.detailsMapper = detailsMapper;
    }

    @Override
    public PersonWithDetailsDTO getAllData(Long idPerson) {
        Optional<Person> oPerson=this.repo.findById(idPerson);
        if(oPerson.isEmpty()){
            throw new NonExistingException(
                    String.format("The person with id %d doesn't exist",
                            idPerson
                    )
            );
        }
        Person person=oPerson.get();
        List<ProgramDTO> programs=this.programService.programsByPerson(person.getId());
        List<WebProjectDTO> projects=this.projectService.projectsByPerson(person.getId());
        return this.detailsMapper.mapToDto(this.mapper.mapToDto(person),programs,projects);
    }

    @Override
    public ContactInfoDTO getSensitiveData(Long idPerson) {
        Optional<Person> p=this.repo.findById(idPerson);
        return this.mapper.mapToSensitiveData(p.orElse(null));
    }

    @Override
    public PersonDTO get(Long idPerson) {
        Optional<Person> oPerson=this.repo.findById(idPerson);
        if(oPerson.isEmpty()){
            throw new NonExistingException(
                    String.format("The person with id %d doesn't exist",
                            idPerson
                    )
            );
        }
        Person person=oPerson.get();
        return this.mapper.mapToDto(person);
    }

    @Override
    public PersonDTO post(PersonDTO person) {
        Person entity=this.mapper.mapToEntity(person);
        Person created=this.repo.save(entity);
        return this.mapper.mapToDto(created);
    }

    @Override
    public PersonDTO update(Long id, PersonDTO person) {
        return null;
    }
}

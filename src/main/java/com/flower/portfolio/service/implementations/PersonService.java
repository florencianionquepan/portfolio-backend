package com.flower.portfolio.service.implementations;

import com.flower.portfolio.dto.ContactInfoDTO;
import com.flower.portfolio.dto.PersonDTO;
import com.flower.portfolio.dto.mapper.IPersonMapper;
import com.flower.portfolio.model.Person;
import com.flower.portfolio.repository.PersonRepository;
import com.flower.portfolio.service.interfaces.IPersonService;
import org.springframework.stereotype.Service;

@Service
public class PersonService implements IPersonService {

    private final PersonRepository repo;
    private final IPersonMapper mapper;

    public PersonService(PersonRepository PersonRepo,
                         IPersonMapper mapper) {
        repo = PersonRepo;
        this.mapper = mapper;
    }

    @Override
    public ContactInfoDTO getSensitiveData(String lastname) {
        Person p=this.repo.findByLastName(lastname);
        return this.mapper.mapToSensitiveData(p);
    }

    @Override
    public PersonDTO get(String lastname) {
        return this.mapper.mapToDto(this.repo.findByLastName(lastname));
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

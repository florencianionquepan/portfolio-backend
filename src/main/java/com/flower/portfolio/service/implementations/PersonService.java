package com.flower.portfolio.service.implementations;

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
    public PersonDTO get(String lastname) {
        return this.mapper.mapToDto(this.repo.findByLastName(lastname));
    }

    @Override
    public PersonDTO post(Person person) {
        return null;
    }

    @Override
    public PersonDTO update(Long id, Person person) {
        return null;
    }
}

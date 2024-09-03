package com.flower.portfolio.service.implementations;

import com.flower.portfolio.model.Person;
import com.flower.portfolio.repository.PersonRepository;
import com.flower.portfolio.service.interfaces.IPersonService;
import org.springframework.stereotype.Service;

@Service
public class PersonService implements IPersonService {

    private final PersonRepository repo;

    public PersonService(PersonRepository iPersonRepo) {
        repo = iPersonRepo;
    }

    @Override
    public Person get(String lastname) {
        return this.repo.findByLastname(lastname);
    }

    @Override
    public Person post(Person person) {
        return null;
    }

    @Override
    public Person update(Long id, Person person) {
        return null;
    }
}

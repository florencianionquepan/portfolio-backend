package com.flower.portfolio.service.interfaces;

import com.flower.portfolio.dto.PersonDTO;
import com.flower.portfolio.model.Person;

public interface IPersonService {
    PersonDTO get(String lastname);
    PersonDTO post(Person person);
    PersonDTO update(Long id, Person person);
}

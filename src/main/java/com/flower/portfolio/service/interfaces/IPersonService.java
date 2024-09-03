package com.flower.portfolio.service.interfaces;

import com.flower.portfolio.model.Person;

public interface IPersonService {
    Person get(String lastname);
    Person post(Person person);
    Person update(Long id, Person person);
}

package com.flower.portfolio.repository;

import com.flower.portfolio.model.Person;
import org.springframework.data.repository.CrudRepository;

public interface IPersonRepository extends CrudRepository<Person,Long> {

    Person findByLastName(String lastname);
}

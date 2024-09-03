package com.flower.portfolio.repository;

import com.flower.portfolio.model.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person,Long> {

    Person findByLastName(String lastname);
}

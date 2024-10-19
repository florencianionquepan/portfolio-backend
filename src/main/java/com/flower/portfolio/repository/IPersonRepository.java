package com.flower.portfolio.repository;

import com.flower.portfolio.model.Person;
import com.flower.portfolio.model.WebProject;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IPersonRepository extends CrudRepository<Person,Long> {

    Person findByLastName(String lastname);

    @Query("SELECT wp FROM WebProject wp WHERE wp.person.id = :id")
    List<WebProject> findAllByPersonId(Long id);
}

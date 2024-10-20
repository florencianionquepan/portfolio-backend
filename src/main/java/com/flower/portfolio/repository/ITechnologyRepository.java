package com.flower.portfolio.repository;

import com.flower.portfolio.model.Technology;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ITechnologyRepository extends CrudRepository<Technology,Long> {
    List<Technology> findDistinctByProjectsId(Long id);
    Optional<Technology> findByNameAndVersion(String name, String version);

}

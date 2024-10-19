package com.flower.portfolio.repository;

import com.flower.portfolio.model.Technology;
import org.springframework.data.repository.CrudRepository;

public interface ITechnologyRepository extends CrudRepository<Technology,Long> {
}

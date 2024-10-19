package com.flower.portfolio.repository;


import com.flower.portfolio.model.Course;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ICourseRepository extends CrudRepository<Course,Long> {
    List<Course> findByPersonId(Long id);
}

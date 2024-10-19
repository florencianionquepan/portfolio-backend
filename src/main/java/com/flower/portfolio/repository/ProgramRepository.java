package com.flower.portfolio.repository;

import com.flower.portfolio.model.AcademicProgram;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProgramRepository extends CrudRepository<AcademicProgram,Long> {
    List<AcademicProgram> findByPersonId(Long personId);
}

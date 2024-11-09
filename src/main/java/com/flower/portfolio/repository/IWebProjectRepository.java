package com.flower.portfolio.repository;

import com.flower.portfolio.model.WebProject;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IWebProjectRepository extends CrudRepository<WebProject,Long> {
    List<WebProject> findByPersonId(Long personId);
}

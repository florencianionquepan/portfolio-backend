package com.flower.portfolio.service.interfaces;

import com.flower.portfolio.dto.CourseDTO;
import com.flower.portfolio.model.Course;

import java.util.List;

public interface ICourseService {
    List<Course> getCoursesByPerson(Long idPerson);
    CourseDTO post(CourseDTO dto, Long idPerson);
    CourseDTO put(CourseDTO dto, Long idC);
}

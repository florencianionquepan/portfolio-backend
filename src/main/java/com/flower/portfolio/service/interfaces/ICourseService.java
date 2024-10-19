package com.flower.portfolio.service.interfaces;

import com.flower.portfolio.dto.CourseDTO;

import java.util.List;

public interface ICourseService {
    List<CourseDTO> getCoursesByPerson(Long idPerson);
    CourseDTO post(CourseDTO dto, Long idPerson);
    CourseDTO put(CourseDTO dto, Long idC);
}

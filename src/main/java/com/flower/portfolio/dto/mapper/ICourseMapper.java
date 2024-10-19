package com.flower.portfolio.dto.mapper;

import com.flower.portfolio.dto.CourseDTO;
import com.flower.portfolio.model.Course;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel="spring")
public interface ICourseMapper {
    Course mapToEntity(CourseDTO dto);
    CourseDTO mapToDTO(Course entity);
    List<CourseDTO> mapToListDTO(List<Course> courses);

}

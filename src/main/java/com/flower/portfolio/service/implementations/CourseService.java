package com.flower.portfolio.service.implementations;

import com.flower.portfolio.dto.CourseDTO;
import com.flower.portfolio.dto.mapper.ICourseMapper;
import com.flower.portfolio.model.Course;
import com.flower.portfolio.model.Person;
import com.flower.portfolio.repository.ICourseRepository;
import com.flower.portfolio.repository.IPersonRepository;
import com.flower.portfolio.service.interfaces.ICourseService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService implements ICourseService {

    private final ICourseRepository repo;
    private final ICourseMapper mapper;
    private final IPersonRepository personRepo;

    public CourseService(ICourseRepository repo,
                         ICourseMapper mapper,
                         IPersonRepository personRepo) {
        this.repo = repo;
        this.mapper = mapper;
        this.personRepo = personRepo;
    }

    @Override
    public List<CourseDTO> getCoursesByPerson(Long idPerson) {
        List<Course> courses=this.repo.findByPersonId(idPerson);
        return this.mapper.mapToListDTO(courses);
    }

    @Override
    public CourseDTO post(CourseDTO dto, Long idPerson) {
        Optional<Person> oPerson=this.personRepo.findById(idPerson);
        if(oPerson.isEmpty()){
            //...lanzar excepcion
        }
        Course course=this.mapper.mapToEntity(dto);
        course.setPerson(oPerson.get());
        return this.mapper.mapToDTO(this.repo.save(course));
    }

    @Override
    public CourseDTO put(CourseDTO dto, Long idC) {
        Optional<Course> oCourse = this.repo.findById(idC);
        if(oCourse.isEmpty()){
            //...
        }
        Course courseModified = this.mapper.mapToEntity(dto);
        courseModified.setId(idC);
        return this.mapper.mapToDTO(this.repo.save(courseModified));
    }
}

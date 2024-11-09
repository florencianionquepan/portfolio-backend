package com.flower.portfolio.service.implementations;

import com.flower.portfolio.dto.WebProjectDTO;
import com.flower.portfolio.dto.mapper.IProjectMapper;
import com.flower.portfolio.model.Person;
import com.flower.portfolio.model.WebProject;
import com.flower.portfolio.repository.IPersonRepository;
import com.flower.portfolio.repository.IWebProjectRepository;
import com.flower.portfolio.service.interfaces.IWebProjectService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WebProjectService implements IWebProjectService {

    private final IWebProjectRepository repo;
    private final IProjectMapper mapper;
    private final IPersonRepository personRepo;

    public WebProjectService(IWebProjectRepository repo,
                             IProjectMapper mapper,
                             IPersonRepository personRepo) {
        this.repo = repo;
        this.mapper = mapper;
        this.personRepo = personRepo;
    }

    @Override
    public List<WebProjectDTO> projectsByPerson(Long id) {
        List<WebProject> projects=this.repo.findByPersonId(id);
        return this.mapper.mapToListDTOs(projects);
    }

    @Override
    public WebProjectDTO create(WebProjectDTO dto, Long idPerson) {
        Optional<Person> oPerson=this.personRepo.findById(idPerson);
        if(oPerson.isEmpty()){
            //...lanzar excepcion
        }
        WebProject project=this.mapper.mapToEntity(dto);
        project.setPerson(oPerson.get());
        return this.mapper.mapToDTO(project);
    }

    @Override
    public WebProjectDTO update(WebProjectDTO dto, Long id) {
        Optional<WebProject> oProject=this.repo.findById(id);
        if(oProject.isEmpty()){
            //lanzo excepcion
        }
        Person person=oProject.get().getPerson();
        WebProject modified=this.mapper.mapToEntity(dto);
        modified.setPerson(person);
        modified.setId(id);
        return this.mapper.mapToDTO(this.repo.save(modified));
    }
}

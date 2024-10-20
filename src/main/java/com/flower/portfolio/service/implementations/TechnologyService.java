package com.flower.portfolio.service.implementations;

import com.flower.portfolio.dto.TechnologyDTO;
import com.flower.portfolio.dto.mapper.ITechnologyMapper;
import com.flower.portfolio.model.Technology;
import com.flower.portfolio.model.WebProject;
import com.flower.portfolio.repository.IPersonRepository;
import com.flower.portfolio.repository.ITechnologyRepository;
import com.flower.portfolio.service.interfaces.ITechnologyService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TechnologyService implements ITechnologyService {

    private final ITechnologyRepository repo;
    private final ITechnologyMapper mapper;
    private final IPersonRepository personRepo;

    public TechnologyService(ITechnologyRepository repo,
                             ITechnologyMapper mapper,
                             IPersonRepository personRepo) {
        this.repo = repo;
        this.mapper = mapper;
        this.personRepo = personRepo;
    }

    @Override
    public List<TechnologyDTO> technologiesByPerson(Long idPerson) {
        List<WebProject> projects=this.personRepo.findAllByPersonId(idPerson);
        List<Technology> techonologies=projects.stream()
                .map(WebProject::getTechnologies)
                .flatMap(List::stream)
                .distinct()
                .toList();
        return this.mapper.mapToListDtos(techonologies);
    }

    @Override
    public List<TechnologyDTO> techonologiesByProject(Long idProject) {
        List<Technology> technologies=this.repo.findDistinctByProjectsId(idProject);
        return this.mapper.mapToListDtos(technologies);
    }

    @Override
    public TechnologyDTO create(TechnologyDTO dto) {
        Optional<Technology> oTech=this.repo.findByNameAndVersion(dto.getName(),dto.getVersion());
        if(oTech.isPresent()){
            //LANZAR EXCEPCION
        }
        Technology technology=this.mapper.mapToEntity(dto);
        return this.mapper.mapToDto(this.repo.save(technology));
    }

    @Override
    public TechnologyDTO update(TechnologyDTO dto, Long idT) {
        Optional<Technology> oTech=this.repo.findByNameAndVersion(dto.getName(),dto.getVersion());
        if(oTech.isPresent() && Objects.equals(oTech.get().getId(), idT)){
            //LANZAR EXCEPCION
        }
        Optional<Technology> oTechId=this.repo.findById(idT);
        if(oTech.isEmpty()){
            //lanzo otra excepcion
        }
        dto.setId(idT);
        Technology entity=this.mapper.mapToEntity(dto);
        return this.mapper.mapToDto(this.repo.save(entity));
    }
}

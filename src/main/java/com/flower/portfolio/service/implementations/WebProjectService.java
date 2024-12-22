package com.flower.portfolio.service.implementations;

import com.flower.portfolio.dto.WebProjectDTO;
import com.flower.portfolio.dto.mapper.IProjectMapper;
import com.flower.portfolio.external.mapper.ImageUploadedMapper;
import com.flower.portfolio.external.service.CloudinaryService;
import com.flower.portfolio.model.*;
import com.flower.portfolio.repository.*;
import com.flower.portfolio.service.interfaces.IWebProjectService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Service
public class WebProjectService implements IWebProjectService {

    private final IWebProjectRepository repo;
    private final IProjectMapper mapper;
    private final IPersonRepository personRepo;
    private final CloudinaryService cloudService;
    private final IImageRepository imageRepo;
    private final ImageUploadedMapper imageMapper;
    private final ILinkRepository linkRepo;
    private final ITechnologyRepository techRepo;

    public WebProjectService(IWebProjectRepository repo,
                             IProjectMapper mapper,
                             IPersonRepository personRepo,
                             CloudinaryService cloudService,
                             IImageRepository imageRepo,
                             ImageUploadedMapper imageMapper,
                             ILinkRepository linkRepo,
                             ITechnologyRepository techRepo) {
        this.repo = repo;
        this.mapper = mapper;
        this.personRepo = personRepo;
        this.cloudService = cloudService;
        this.imageRepo = imageRepo;
        this.imageMapper = imageMapper;
        this.linkRepo = linkRepo;
        this.techRepo = techRepo;
    }

    @Override
    public List<WebProjectDTO> projectsByPerson(Long id) {
        List<WebProject> projects=this.repo.findByPersonId(id);
        return this.mapper.mapToListDTOs(projects);
    }

    @Override
    @Transactional
    public WebProjectDTO create(WebProjectDTO dto, MultipartFile[] files, Long idPerson) {
        Optional<Person> oPerson=this.personRepo.findById(idPerson);
        if(oPerson.isEmpty()){
            //...lanzar excepcion
        }
        WebProject project=this.mapper.mapToEntity(dto);
        project.setPerson(oPerson.get());
        WebProject created=this.repo.save(project);
        //subir y guardar imagenes
        List<Image> uploaded=this.uploadAndSave(files, created);
        created.setImages(uploaded);
        if(project.getLinks()!=null){
            project.getLinks().forEach(link -> link.setProject(created));
            //en teoria para edicion funciona esto =
            List<Link> links= (List<Link>) this.linkRepo.saveAll(created.getLinks());
            created.setLinks(links);
        }
        if(project.getTechnologies()!=null && !project.getTechnologies().isEmpty()){
            for (Technology tech : project.getTechnologies()) {
                if (!techRepo.existsById(tech.getId())) {
                    throw new IllegalArgumentException("Technology not found with ID: " + tech.getId());
                }
            }
            project.getTechnologies().forEach(tech -> {
                if(tech.getProjects()==null){
                    tech.setProjects(new ArrayList<>());
                }
                if (!tech.getProjects().contains(created)) {
                    tech.getProjects().add(created);
                }
            });
        }
        return this.mapper.mapToDTO(this.repo.save(created));
    }

    private List<Image> uploadAndSave(MultipartFile[] files, WebProject project){
        List<Map<String, Object>> results=this.cloudService.upload(files);
        List<Image> images=this.imageMapper.mapToListImage(results, project);
        return (List<Image>) this.imageRepo.saveAll(images);
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

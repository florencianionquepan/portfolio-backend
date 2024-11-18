package com.flower.portfolio.service.implementations;

import com.flower.portfolio.dto.WebProjectDTO;
import com.flower.portfolio.dto.mapper.IProjectMapper;
import com.flower.portfolio.external.mapper.ImageUploadedMapper;
import com.flower.portfolio.external.service.CloudinaryService;
import com.flower.portfolio.model.Image;
import com.flower.portfolio.model.Link;
import com.flower.portfolio.model.Person;
import com.flower.portfolio.model.WebProject;
import com.flower.portfolio.repository.IImageRepository;
import com.flower.portfolio.repository.ILinkRepository;
import com.flower.portfolio.repository.IPersonRepository;
import com.flower.portfolio.repository.IWebProjectRepository;
import com.flower.portfolio.service.interfaces.IWebProjectService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class WebProjectService implements IWebProjectService {

    private final IWebProjectRepository repo;
    private final IProjectMapper mapper;
    private final IPersonRepository personRepo;
    private final CloudinaryService cloudService;
    private final IImageRepository imageRepo;
    private final ImageUploadedMapper imageMapper;
    private final ILinkRepository linkRepo;

    public WebProjectService(IWebProjectRepository repo,
                             IProjectMapper mapper,
                             IPersonRepository personRepo,
                             CloudinaryService cloudService,
                             IImageRepository imageRepo,
                             ImageUploadedMapper imageMapper,
                             ILinkRepository linkRepo) {
        this.repo = repo;
        this.mapper = mapper;
        this.personRepo = personRepo;
        this.cloudService = cloudService;
        this.imageRepo = imageRepo;
        this.imageMapper = imageMapper;
        this.linkRepo = linkRepo;
    }

    @Override
    public List<WebProjectDTO> projectsByPerson(Long id) {
        List<WebProject> projects=this.repo.findByPersonId(id);
        return this.mapper.mapToListDTOs(projects);
    }

    @Override
    public WebProjectDTO create(WebProjectDTO dto, MultipartFile[] files, Long idPerson) {
        Optional<Person> oPerson=this.personRepo.findById(idPerson);
        if(oPerson.isEmpty()){
            //...lanzar excepcion
        }
        WebProject project=this.mapper.mapToEntity(dto);
        //subir y guardar imagenes
        List<Image> created=this.uploadAndSave(files);
        project.setImages(created);
        //guardar links
        if(project.getLinks()!=null){
            List<Link> links= (List<Link>) this.linkRepo.saveAll(project.getLinks());
            project.setLinks(links);
        }
        project.setPerson(oPerson.get());
        return this.mapper.mapToDTO(this.repo.save(project));
    }

    private List<Image> uploadAndSave(MultipartFile[] files){
        List<Map<String, Object>> results=this.cloudService.upload(files);
        List<Image> images=this.imageMapper.mapToListImage(results);
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

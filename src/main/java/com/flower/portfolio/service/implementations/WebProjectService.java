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
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

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
    @Transactional
    public WebProjectDTO update(WebProjectDTO dto, Long id, MultipartFile[] files) {
        Optional<WebProject> oProject=this.repo.findById(id);
        if(oProject.isEmpty()){
            //lanzo excepcion
        }
        Person person=oProject.get().getPerson();
        WebProject modified=this.mapper.mapToEntity(dto);
        List<Image> totalImages=this.checkPreviewsImages(oProject.get().getImages(), modified);
        if(files!=null && files.length>0){
            List<Image> uploaded=this.uploadAndSave(files,oProject.get());
            totalImages.addAll(uploaded);
        }
        modified.setImages(totalImages);

        // Manejar Links y Technologies usando el método genérico
        List<Technology> updatedTechnologies = checkRemovedItems(
                oProject.get().getTechnologies(), modified.getTechnologies(),
                Technology::getId, tech -> tech.getProjects().remove(oProject.get())
        );
        // Filtra solo las tecnologías que NO están en updatedTechnologies (nuevas)
        List<Technology> newTechnologies = modified.getTechnologies().stream()
                .filter(tech -> updatedTechnologies.stream()
                        .noneMatch(existingTech -> existingTech.getId().equals(tech.getId())))
                .toList();
        // Agrega solo las nuevas tecnologías
        updatedTechnologies.addAll(newTechnologies);
        this.techRepo.saveAll(updatedTechnologies);
        modified.setTechnologies(updatedTechnologies);

        //falta chequear links nombre y url si modificaron
        List<Link> updatedLinks = checkRemovedItems(
                oProject.get().getLinks(), modified.getLinks(),
                Link::getId, link -> link.setProject(null)
        );
        List<Link> updatedNewLinks=this.createOrUpdateItems(updatedLinks, modified.getLinks(), oProject.get());
        this.linkRepo.saveAll(updatedNewLinks);
        modified.setLinks(updatedNewLinks);
        modified.setPerson(person);
        modified.setId(id);
        return this.mapper.mapToDTO(this.repo.save(modified));
    }

    private List<Image> checkPreviewsImages(List<Image> imagesdb,WebProject modified){
        if(imagesdb.size()>modified.getImages().size()){
            // Convert the modified project's image list into a Set of IDs to improve lookup efficiency
            Set<Long> imagesProjectModified= modified.getImages().stream()
                    .map(Image::getId)
                    .collect(Collectors.toSet());
            List<Image> deletedImages= imagesdb.stream()
                    .filter(img->!imagesProjectModified.contains(img.getId()))
                    .toList();

            deletedImages.forEach(d->d.setProject(null));
            this.imageRepo.saveAll(deletedImages);

            // Obtener las imágenes existentes simplemente restando deletedImages de imagesdb
            List<Image> existingImages = new ArrayList<>(imagesdb);
            existingImages.removeAll(deletedImages);

            return existingImages;
        }
        return imagesdb;
    }

    private <T> List<T> checkRemovedItems(List<T> databaseItems, List<T> modifiedItems,
                                          Function<T, Long> getId, Consumer<T> unlinkProject) {
        // Convertir la lista modificada en un Set de IDs para mejorar la búsqueda
        Set<Long> modifiedItemIds = modifiedItems.stream()
                .map(getId)
                .collect(Collectors.toSet());

        // Filtrar elementos eliminados
        List<T> deletedItems = databaseItems.stream()
                .filter(item -> !modifiedItemIds.contains(getId.apply(item)))
                .toList();

        // Desvincular los elementos eliminados
        deletedItems.forEach(unlinkProject);

        return databaseItems.stream()
                .filter(item -> modifiedItemIds.contains(getId.apply(item)))
                .collect(Collectors.toCollection(ArrayList::new)); //para devolver lista mutable
    }

    private List<Link> createOrUpdateItems(List<Link> updatedLinks, List<Link> modifieds, WebProject project) {
        // Crear un Map de los Links en updatedLinks por ID para facilitar la búsqueda
        Map<Long, Link> linkMap = updatedLinks.stream()
                .collect(Collectors.toMap(Link::getId, link -> link));

        // Actualizar los Links o agregar los nuevos desde modified.getLinks()
        modifieds.forEach(modifiedLink -> {
            if (modifiedLink.getId() == null) {
                modifiedLink.setProject(project);
                linkMap.put(System.nanoTime(), modifiedLink); // para que se creen todos los nuevos
            } else {
                linkMap.put(modifiedLink.getId(), modifiedLink); //para actualizar si tenia el id
            }
        });
        return new ArrayList<>(linkMap.values());
    }
}

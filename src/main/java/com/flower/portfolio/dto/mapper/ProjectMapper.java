package com.flower.portfolio.dto.mapper;

import com.flower.portfolio.dto.WebProjectDTO;
import com.flower.portfolio.model.Status;
import com.flower.portfolio.model.WebProject;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProjectMapper implements IProjectMapper{

    private final IImageMapper imageMapper;
    private final ILinkMapper linkMapper;
    private final ITechnologyMapper techMapper;

    public ProjectMapper(IImageMapper imageMapper,
                         ILinkMapper linkMapper,
                         ITechnologyMapper techMapper) {
        this.imageMapper = imageMapper;
        this.linkMapper = linkMapper;
        this.techMapper = techMapper;
    }

    @Override
    public WebProjectDTO mapToDTO(WebProject entity) {
        WebProjectDTO dto=new WebProjectDTO();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setEndDate(entity.getEndDate());
        dto.setStatus(entity.getStatus().name());
        if(entity.getImages()!=null && !entity.getImages().isEmpty()){
            dto.setImages(this.imageMapper.mapToListDTO(entity.getImages()));
        }
        if(entity.getLinks()!=null && !entity.getLinks().isEmpty()){
            dto.setLinks(this.linkMapper.mapToListDTO(entity.getLinks()));
        }
        if(entity.getTechnologies()!=null && !entity.getTechnologies().isEmpty()){
            dto.setTechnologies(this.techMapper.mapToListDtos(entity.getTechnologies()));
        }
        return dto;
    }

    @Override
    public WebProject mapToEntity(WebProjectDTO dto) {
        WebProject entity=new WebProject();
        entity.setId(dto.getId());
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setStatus(Status.valueOf(dto.getStatus()));
        if(dto.getImages()!=null && !dto.getImages().isEmpty()){
            entity.setImages(this.imageMapper.mapToListEntities(dto.getImages()));
        }
        if(dto.getLinks()!=null && !dto.getLinks().isEmpty()){
            entity.setLinks(this.linkMapper.mapToListEntities(dto.getLinks()));
        }
        if(dto.getTechnologies()!=null && !dto.getTechnologies().isEmpty()){
            entity.setTechnologies(this.techMapper.mapToListEntities(dto.getTechnologies()));
        }
        if(dto.getEndDate()!=null){
            entity.setEndDate(dto.getEndDate());
        }
        return entity;
    }

    @Override
    public List<WebProjectDTO> mapToListDTOs(List<WebProject> entities) {
        return entities.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }
}

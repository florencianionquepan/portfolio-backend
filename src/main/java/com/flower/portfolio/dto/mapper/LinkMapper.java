package com.flower.portfolio.dto.mapper;

import com.flower.portfolio.dto.LinkDTO;
import com.flower.portfolio.model.Link;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class LinkMapper implements ILinkMapper{

    @Override
    public Link mapToEntity(LinkDTO dto) {
        Link entity=new Link();
        entity.setId(dto.getId());
        entity.setTitle(dto.getTitle());
        entity.setUrl(dto.getUrl());
        return entity;
    }

    @Override
    public LinkDTO mapToDTO(Link entity) {
        LinkDTO dto=new LinkDTO();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setUrl(entity.getUrl());
        return dto;
    }

    @Override
    public List<LinkDTO> mapToListDTO(List<Link> entities) {
        return entities.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }
}

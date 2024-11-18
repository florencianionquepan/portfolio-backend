package com.flower.portfolio.dto.mapper;

import com.flower.portfolio.dto.LinkDTO;
import com.flower.portfolio.model.Link;

import java.util.List;

public interface ILinkMapper {
    Link mapToEntity(LinkDTO dto);
    LinkDTO mapToDTO(Link entity);
    List<LinkDTO> mapToListDTO(List<Link> entities);
}

package com.flower.portfolio.dto.mapper;

import com.flower.portfolio.dto.WebProjectDTO;
import com.flower.portfolio.model.WebProject;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IProjectMapper {
    WebProjectDTO mapToDTO(WebProject entity);
    WebProject mapToEntity(WebProjectDTO dto);
    List<WebProjectDTO> mapToListDTOs(List<WebProject> entities);
}

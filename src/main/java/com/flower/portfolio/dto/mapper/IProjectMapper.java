package com.flower.portfolio.dto.mapper;

import com.flower.portfolio.dto.WebProjectDTO;
import com.flower.portfolio.model.WebProject;

import java.util.List;

public interface IProjectMapper {
    WebProjectDTO mapToDTO(WebProject entity);
    WebProject mapToEntity(WebProjectDTO dto);
    List<WebProjectDTO> mapToListDTOs(List<WebProject> entities);
}

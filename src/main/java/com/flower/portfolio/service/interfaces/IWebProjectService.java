package com.flower.portfolio.service.interfaces;

import com.flower.portfolio.dto.WebProjectDTO;

import java.util.List;

public interface IWebProjectService {
    List<WebProjectDTO> projectsByPerson(Long id);
    WebProjectDTO create(WebProjectDTO dto, Long idPerson);
    WebProjectDTO update(WebProjectDTO dto, Long id);
}

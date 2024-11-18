package com.flower.portfolio.service.interfaces;

import com.flower.portfolio.dto.TechnologyDTO;

import java.util.List;

public interface ITechnologyService {
    List<TechnologyDTO> getAll();
    List<TechnologyDTO> technologiesByPerson(Long idPerson);
    List<TechnologyDTO> techonologiesByProject(Long idProject);
    TechnologyDTO create(TechnologyDTO dto);
    TechnologyDTO update(TechnologyDTO dto, Long idT);
}

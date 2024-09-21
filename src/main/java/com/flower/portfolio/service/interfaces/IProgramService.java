package com.flower.portfolio.service.interfaces;

import com.flower.portfolio.dto.ProgramDTO;

import java.util.List;

public interface IProgramService {
    List<ProgramDTO> programsByPerson(Long idPerson);
    ProgramDTO post(ProgramDTO dto, Long idPerson);
    ProgramDTO update(ProgramDTO dto, Long id, Long idPerson);
}

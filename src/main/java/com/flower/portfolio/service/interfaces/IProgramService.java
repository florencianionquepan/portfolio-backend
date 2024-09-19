package com.flower.portfolio.service.interfaces;

import com.flower.portfolio.dto.ProgramDTO;

public interface IProgramService {
    ProgramDTO getBy(String lastname);
    ProgramDTO post(ProgramDTO dto);
    ProgramDTO update(ProgramDTO dto);
}

package com.flower.portfolio.dto;

import lombok.Data;

import java.util.List;

@Data
public class PersonWithDetailsDTO {
    private PersonDTO person;
    private List<ProgramDTO> programs;
    private List<WebProjectDTO> projects;
}

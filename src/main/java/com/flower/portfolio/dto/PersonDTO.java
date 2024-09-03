package com.flower.portfolio.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonDTO {
    private String name;
    private String lastName;
    private String tel;
    private LocalDate dateOfBirth;
    private String occupation;
    private String presentation;

}

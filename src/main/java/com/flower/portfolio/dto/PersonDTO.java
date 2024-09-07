package com.flower.portfolio.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonDTO {
    private Long id;
    @NotNull
    @NotEmpty
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Name must contain only letters.")
    private String name;
    @NotNull
    @NotEmpty
    @Pattern(regexp = "^[a-zA-ZñÑ]+$", message = "Last name must contain only letters.")
    private String lastName;
    @Pattern(regexp = "^\\+?[0-9]+$", message = "Phone number must contain only digits and optionally start with a '+'.")
    private String tel;
    @Email
    private String email;
    private LocalDate dateOfBirth;
    @Pattern(regexp = "^[a-zA-Z.()!]+$", message = "Occupation must contain only letters, periods, exclamation marks, and parentheses.")
    private String occupation;
    @Pattern(regexp = "^[a-zA-Z.()!]+$", message = "Presentation must contain only letters, periods, exclamation marks, and parentheses.")
    private String presentation;

}

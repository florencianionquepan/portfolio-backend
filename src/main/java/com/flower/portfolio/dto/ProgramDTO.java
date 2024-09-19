package com.flower.portfolio.dto;


import com.flower.portfolio.dto.validator.ValueOfEnum;
import com.flower.portfolio.model.Status;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;


import java.time.LocalDate;

@Data
public class ProgramDTO {

    private Long id;
    @NotNull
    @NotEmpty
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Name must contain only letters.")
    private String name;
    @NotNull
    @NotEmpty
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Institution must contain only letters.")
    private String institution;
    private String degreeType;
    private LocalDate startDate;
    private LocalDate endDate;
    @ValueOfEnum(enumClass=Status.class)
    private Status status;

}

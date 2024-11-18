package com.flower.portfolio.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class TechnologyDTO {
    private Long id;
    @NotNull
    @NotEmpty
    @Pattern(regexp = "^[a-zA-Z.\\s]+$", message = "Name must contain only letters and periods.")
    private String name;
    private String logoUrl;
    @Pattern(regexp = "^[0-9.]+$", message = "Version must contain only numbers and periods.")
    private String version;
}

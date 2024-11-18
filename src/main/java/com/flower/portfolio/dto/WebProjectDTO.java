package com.flower.portfolio.dto;

import com.flower.portfolio.dto.validator.ValueOfEnum;
import com.flower.portfolio.model.Image;
import com.flower.portfolio.model.Link;
import com.flower.portfolio.model.Status;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class WebProjectDTO {
    private Long id;
    @NotNull
    @NotEmpty
    @Pattern(regexp = "^[a-zA-Z\\sÀ-ÿ]+$", message = "Title must contain only letters.")
    private String title;
    private String description;
    private LocalDate endDate;
    private Status status;
    private List<ImageDTO> images;
    private List<LinkDTO> links;
}

package com.flower.portfolio.dto;

import com.flower.portfolio.dto.validator.PastDateRange;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CourseDTO {
    private Long id;
    @NotNull
    @NotEmpty
    @Pattern(regexp = "^[a-zA-Z\\sÀ-ÿ]+$", message = "Title must contain only letters.")
    private String title;
    @Pattern(regexp = "^[a-zA-ZÀ-ÿ\\s()]+$", message = "Instructor must contain only letters, spaces and parentheses")
    private String instructor;
    @Pattern(regexp = "^[a-zA-Z\\sÀ-ÿ0-9]+$", message = "Duration must contain only letters and numbers")
    private String duration;
    @Pattern(regexp = "^[a-zA-ZÀ-ÿ\\s()\\-:.,]+$", message = "Description must contain only letters, spaces, parentheses, hyphens, colons, commas, and periods.")
    private String description;
    @PastDateRange
    private LocalDate startDate;
}

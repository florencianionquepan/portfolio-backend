package com.flower.portfolio.dto.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class DateRangeValidator implements ConstraintValidator<PastDateRange, LocalDate> {

    private int maxYearsAgo;

    @Override
    public void initialize(PastDateRange constraintAnnotation) {
        this.maxYearsAgo = constraintAnnotation.maxYearsAgo();
    }

    @Override
    public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }

        LocalDate now = LocalDate.now();
        LocalDate earliestAllowedDate = now.minusYears(maxYearsAgo);

        return !value.isAfter(now) && !value.isBefore(earliestAllowedDate);
    }
}

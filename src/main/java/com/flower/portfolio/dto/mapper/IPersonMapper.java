package com.flower.portfolio.dto.mapper;

import com.flower.portfolio.dto.PersonDTO;
import com.flower.portfolio.model.Person;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IPersonMapper {
    PersonDTO mapToDto(Person person);
}
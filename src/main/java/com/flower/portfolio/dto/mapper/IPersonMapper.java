package com.flower.portfolio.dto.mapper;

import com.flower.portfolio.dto.ContactInfoDTO;
import com.flower.portfolio.dto.PersonDTO;
import com.flower.portfolio.model.Person;


public interface IPersonMapper {
    Person mapToEntity(PersonDTO dto);
    PersonDTO mapToDto(Person person);
    ContactInfoDTO mapToSensitiveData(Person person);
}

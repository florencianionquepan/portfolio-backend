package com.flower.portfolio.service.interfaces;

import com.flower.portfolio.dto.ContactInfoDTO;
import com.flower.portfolio.dto.PersonDTO;
import com.flower.portfolio.dto.PersonWithDetailsDTO;
import com.flower.portfolio.model.Person;

public interface IPersonService {
    PersonWithDetailsDTO getAllData(String lastname);
    ContactInfoDTO getSensitiveData(String lastname);
    PersonDTO get(String lastname);
    PersonDTO post(PersonDTO person);
    PersonDTO update(Long id, PersonDTO person);
}

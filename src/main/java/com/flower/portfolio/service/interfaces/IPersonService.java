package com.flower.portfolio.service.interfaces;

import com.flower.portfolio.dto.ContactInfoDTO;
import com.flower.portfolio.dto.PersonDTO;
import com.flower.portfolio.dto.PersonWithDetailsDTO;
import com.flower.portfolio.model.Person;

public interface IPersonService {
    PersonWithDetailsDTO getAllData(Long idPerson);
    ContactInfoDTO getSensitiveData(Long idPerson);
    PersonDTO get(Long idPerson);
    PersonDTO post(PersonDTO person);
    PersonDTO update(Long id, PersonDTO person);
}

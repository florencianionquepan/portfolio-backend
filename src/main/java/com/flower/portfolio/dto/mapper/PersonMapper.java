package com.flower.portfolio.dto.mapper;

import com.flower.portfolio.dto.ContactInfoDTO;
import com.flower.portfolio.dto.PersonDTO;
import com.flower.portfolio.model.Person;
import org.springframework.stereotype.Component;

@Component
public class PersonMapper implements IPersonMapper{
    @Override
    public Person mapToEntity(PersonDTO dto) {
        Person entity=new Person();
        entity.setName(dto.getName());
        entity.setLastName(dto.getLastName());
        entity.setDateOfBirth(dto.getDateOfBirth());
        entity.setTel(dto.getTel());
        entity.setEmail(dto.getEmail());
        entity.setOccupation(dto.getOccupation());
        if(dto.getPresentation()!=null){
            entity.setPresentation(dto.getPresentation());
        }
        return entity;
    }

    @Override
    public PersonDTO mapToDto(Person person) {
        PersonDTO dto=new PersonDTO();
        dto.setId(person.getId());
        dto.setName(person.getName());
        dto.setLastName(person.getLastName());
        dto.setOccupation(person.getOccupation());
        if(person.getPresentation()!=null){
            dto.setPresentation(person.getPresentation());
        }
        return dto;
    }

    @Override
    public ContactInfoDTO mapToSensitiveData(Person person) {
        ContactInfoDTO dto=new ContactInfoDTO();
        dto.setTel(person.getTel());
        dto.setEmail(person.getEmail());
        dto.setDateOfBirth(person.getDateOfBirth());
        return dto;
    }
}

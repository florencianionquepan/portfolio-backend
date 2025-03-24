package com.flower.portfolio.controller;

import com.flower.portfolio.dto.ContactInfoDTO;
import com.flower.portfolio.dto.PersonDTO;
import com.flower.portfolio.service.interfaces.IPersonService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/person")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class PersonController {

    private final IPersonService personService;

    public PersonController(IPersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public ResponseEntity<?> createProfile(@Valid PersonDTO dto){
        PersonDTO created=this.personService.post(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping("/{lastname}")
    public ResponseEntity<?> getSensitiveData(@PathVariable String lastname){
        ContactInfoDTO contact=this.personService.getSensitiveData(lastname);
        return ResponseEntity.ok(contact);
    }
}

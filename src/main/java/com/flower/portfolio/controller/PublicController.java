package com.flower.portfolio.controller;

import com.flower.portfolio.dto.PersonWithDetailsDTO;
import com.flower.portfolio.service.interfaces.IPersonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class PublicController {

    private final IPersonService personService;

    public PublicController(IPersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/profile/{lastname}")
    public ResponseEntity<?> getPersonDetails(@PathVariable String lastname){
        PersonWithDetailsDTO dto=this.personService.getAllData(lastname);
        return ResponseEntity.ok(dto);
    }
}

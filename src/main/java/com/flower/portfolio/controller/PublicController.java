package com.flower.portfolio.controller;

import com.flower.portfolio.dto.PersonDTO;
import com.flower.portfolio.model.Person;
import com.flower.portfolio.service.interfaces.IPersonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class PublicController {

    private final IPersonService personService;
    public Map<String,Object> mensajeBody= new HashMap<>();

    public PublicController(IPersonService personService) {
        this.personService = personService;
    }

    private ResponseEntity<?> successResponse(Object data){
        mensajeBody.put("success",Boolean.TRUE);
        mensajeBody.put("data",data);
        return ResponseEntity.ok(mensajeBody);
    }

    @GetMapping("/profile/{lastname}")
    public ResponseEntity<?> getPerson(@PathVariable String lastname){
        PersonDTO dto=this.personService.get(lastname);
        return this.successResponse(dto);
    }

}

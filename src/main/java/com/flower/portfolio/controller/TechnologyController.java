package com.flower.portfolio.controller;

import com.flower.portfolio.dto.TechnologyDTO;
import com.flower.portfolio.service.interfaces.ITechnologyService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/technologies")
public class TechnologyController {

    private final ITechnologyService service;
    public Map<String,Object> bodyMessage= new HashMap<>();

    public TechnologyController(ITechnologyService service) {
        this.service = service;
    }

    private ResponseEntity<?> successResponse(Object data){
        bodyMessage.put("success",Boolean.TRUE);
        bodyMessage.put("data",data);
        return ResponseEntity.ok(bodyMessage);
    }

    @PostMapping
    public ResponseEntity<?> createProgram(@RequestBody @Valid TechnologyDTO dto){
        TechnologyDTO created=this.service.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{idT}")
    public ResponseEntity<?> updateProgram(@PathVariable Long idT,
                                           @RequestBody @Valid TechnologyDTO dto){
        TechnologyDTO modified=this.service.update(dto,idT);
        return this.successResponse(modified);
    }
}

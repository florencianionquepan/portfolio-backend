package com.flower.portfolio.controller;

import com.flower.portfolio.dto.ProgramDTO;
import com.flower.portfolio.service.interfaces.IProgramService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/programs")
public class ProgramController {
    private final IProgramService service;
    public Map<String,Object> bodyMessage= new HashMap<>();

    public ProgramController(IProgramService service) {
        this.service = service;
    }

    private ResponseEntity<?> successResponse(Object data){
        bodyMessage.put("success",Boolean.TRUE);
        bodyMessage.put("data",data);
        return ResponseEntity.ok(bodyMessage);
    }

    @PostMapping("/person/{id}")
    public ResponseEntity<?> createProgram(@PathVariable Long id, @RequestBody @Valid ProgramDTO dto){
        ProgramDTO created=this.service.post(dto, id);
        return ResponseEntity.status(HttpStatus.CREATED).body(this.successResponse(created));
    }

    @PutMapping("/{idP}")
    public ResponseEntity<?> updateProgram(@PathVariable Long idP, @RequestBody @Valid ProgramDTO dto){
        ProgramDTO modified=this.service.update(dto,idP);
        return this.successResponse(modified);
    }


}

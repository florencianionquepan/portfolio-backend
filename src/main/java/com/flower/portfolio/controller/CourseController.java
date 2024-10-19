package com.flower.portfolio.controller;

import com.flower.portfolio.dto.CourseDTO;
import com.flower.portfolio.dto.ProgramDTO;
import com.flower.portfolio.service.interfaces.ICourseService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private final ICourseService service;
    public Map<String,Object> bodyMessage= new HashMap<>();

    public CourseController(ICourseService service) {
        this.service = service;
    }

    private ResponseEntity<?> successResponse(Object data){
        bodyMessage.put("success",Boolean.TRUE);
        bodyMessage.put("data",data);
        return ResponseEntity.ok(bodyMessage);
    }


    @PostMapping("/person/{id}")
    public ResponseEntity<?> createProgram(@PathVariable Long id, @RequestBody @Valid CourseDTO dto){
        CourseDTO created=this.service.post(dto, id);
        return ResponseEntity.status(HttpStatus.CREATED).body(this.successResponse(created));
    }

    @PutMapping("/{idP}")
    public ResponseEntity<?> updateProgram(@PathVariable Long idP, @RequestBody @Valid CourseDTO dto){
        CourseDTO modified=this.service.put(dto,idP);
        return this.successResponse(modified);
    }



}

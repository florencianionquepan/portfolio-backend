package com.flower.portfolio.controller;

import com.flower.portfolio.dto.TechnologyDTO;
import com.flower.portfolio.service.interfaces.ITechnologyService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/technologies")
public class TechnologyController {

    private final ITechnologyService service;

    public TechnologyController(ITechnologyService service) {
        this.service = service;
    }

    @GetMapping()
    public ResponseEntity<?> getAll(){
        List<TechnologyDTO> technologies=this.service.getAll();
        return ResponseEntity.ok(technologies);
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> createTechnology(@RequestBody @Valid TechnologyDTO dto){
        TechnologyDTO created=this.service.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{idT}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> updateTechnology(@PathVariable Long idT,
                                           @RequestBody @Valid TechnologyDTO dto){
        TechnologyDTO modified=this.service.update(dto,idT);
        return ResponseEntity.ok(modified);
    }
}

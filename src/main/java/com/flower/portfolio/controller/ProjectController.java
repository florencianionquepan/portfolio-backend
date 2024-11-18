package com.flower.portfolio.controller;

import com.flower.portfolio.dto.WebProjectDTO;
import com.flower.portfolio.service.interfaces.IWebProjectService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    private final IWebProjectService service;

    public ProjectController(IWebProjectService service) {
        this.service = service;
    }

    private ResponseEntity<?> validarFiles(MultipartFile[] multipartFiles){
        if (multipartFiles == null || multipartFiles.length == 0) {
            //return this.notSuccessResponse("No se proporciono ninguna imagen del gatito",0);
        }else{
            for (MultipartFile multipartFile : multipartFiles){
                BufferedImage bi= null;
                try {
                    bi = ImageIO.read(multipartFile.getInputStream());
                } catch (IOException e) {
                    return ResponseEntity.badRequest().body(e.getMessage());
                }
                if(bi==null){
                    return ResponseEntity.badRequest().body("Alguna imagen no es válida");
                }
            }
        }
        return ResponseEntity.ok("ok");
    }

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE,MediaType.APPLICATION_OCTET_STREAM_VALUE})
    public ResponseEntity<?> createProject(@RequestPart("project") @Valid WebProjectDTO dto,
                                           @RequestParam Long idPerson,
                                           @RequestParam(required = false) MultipartFile[] files){
        ResponseEntity<?> response=this.validarFiles(files);
        if(response.getStatusCode()!=HttpStatus.OK){
            return response;
        }
        WebProjectDTO dtoCreated=this.service.create(dto, files, idPerson);
        return ResponseEntity.status(HttpStatus.CREATED).body(dtoCreated);
    }

}

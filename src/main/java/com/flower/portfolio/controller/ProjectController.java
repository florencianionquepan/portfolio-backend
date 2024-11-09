package com.flower.portfolio.controller;

import com.flower.portfolio.service.interfaces.IWebProjectService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    private final IWebProjectService service;

    public ProjectController(IWebProjectService service) {
        this.service = service;
    }
}

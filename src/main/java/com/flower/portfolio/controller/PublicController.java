package com.flower.portfolio.controller;

import com.flower.portfolio.dto.ContactFormDTO;
import com.flower.portfolio.dto.PersonWithDetailsDTO;
import com.flower.portfolio.service.interfaces.IEmailService;
import com.flower.portfolio.service.interfaces.IPersonService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {

    private final IPersonService personService;
    private final IEmailService emailService;

    public PublicController(IPersonService personService,
                            IEmailService emailService) {
        this.personService = personService;
        this.emailService = emailService;
    }

    @GetMapping("/profile/{lastname}")
    public ResponseEntity<?> getPersonDetails(@PathVariable String lastname){
        PersonWithDetailsDTO dto=this.personService.getAllData(lastname);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/contact")
    public ResponseEntity<?> sendContactForm(@RequestBody ContactFormDTO contact, HttpServletRequest httpRequest){
        String clientIp = httpRequest.getRemoteAddr();
        Boolean sent=this.emailService.sendMessage(contact, clientIp);
        return sent
                ? ResponseEntity.ok("Message sent successfully")
                : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to send message");
    }
}

package com.flower.portfolio.controller;

import com.flower.portfolio.dto.ContactFormDTO;
import com.flower.portfolio.dto.PersonWithDetailsDTO;
import com.flower.portfolio.service.interfaces.IEmailService;
import com.flower.portfolio.service.interfaces.IPersonService;
import com.flower.portfolio.service.interfaces.IRecordDownloadService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/public")
public class PublicController {

    private final IPersonService personService;
    private final IEmailService emailService;
    private final IRecordDownloadService downloadService;

    @Value("${resume.path}")
    private String RESUME_PATH;

    public PublicController(IPersonService personService,
                            IEmailService emailService,
                            IRecordDownloadService downloadService) {
        this.personService = personService;
        this.emailService = emailService;
        this.downloadService = downloadService;
    }

    @GetMapping("/profile/{lastname}")
    public ResponseEntity<?> getPersonDetails(@PathVariable String lastname){
        PersonWithDetailsDTO dto=this.personService.getAllData(lastname);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/contact")
    public ResponseEntity<?> sendContactForm(@RequestBody ContactFormDTO contact, HttpServletRequest httpRequest){
        String clientIp = this.getClientIp(httpRequest);
        Boolean sent=this.emailService.sendMessage(contact, clientIp);
        return sent
                ? ResponseEntity.ok("Message sent successfully")
                : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to send message");
    }

    @GetMapping("/resume")
    public ResponseEntity<?> getResume(HttpServletRequest request){
        String clientIp = this.getClientIp(request);
        String userAgent= request.getHeader("User-Agent");
        try {
            Path path = Paths.get(RESUME_PATH);
            Resource resource = new UrlResource(path.toUri());
            if (!resource.exists() || !resource.isReadable()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            this.downloadService.create("OK",userAgent,clientIp);
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_PDF)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=resume.pdf")
                    .body(resource);
        } catch (Exception e) {
            //logger.error("Error al descargar el CV: {}", e.getMessage());
            this.downloadService.create(e.getMessage(),userAgent,clientIp);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    private String getClientIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr(); // Si no hay X-Forwarded-For, usar la IP directa
        } else {
            ip = ip.split(",")[0].trim(); // Tomar la primera IP en caso de múltiples proxies
        }
        return ip;
    }
}

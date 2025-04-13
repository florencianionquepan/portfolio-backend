package com.flower.portfolio.service.implementations;

import com.flower.portfolio.dto.ContactFormDTO;
import com.flower.portfolio.service.interfaces.IEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService implements IEmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value(("${spring.mail.username}"))
    private String emailDestinatary;

    @Override
    public Boolean sendMessage(ContactFormDTO contactForm, String ip) {
        try{
            SimpleMailMessage email = new SimpleMailMessage();
            email.setTo(emailDestinatary);
            email.setSubject(contactForm.getSubject());
            email.setText("Mensaje de: " + contactForm.getEmail() + "\n\n" + contactForm.getDescription() + "\n\nIP del remitente: " + ip);
            email.setFrom(contactForm.getEmail());

            mailSender.send(email);
            return true;
        } catch (MailException e) {
            System.err.println("There was an Error sending the email: " + e.getMessage());
            return false;
        }
    }
}

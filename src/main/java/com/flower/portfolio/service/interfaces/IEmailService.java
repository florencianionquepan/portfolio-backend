package com.flower.portfolio.service.interfaces;

import com.flower.portfolio.dto.ContactFormDTO;
import jakarta.servlet.http.HttpServletRequest;

public interface IEmailService {
    Boolean sendMessage(ContactFormDTO contactForm, String ip);
}

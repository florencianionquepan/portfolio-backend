package com.flower.portfolio.auth.controller;

import com.flower.portfolio.auth.dto.UserDTO;
import com.flower.portfolio.service.interfaces.IUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    public Map<String,Object> mensajeBody= new HashMap<>();
    public final IUserService service;

    public AuthController(IUserService service) {
        this.service = service;
    }

    private ResponseEntity<?> successResponse(Object data){
        mensajeBody.put("success",Boolean.TRUE);
        mensajeBody.put("data",data);
        return ResponseEntity.ok(mensajeBody);
    }

    @GetMapping
    private ResponseEntity<?> getUserDetails(Authentication authentication){
        UserDTO dto=new UserDTO();
        OAuth2AuthenticationToken oAuth2AuthenticationToken = (OAuth2AuthenticationToken) authentication;
        if("github".equals(oAuth2AuthenticationToken.getAuthorizedClientRegistrationId())){
            DefaultOAuth2User principal= (DefaultOAuth2User) authentication.getPrincipal();
            Map<String,Object> attributes=principal.getAttributes();
            String name = getAttributeValue(attributes, "name");
            String email = getAttributeValue(attributes, "email");
            String avatarUrl = getAttributeValue(attributes, "avatar_url");
            String username = getAttributeValue(attributes,"login");
            dto.setEmail(email);
            dto.setName(name);
            dto.setImageURL(avatarUrl);
            dto.setUsername(username);
            this.service.createOrUpdateUser(dto,"github");
        }
        return this.successResponse(dto);
    }

    private String getAttributeValue(Map<String, Object> attributes, String key) {
        return Optional.ofNullable(attributes.get(key))
                .map(Object::toString)
                .orElse("");
    }
}

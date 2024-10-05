package com.flower.portfolio.controller;

import com.flower.portfolio.auth.dto.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    public Map<String,Object> mensajeBody= new HashMap<>();

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
            Object emailObject = attributes.get("email");
            String email = (emailObject != null) ? emailObject.toString() : "";

            Object avatarUrlObject = attributes.get("avatar_url");
            String avatarUrl = (avatarUrlObject != null) ? avatarUrlObject.toString() : "";

            Object nameObject = attributes.get("name");
            String name = (nameObject != null) ? nameObject.toString() : "";
            dto.setEmail(email);
            dto.setName(name);
            dto.setImageURL(avatarUrl);
        }
        System.out.println(dto);
        return this.successResponse(dto);
    }
}

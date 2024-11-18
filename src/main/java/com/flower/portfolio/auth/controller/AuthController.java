package com.flower.portfolio.auth.controller;

import com.flower.portfolio.auth.dto.UserDTO;
import com.flower.portfolio.auth.dto.mapper.IUserMapper;
import com.flower.portfolio.auth.service.IUserService;
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
    public final IUserService service;
    public final IUserMapper userMapper;

    public AuthController(IUserService service,
                          IUserMapper userMapper) {
        this.service = service;
        this.userMapper = userMapper;
    }

    @GetMapping
    private ResponseEntity<?> getUserDetails(Authentication authentication){
        OAuth2AuthenticationToken oAuth2AuthenticationToken = (OAuth2AuthenticationToken) authentication;
        String provider=oAuth2AuthenticationToken.getAuthorizedClientRegistrationId();
        DefaultOAuth2User principal= (DefaultOAuth2User) authentication.getPrincipal();
        UserDTO dto=this.userMapper.mapDefaultOAuth2UserToDto(principal,provider);
        UserDTO created=this.service.createOrUpdateUser(dto,provider);
        return ResponseEntity.ok(created);
    }

}

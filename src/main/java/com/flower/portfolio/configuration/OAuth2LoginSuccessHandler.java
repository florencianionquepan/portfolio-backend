package com.flower.portfolio.configuration;

import com.flower.portfolio.auth.controller.AuthController;
import com.flower.portfolio.auth.dto.UserDTO;
import com.flower.portfolio.auth.dto.mapper.IUserMapper;
import com.flower.portfolio.auth.service.IUserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class OAuth2LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Value("${frontend.url}")
    public String frontUrl;

    public final IUserService service;
    public final IUserMapper userMapper;

    private Logger logger= LoggerFactory.getLogger(OAuth2LoginSuccessHandler.class);

    public OAuth2LoginSuccessHandler(IUserService service,
                                     IUserMapper userMapper) {
        this.service = service;
        this.userMapper = userMapper;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        OAuth2AuthenticationToken oAuth2AuthenticationToken = (OAuth2AuthenticationToken) authentication;
        String provider=oAuth2AuthenticationToken.getAuthorizedClientRegistrationId();
        DefaultOAuth2User principal= (DefaultOAuth2User) authentication.getPrincipal();
        UserDTO dto=this.userMapper.mapDefaultOAuth2UserToDto(principal,provider);
        UserDTO created=this.service.createOrUpdateUser(dto,provider);
        List<String> roles = created.getRole();
        List<GrantedAuthority> authorities = roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        String nameAttributeKey = (provider.equals("github"))?"id":"sub";
        DefaultOAuth2User user = new DefaultOAuth2User(authorities, principal.getAttributes(), nameAttributeKey);
        Authentication securityAuth = new OAuth2AuthenticationToken(user,
                authorities, oAuth2AuthenticationToken.getAuthorizedClientRegistrationId());
        SecurityContextHolder.getContext().setAuthentication(securityAuth);
        this.setAlwaysUseDefaultTargetUrl(true);
        this.setDefaultTargetUrl(frontUrl);
        logger.info("LoginSuccesHandler here!");
        super.onAuthenticationSuccess(request, response, authentication);
    }
}

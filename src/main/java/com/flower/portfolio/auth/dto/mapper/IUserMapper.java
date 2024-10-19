package com.flower.portfolio.auth.dto.mapper;

import com.flower.portfolio.auth.dto.UserDTO;
import com.flower.portfolio.model.User;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;


public interface IUserMapper {
    User mapToEntity(UserDTO dto, String provider);
    UserDTO mapDefaultOAuth2UserToDto(DefaultOAuth2User auth2User, String provider);
    UserDTO mapEntityToDTO(User user);
}

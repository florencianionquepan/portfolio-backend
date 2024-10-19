package com.flower.portfolio.auth.dto.mapper;

import com.flower.portfolio.auth.dto.UserDTO;
import com.flower.portfolio.model.Role;
import com.flower.portfolio.model.User;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class UserMapper implements IUserMapper{

    @Override
    public User mapToEntity(UserDTO dto, String provider) {
        User user=new User();
        LocalDate lastLogin=LocalDate.now();
        String name="";
        String lastname="";
        if(dto.getName()!=null) {
            String fullname = dto.getName();
            String[] parts = fullname.split(" ");
            name = parts[0];
            lastname = parts.length > 1 ? parts[1] : "";
        }
        user.setName(name);
        user.setLastname(lastname);
        if(dto.getEmail()!=null){
            user.setEmail(dto.getEmail());
        }
        if(dto.getUsername()!=null){
            user.setUsername(dto.getUsername());
        }
        if(dto.getImageURL()!=null){
            user.setImageURL(dto.getImageURL());
        }
        user.setProvider(provider);
        user.setLastLogin(lastLogin);
        return user;
    }

    @Override
    public UserDTO mapDefaultOAuth2UserToDto(DefaultOAuth2User auth2User, String provider) {
        UserDTO dto=new UserDTO();
        Map<String,Object> attributes=auth2User.getAttributes();
        String name = getAttributeValue(attributes, "name");
        String email = getAttributeValue(attributes, "email");
        dto.setEmail(email);
        dto.setName(name);
        if(provider.equals("github")){
            String avatarUrl = getAttributeValue(attributes, "avatar_url");
            String username = getAttributeValue(attributes,"login");
            dto.setUsername(username);
            dto.setImageURL(avatarUrl);
        } else if (provider.equals("google")) {
            String avatarUrl = getAttributeValue(attributes, "picture");
            dto.setImageURL(avatarUrl);
        }
        return dto;
    }

    private String getAttributeValue(Map<String, Object> attributes, String key) {
        return Optional.ofNullable(attributes.get(key))
                .map(Object::toString)
                .orElse("");
    }

    @Override
    public UserDTO mapEntityToDTO(User user) {
        UserDTO dto=new UserDTO();
        List<String> roles = user.getRoles().stream()
                .map(u->u.getName().toString())
                .collect(Collectors.toList());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setName(user.getName());
        dto.setImageURL(user.getImageURL());
        dto.setRole(roles);
        return dto;
    }
}

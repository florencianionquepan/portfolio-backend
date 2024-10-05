package com.flower.portfolio.auth.dto.mapper;

import com.flower.portfolio.auth.dto.UserDTO;
import com.flower.portfolio.model.User;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

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
}

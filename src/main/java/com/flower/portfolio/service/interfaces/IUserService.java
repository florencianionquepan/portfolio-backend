package com.flower.portfolio.service.interfaces;


import com.flower.portfolio.auth.dto.UserDTO;

public interface IUserService {
    void createOrUpdateUser(UserDTO dto, String provider);
}

package com.flower.portfolio.auth.service;


import com.flower.portfolio.auth.dto.UserDTO;
import com.flower.portfolio.model.User;

public interface IUserService {
    User createOrUpdateUser(UserDTO dto, String provider);
}

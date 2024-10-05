package com.flower.portfolio.auth.dto.mapper;

import com.flower.portfolio.auth.dto.UserDTO;
import com.flower.portfolio.model.User;


public interface IUserMapper {
    User mapToEntity(UserDTO dto, String provider);
}

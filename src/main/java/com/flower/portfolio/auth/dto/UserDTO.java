package com.flower.portfolio.auth.dto;

import lombok.Data;

@Data
public class UserDTO {
    private String email;
    private String username;
    private String name;
    private String imageURL;
    private String role;
}

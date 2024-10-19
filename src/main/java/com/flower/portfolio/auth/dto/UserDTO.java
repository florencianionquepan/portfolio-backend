package com.flower.portfolio.auth.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserDTO {
    private String email;
    private String username;
    private String name;
    private String imageURL;
    private List<String> role;
}

package com.flower.portfolio.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(nullable = false, unique = true)
    private String email;
    private String imageURL;
    private LocalDate lastLogin;
    private Boolean active;

    @ManyToMany(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    @JoinTable(
            name="roles_users",
            joinColumns = @JoinColumn(name="user_id"),
            inverseJoinColumns = @JoinColumn (name="rol_id")
    )
    @JsonIgnoreProperties(value="users")
    private List<Role> roles;

}
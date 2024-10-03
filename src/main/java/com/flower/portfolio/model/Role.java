package com.flower.portfolio.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.List;

@Entity
@Table(name="roles")
@Data
public class Role {
    @Id
    private int id;
    private RoleName name;

    @ManyToMany(mappedBy = "roles")
    @JsonIgnoreProperties(value="roles")
    private List<User> users;
}

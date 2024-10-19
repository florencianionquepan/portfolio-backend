package com.flower.portfolio.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name="technologies")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Technology {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 20)
    private String name;
    private String logoUrl;
    private String version;

    @ManyToMany(mappedBy = "technologies")
    @JsonIgnoreProperties(value="technologies")
    private List<WebProject> projects;
}

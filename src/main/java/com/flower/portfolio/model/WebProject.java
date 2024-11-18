package com.flower.portfolio.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="projects")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WebProject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 20)
    private String title;
    @Column(length = 500)
    private String description;
    private LocalDate endDate;
    private Status status;
    @OneToMany(mappedBy = "project",fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JsonIgnoreProperties(value="project")
    private List<Image> images;

    @OneToMany(mappedBy = "project",fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JsonIgnoreProperties(value="project")
    private List<Link> links;

    @ManyToMany(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    @JoinTable(
            name="projects_technologies",
            joinColumns = @JoinColumn(name="project_id"),
            inverseJoinColumns = @JoinColumn (name="technology_id")
    )
    @JsonIgnoreProperties(value="projects")
    private List<Technology> technologies;

    @ManyToOne(cascade=CascadeType.MERGE)
    @JsonIgnoreProperties(value="projects")
    private Person person;
}

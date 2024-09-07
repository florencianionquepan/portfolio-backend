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
@Table(name="persons")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 20)
    private String name;
    @Column(nullable = false, length = 20)
    private String lastName;
    @Column(nullable = false, length = 30)
    private String tel;
    private String email;
    @Column(nullable = false)
    private LocalDate dateOfBirth;
    @Column(nullable = false, length = 30)
    private String occupation;
    private String presentation;

    @OneToMany(mappedBy = "person", cascade = CascadeType.MERGE)
    @JsonIgnoreProperties(value="person")
    private List<Course> courses;
    @OneToMany(mappedBy = "person", cascade = CascadeType.MERGE)
    @JsonIgnoreProperties(value="person")
    private List<WebProject> projects;
    @OneToMany(mappedBy = "person", cascade = CascadeType.MERGE)
    @JsonIgnoreProperties(value="person")
    private List<AcademicProgram> programs;

}
